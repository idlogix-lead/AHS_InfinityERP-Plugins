package com.idlogix.processes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.idlogix.models.X_IDL_Assortment_Line;
import com.idlogix.models.X_M_Parent_Product;

public class addSO_Lines extends SvrProcess {
	
	int pp_ID;
	int assortID;
	int boxes;
	private Map<Integer,Integer> prodIds;
	private int record_id;
	private int order_id;
	private int locator_id;
	private int tax_id;
	private Map<Integer,Integer> assortMapper;
	private MOrder order;
	Map<Integer,Integer> orderLines;
	BigDecimal discount1 = Env.ZERO;
	BigDecimal discount2 = Env.ZERO;
	BigDecimal discount3 = Env.ZERO;
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		record_id = getRecord_ID();
		ProcessInfoParameter[] paras = getParameter();
		for (ProcessInfoParameter p : paras) {
			String name = p.getParameterName();
			if(name.equalsIgnoreCase("C_Order_ID"))
				order_id = p.getParameterAsInt();
			if(name.equalsIgnoreCase("M_Locator_ID"))
				locator_id = p.getParameterAsInt();
			if(name.equalsIgnoreCase("C_Tax_ID"))
				tax_id = p.getParameterAsInt();
			else if (name.equalsIgnoreCase("M_Parent_Product_ID"))
				pp_ID = p.getParameterAsInt();
			else if (name.equalsIgnoreCase("IDL_Assortment_ID"))
				assortID = p.getParameterAsInt();	
			else if(name.equalsIgnoreCase("Boxes"))
				boxes = p.getParameterAsInt();
			else if(name.equalsIgnoreCase("discount1"))
				discount1 = p.getParameterAsBigDecimal();
			else if(name.equalsIgnoreCase("discount2"))
				discount2 = p.getParameterAsBigDecimal();
			else if(name.equalsIgnoreCase("discount3"))
				discount3 = p.getParameterAsBigDecimal();
			
			else {
				log.severe("Unknown Parameter: " + name);
			}
		}
		boxes = Math.abs(boxes);		
	}

	@Override
	protected String doIt() throws Exception {
		// Get Parent Product and Order from Ctx. Get Assortment_ID from parent prod
		// and get all sizes against that assortment in form of size-to-qty mapper.
		// check if lines already present than update otherwise insert.
		
		
		order =new MOrder(getCtx(), order_id, null);
		final String docstatus = order.getDocStatus();
		if(docstatus.equalsIgnoreCase("CO"))
			return null;
		prodIds = X_M_Parent_Product.getChildProdIds(pp_ID,assortID);
		assortMapper = X_IDL_Assortment_Line.sizeToQtyMapper(assortID);	
		orderLines = getOrderLines(order_id);
		updateOrInsertLines();
		return null;
	}	
	public void updateOrInsertLines() {
		Iterator<Integer> itr = assortMapper.keySet().iterator();
		int quantity = 0;
		for (Map.Entry<Integer, Integer> entry : assortMapper.entrySet()) {
			quantity += entry.getValue();
        }
		BigDecimal perUnitDiscount = Env.ZERO;
		if(quantity>0) {
			perUnitDiscount =discount3.divide((BigDecimal.valueOf(quantity)), 2, RoundingMode.HALF_EVEN);
		}
		
		itr = prodIds.keySet().iterator();
		while (itr.hasNext()) 
		{
			 int prodId = itr.next();
		     if(orderLines.get(prodId) != null) {
		    	    MOrderLine line = new MOrderLine(getCtx(), orderLines.get(prodId), null);					
					int qty = assortMapper.getOrDefault(prodIds.get(prodId),Integer.MIN_VALUE);
					qty*=boxes;
					line.set_ValueOfColumn("QtyEntered",new BigDecimal(qty));
					line.set_ValueOfColumn("QtyOrdered",new BigDecimal(qty));
					line.saveEx();
					
		     }
		     else {
		    	 	int qty = assortMapper.getOrDefault(prodIds.get(prodId),Integer.MIN_VALUE);
		    	 	qty=qty*boxes;
		    	    MOrderLine line = new MOrderLine(getCtx(), 0, null);
					line.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
					line.set_ValueOfColumn("M_Product_ID", prodId);
					line.set_ValueOfColumn("QtyEntered",new BigDecimal(qty));
					line.set_ValueOfColumn("QtyOrdered",new BigDecimal(qty));
					line.set_ValueOfColumn("C_Order_ParentProduct_ID", record_id);
					line.set_ValueOfColumn("M_Parent_Product_ID", pp_ID);
					line.set_ValueOfColumn("IDL_Assortment_ID", assortID);
					line.set_ValueOfColumn("M_Locator_ID", locator_id);
					line.setC_Tax_ID(tax_id);
					line.setC_Order_ID(order_id);
					line.setOrder(order);
					log.fine(line.getPriceEntered().toString());
					line.saveEx();
					BigDecimal discount  = (line.getPriceList().subtract(line.getPriceActual()));
					if(discount.compareTo(Env.ZERO)<0) {
						discount = Env.ZERO;
					}
					line.set_ValueOfColumn("DefaultDiscount", discount);
					line.set_ValueOfColumn("Dis1Percentage", discount1);
					line.set_ValueOfColumn("Dis2Percentage", discount2);
					applyDiscount(line, discount1, true, "discount1");
					applyDiscount(line, discount2, true, "discount2");
					applyDiscount(line, perUnitDiscount, false,"discount3");
					line.setDiscount();
					line.save();
					
		     }	     
		}		
	}
	public Map<Integer,Integer> getOrderLines(int orderID){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		String strSQL =   "select ol.m_product_id::integer,ol.c_orderline_id::integer \n"
						+ " from adempiere.c_orderline ol\n"
						+ " join adempiere.m_product pd ON ol.m_product_id = pd.m_product_id "
						+ " where ol.c_order_id =  " + order_id +"\n"
						+ " and ol.m_parent_product_id = "+ pp_ID+"\n"
						+ " and pd.idl_assortment_id = "+ assortID+"\n"
						+ " and ol.isActive = 'Y' and pd.isActive = 'Y' " ;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;	
		 try
		 {
		 pstmt = DB.prepareStatement (strSQL.toString(), null);
		 rs = pstmt.executeQuery ();
			 while(rs.next()) {
			  map.put(rs.getInt("m_product_id"),rs.getInt("c_orderline_id"));			  
			 }
		 }
		 catch (Exception e)
		 {
		 	throw new AdempiereException(e);
		 }
		 return map;
	}
	public void deleteOrderLines() {
		String strSQL = "DELETE\n"
				+ " FROM ADEMPIERE.C_ORDERLINE\n"
				+ " WHERE C_ORDER_PARENTPRODUCT_ID = " + record_id;
			 PreparedStatement pstmt = null;	
			 try
			 {
			 pstmt = DB.prepareStatement (strSQL.toString(), null);
			 pstmt.executeUpdate();
			 }
			 catch (Exception e)
			 {
			 	throw new AdempiereException(e);
			 }		
	}

	private void applyDiscount(MOrderLine line,BigDecimal dis,boolean isPerUnit,String colname) {
		
		BigDecimal price = line.getPriceEntered();
		BigDecimal listprice = line.getPriceList();
		if(listprice.compareTo(price)<0) {
			return;
		}
		MathContext m = new MathContext(2);
		if(isPerUnit) {
			dis = dis.divide(Env.ONEHUNDRED).multiply(price).setScale(2, RoundingMode.HALF_EVEN);
		}
		
		if(price.compareTo(dis)<0)
			dis = price;
		price = price.subtract(dis);
		if(price.compareTo(Env.ZERO)<=0)
			price =Env.ZERO;
		BigDecimal newdis = listprice.subtract(price);
		newdis = newdis.divide(listprice, 2, RoundingMode.HALF_EVEN);
		newdis = newdis.multiply(Env.ONEHUNDRED).setScale(2, RoundingMode.HALF_EVEN);
		line.setPriceEntered(price);
		line.setPriceActual(price);
		line.setDiscount(newdis);
		line.set_ValueOfColumn(colname, dis);
	}
}
