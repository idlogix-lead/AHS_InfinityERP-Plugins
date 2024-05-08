package com.idlogix.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import com.idlogix.models.X_C_Order_ParentProduct;
import com.idlogix.models.X_IDL_Assortment_Line;
import com.idlogix.models.X_M_Parent_Product;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.Callback;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;

public class addSO_Lines_Old extends SvrProcess {
	
	int pp_ID;
	int assortID;
	int boxes;
	private List<X_IDL_Assortment_Line> list;
	private List<Integer> prodIds;
	private int record_id;
	private int order_id;
	private Map<Integer,Integer> assortMapper;
	private Map<Integer,Integer> prodMapper;
	private MOrder order;
	List<MOrderLine> orderLines;
	List<Integer> assortIDs;
	
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		record_id = getRecord_ID();
		ProcessInfoParameter[] paras = getParameter();
		for (ProcessInfoParameter p : paras) {
			String name = p.getParameterName();
			if(name.equalsIgnoreCase("C_Order_ID"))
				order_id = p.getParameterAsInt();
			else if (name.equalsIgnoreCase("M_Parent_Product_ID"))
				pp_ID = p.getParameterAsInt();
			else if (name.equalsIgnoreCase("IDL_Assortment_ID"))
				assortID = p.getParameterAsInt();	
			else if(name.equalsIgnoreCase("Boxes"))
				boxes = p.getParameterAsInt();
			
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
		
		
		X_M_Parent_Product parent_Prod = new X_M_Parent_Product(getCtx(),pp_ID,null);
		X_C_Order_ParentProduct odpp = new X_C_Order_ParentProduct(getCtx(), record_id, (String)null);
		order =new MOrder(getCtx(), order_id, null);
		final String docstatus = order.getDocStatus();
		prodIds = X_M_Parent_Product.getChildProdIds(pp_ID);
		assortMapper = X_IDL_Assortment_Line.sizeToQtyMapper(assortID);	
		orderLines = getOrderLines(order_id);
		assortIDs = existingAssortments();
		if(assortIDs.size()>0) {
		processUI.ask("Lines Already Entered Against this Assortment!", new Callback<Boolean>() {
			@Override
			public void onCallback(Boolean result) {
				// TODO Auto-generated method stub
				if(result && docstatus.equalsIgnoreCase("DR"))
					updateLines();
			}
			});
		}
		else 
			insertLines();
		return ""+prodIds.size();
	}	
	public List<MOrderLine> getOrderLines(int orderID){
		List<MOrderLine> lines = new ArrayList<MOrderLine>();
		String strSQL = "select c_orderline_id::integer \n"
				+ " from adempiere.c_orderline ol\n"
				+ " join adempiere.m_product pd ON ol.m_product_id = pd.m_product_id "
				+ " where ol.c_order_id =  " + order_id +"\n"
				+ " and ol.m_parent_product_id = "+ pp_ID+"\n"
				+ " and pd.idl_assortment_id = "+ assortID+"\n"
				+ " and ol.isActive = 'Y' and p.isActive = 'Y' " ;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;	
		 try
		 {
		 pstmt = DB.prepareStatement (strSQL.toString(), null);
		 rs = pstmt.executeQuery ();
			 while(rs.next()) {
			  int lineID = rs.getInt("c_orderline_id");
			  MOrderLine line = new MOrderLine(getCtx(), lineID, null);
			  lines.add(line);			  
			 }
		 }
		 catch (Exception e)
		 {
		 	throw new AdempiereException(e);
		 }
		 return lines;
	}
	public List<Integer> existingAssortments(){
		List<Integer> lines = new ArrayList<Integer>();
		String strSQL = "select coalesce(pd.idl_assortment_id,0)::integer assort_id\n "
				+ " from adempiere.c_orderline ol\n"
				+ " join adempiere.m_product pd ON ol.m_product_id = pd.m_product_id "
				+ " where ol.c_order_id =  " + order_id +"\n"
				+ " and ol.m_parent_product_id = "+ pp_ID+"\n"
				+ " and pd.idl_assortment_id = "+ assortID;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;	
		 try
		 {
		 pstmt = DB.prepareStatement (strSQL.toString(), null);
		 rs = pstmt.executeQuery ();
			 while(rs.next()) {
			  int ID = rs.getInt("assort_id");
			  lines.add(ID);			  
			 }
		 }
		 catch (Exception e)
		 {
		 	throw new AdempiereException(e);
		 }
		 return lines;
	}
	public void insertLines() {
		
		if(prodIds.size()==0) {
			processUI.ask("Sizes not created against this Color!", new Callback<Boolean>() {
				@Override
				public void onCallback(Boolean result) {
					// TODO Auto-generated method stub
					
				}
				});
		}
		else {
			for(int prodId:prodIds) {
				MProduct prod = new MProduct(getCtx(), prodId, null);		
				int size_id = prod.get_ValueAsInt("ER_Size_ID");
				int qty = assortMapper.getOrDefault(size_id,Integer.MIN_VALUE);
				
				if(prod.get_ValueAsInt("IDL_Assortment_ID") == assortID) {
				qty=qty*boxes;			
					MOrderLine line = new MOrderLine(getCtx(), 0, null);
					line.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
					line.set_ValueOfColumn("M_Product_ID", prodId);
					line.set_ValueOfColumn("QtyEntered",new BigDecimal(qty));
					line.set_ValueOfColumn("QtyOrdered",new BigDecimal(qty));
					line.set_ValueOfColumn("C_Order_ParentProduct_ID", record_id);
					line.set_ValueOfColumn("M_Parent_Product_ID", pp_ID);
					line.set_ValueOfColumn("IDL_Assortment_ID", assortID);
					line.setC_Tax_ID(1000000);
					line.setC_Order_ID(order_id);
					line.setOrder(order);
					log.fine(line.getPriceEntered().toString());
					line.saveEx();
				}
			}
		}
	}
	public void updateLines() {
		for(MOrderLine line:orderLines) {
			int prodID = line.get_ValueAsInt("M_Product_ID");
			MProduct prod = new MProduct(getCtx(), prodID, null);
			int sizeID = prod.get_ValueAsInt("ER_Size_ID");
			int qty = assortMapper.get(sizeID);
			qty*=boxes;
			line.set_ValueOfColumn("QtyEntered",new BigDecimal(qty));
			line.set_ValueOfColumn("QtyOrdered",new BigDecimal(qty));
			line.saveEx();
		}		
	}
	public void deleteOrderLines() {
		String strSQL = "DELETE\n"
				+ " FROM ADEMPIERE.C_ORDERLINE\n"
				+ " WHERE C_ORDER_PARENTPRODUCT_ID = " + record_id;
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;	
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
}
