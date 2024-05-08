package com.idlogix.forms;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import org.compiere.apps.IStatusBar;
import org.compiere.grid.CreateFrom;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MInOut;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;

public class CreateFromLaborWage extends CreateFrom {

//	List<BigDecimal> org_cartons = new ArrayList<BigDecimal>();
	Map<String,BigDecimal> org_cartons = new HashMap<String,BigDecimal>();
	public CreateFromLaborWage(GridTab gridTab) {
		super(gridTab);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dynInit() throws Exception {
		// TODO Auto-generated method stub
		setTitle("GRN");
		return false;
	}

	@Override
	public void info(IMiniTable miniTable, IStatusBar statusBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean save(IMiniTable miniTable, String trxName) {
		// TODO Auto-generated method stub
		int locator_id = CreateFromArticleUI.locatorField.getItemAtIndex(0).toKeyNamePair().getKey();
		if(CreateFromArticleUI.locatorField.getSelectedItem()!=null)
		    locator_id = CreateFromArticleUI.locatorField.getSelectedItem().toKeyNamePair().getKey();
			KeyNamePair ord = (KeyNamePair)miniTable.getValueAt(0, 1);
			int order_id =ord.getKey();
			int inout_id = ((Integer) getGridTab().getValue("M_InOut_ID")).intValue();
			MOrder mOrder = new MOrder(Env.getCtx(), order_id, (String)null);
			MInOut io = new MInOut(Env.getCtx(), inout_id, (String)null);
			io.setC_Order_ID(order_id);
			io.setDateOrdered(mOrder.getDateOrdered());
			io.setPOReference(mOrder.getPOReference());
			io.save();
			Map<Integer,Integer> receipt_lines = getRecieptLines(inout_id);
		
		for(int i=0;i<miniTable.getRowCount();i++) {
			if(((Boolean) miniTable.getValueAt(i, 0)).booleanValue()) {
				KeyNamePair art = (KeyNamePair)miniTable.getValueAt(i, 2);				
				int article_id = art.getKey();
				KeyNamePair assort = (KeyNamePair)miniTable.getValueAt(i, 3);				
				int assortId = assort.getKey();
				BigDecimal cartons = (BigDecimal) miniTable.getValueAt(i, 4);
				cartons = cartons.compareTo(Env.ZERO)<0?Env.ZERO:cartons;
				String search_key = Integer.toString(art.getKey())+Integer.toString(assort.getKey());
				cartons = cartons.compareTo(org_cartons.get(search_key))>0?org_cartons.get(Integer.toString(ord.getKey())+Integer.toString(assort.getKey())):cartons;
				if(cartons.compareTo(Env.ZERO)==0)
				continue;				
				//query 
				String sql = "\n"
						+ "select pd.m_product_id,ol.c_orderline_id,ol.c_uom_id,\n"
						+  cartons +" * al.qty as qty\n"
						+ "\n"
						+ "				from adempiere.c_order od\n"
						+ "				join adempiere.c_order_parentproduct opp ON od.c_order_id = opp.c_order_id\n"
						+ "				join adempiere.m_parent_product pp ON (opp.m_parent_product_id = pp.m_parent_product_id)\n"
						+ "				join adempiere.c_orderline ol ON (opp.c_order_parentproduct_id = ol.c_order_parentproduct_id)\n"
						+ "				join adempiere.m_product pd ON ol.m_product_id = pd.m_product_id\n"
						+ "				join adempiere.er_size s ON pd.er_size_id = s.er_size_id\n"
						+ "				join adempiere.IDL_Assortment_Line al ON pd.idl_assortment_id = al.idl_assortment_id and s.er_size_id = al.er_size_id\n"
						+ "				where od.c_order_id = "+order_id+" and pp.m_parent_product_id = "+article_id+" and pd.idl_assortment_id  ="+assortId+"\n"
						+ "";
				//
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					pstmt = DB.prepareStatement(sql.toString(), null);
					rs = pstmt.executeQuery();
					while (rs.next())
					{
						
						
						int prod_id = rs.getInt("m_product_id");
						int uom_id = rs.getInt("c_uom_id");
						BigDecimal qty = rs.getBigDecimal("qty");
						int orderline_id = rs.getInt("c_orderline_id");
						if(receipt_lines.get(orderline_id)!=null) 
						{
							MInOutLine line = new MInOutLine(Env.getCtx(),(Integer)receipt_lines.get(orderline_id) , (String) null);
							BigDecimal newQty = line.getQtyEntered().add(qty);
							line.setQty(newQty);
							line.setMovementQty(newQty);
							line.saveEx();
						}
						else 
						{
							MInOutLine line = new MInOutLine(Env.getCtx(), 0, (String) null);
							line.setM_InOut_ID(inout_id);
							line.setM_Locator_ID(locator_id);
							line.setM_Product_ID(prod_id);
							line.setC_UOM_ID(uom_id);
							line.setQty(qty);
							line.setMovementQty(qty);
							line.setC_OrderLine_ID(orderline_id);
							line.save();
						}
						
					}
				}
				catch (SQLException e)
				{
					log.log(Level.SEVERE, sql.toString(), e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
					getGridTab().dataRefresh();
				}
				
			}
		}
		if (log.isLoggable(Level.INFO)) log.info("In The Save Function");
		return false;
	}
	protected Vector<String> getOISColumnNames()
	{
	    Vector<String> columnNames = new Vector<String>(10);
	    columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
	    columnNames.add(Msg.translate(Env.getCtx(), "Order"));
	    columnNames.add(Msg.translate(Env.getCtx(), "Article"));
	    columnNames.add(Msg.translate(Env.getCtx(), "Assortment"));
	    columnNames.add(Msg.translate(Env.getCtx(), "Cartons"));
	    
	    
	    return columnNames;
	    
	}
	protected void configureMiniTable (IMiniTable miniTable)
	{
		int i = 1;
		miniTable.setColumnClass(0, Boolean.class,  false);     		//  Selection
		miniTable.setColumnClass(i++, String.class, false);   		//  Order
		miniTable.setColumnClass(i++, String.class, false);   	//  Article
		miniTable.setColumnClass(i++, String.class, false);   	//  Assortment
		miniTable.setColumnClass(i++, BigDecimal.class, false);   	//  Cartons
		
		miniTable.autoSize();

	}
	protected ArrayList<KeyNamePair> getOrderList (int c_bpartner_id,String issoTrx)
	{
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();

		String sql = "select od.c_order_id,od.documentno\n"
				+ "from adempiere.c_orderline ol\n"
				+ "join adempiere.c_order od ON ol.c_order_id = od.c_order_id\n"
				+ "join adempiere.c_order_parentproduct opp ON od.c_order_id = opp.c_order_id\n"
				+ "where od.issotrx = '"+issoTrx+"' and od.docstatus = 'CO' and od.isactive = 'Y'  and ol.qtyreserved > 0  \n"
				+" and od.c_bpartner_id = "+c_bpartner_id+" \n"
				+ "group by od.c_order_id,od.documentno";
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		return list;
	}
	protected Vector<Vector<Object>> getOrderData (int C_Order_ID)
	{
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		org_cartons.clear();
		String sql = "select c_order_id,documentno,m_parent_product_id,pp_name,idl_assortment_id,assortment, MIN(cartons) cartons \n"
				+ "				from (\n"
				+ "				select od.c_order_id::integer,od.documentno,pp.m_parent_product_id::integer,pp.name pp_name,ass.idl_assortment_id,ass.value assortment,\n"
				+ "								case when (ol.qtyordered-(select coalesce(SUM(movementqty),0) from adempiere.M_InOutLine where c_orderline_id = ol.c_orderline_id )) < 0 then 0\n"
				+ "								else floor((ol.qtyordered - (select coalesce(SUM(movementqty),0) from adempiere.M_InOutLine where c_orderline_id = ol.c_orderline_id ))/al.Qty) end cartons\n"
				+ "												from adempiere.c_order od\n"
				+ "												join adempiere.c_order_parentproduct opp ON od.c_order_id = opp.c_order_id\n"
				+ "												join adempiere.m_parent_product pp ON (opp.m_parent_product_id = pp.m_parent_product_id)\n"
				+ "												join adempiere.c_orderline ol ON (opp.c_order_parentproduct_id = ol.c_order_parentproduct_id)\n"
				+ "												join adempiere.m_product pd ON ol.m_product_id = pd.m_product_id\n"
				+ "												join adempiere.er_size s ON pd.er_size_id = s.er_size_id\n"
				+ "												join adempiere.IDL_Assortment ass ON pd.idl_assortment_id = ass.idl_assortment_id \n"
				+ "												join adempiere.IDL_Assortment_Line al ON pd.idl_assortment_id = al.idl_assortment_id and s.er_size_id = al.er_size_id\n"
				+ "												where od.c_order_id = "+C_Order_ID+"\n"
				+ ") abc\n"
				+ "group by c_order_id::integer,documentno,m_parent_product_id::integer,pp_name,idl_assortment_id,assortment ";
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>();
				line.add(Boolean.FALSE); 
				KeyNamePair ord = new KeyNamePair(rs.getInt("c_order_id"), rs.getString("documentno").trim());
				line.add(ord); 
				KeyNamePair art = new KeyNamePair(rs.getInt("m_parent_product_id"), rs.getString("pp_name").trim());
				line.add(art);
				KeyNamePair assort = new KeyNamePair(rs.getInt("idl_assortment_id"), rs.getString("assortment").trim());
				line.add(assort); 
				BigDecimal carton = rs.getBigDecimal("cartons");
				line.add(carton);
				String search_key = Integer.toString(art.getKey())+Integer.toString(assort.getKey());
				org_cartons.put(search_key, carton);
				
				data.add(line);
			};
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return data;
	}
	protected ArrayList<KeyNamePair> getLocatorList ()
	{
		int warehouse_id = ((Integer) getGridTab().getValue("M_Warehouse_ID")).intValue();
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		
		String sql = "select m_locator_id,value\n"
				+ "from adempiere.m_locator \n"
				+ "where m_warehouse_id = "+warehouse_id;
				
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		return list;
	}
	public Map<Integer,Integer> getRecieptLines(int m_inout_id){
		Map<Integer,Integer> lines = new HashMap<Integer,Integer>();		
		String sql = "select c_orderline_id,m_inoutline_id\n"
				+ "from adempiere.m_inoutline\n"
				+ "where m_inout_id = "+m_inout_id;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				lines.put(rs.getInt("c_orderline_id"), rs.getInt("m_inoutline_id"));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return lines;
	} 
	
}
