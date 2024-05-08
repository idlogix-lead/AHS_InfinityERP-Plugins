package com.idlogix.processes;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import com.idlogix.models.MMasterProduction;
import com.idlogix.models.MMasterProductionLine;
import com.idlogix.models.X_ER_MasterProduction;
import com.idlogix.models.X_IDL_Assortment;
import com.idlogix.models.X_IDL_Assortment_Line;
import com.idlogix.models.X_M_Parent_Product;


public class addProductsMasterManually  extends SvrProcess{

	int id=0;
	int production_qty=1;
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		id = getRecord_ID();
		ProcessInfoParameter[] paras = getParameter();
		for (ProcessInfoParameter p : paras) {
			String name = p.getParameterName();	
			if (name.equalsIgnoreCase("Production_Qty")) {
				production_qty = p.getParameterAsInt();
				production_qty=production_qty<0?production_qty*-1:production_qty;
			}
			else {
				log.severe("Unknown Parameter: " + name);
			}
		}
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		MMasterProduction mp = new MMasterProduction(Env.getCtx(), id, get_TrxName());
		int order_id = mp.get_ValueAsInt("C_Order_ID");
		if(mp.getC_Order_ID()!=0 || mp.getC_Order_ID() != -1) {
			mp_against_order();
			return null;
		}
		
		int existing_records = getExistingRecords();
		if(existing_records==0) {
			
			int ppID = mp.get_ValueAsInt("M_Parent_Product_ID");
			X_M_Parent_Product parentprod = new X_M_Parent_Product(getCtx(), ppID, null);
			int assortmentID = parentprod.get_ValueAsInt("IDL_Assortment_ID");
			List<X_IDL_Assortment_Line> list = X_IDL_Assortment_Line.getAssortmentLines(assortmentID);
			
			
			for(X_IDL_Assortment_Line obj:list) {
				int sizeID =obj.get_ValueAsInt("ER_Size_ID");
				BigDecimal qty = BigDecimal.valueOf(obj.get_ValueAsInt("qty")*production_qty);
				MProduct prod = X_M_Parent_Product.getChildProd(ppID, sizeID);
				MMasterProductionLine pline = new MMasterProductionLine(Env.getCtx(), 0, get_TrxName());
				pline.setAD_Org_ID(mp.getAD_Org_ID()); 
				pline.setM_Product_ID(prod.getM_Product_ID());
				pline.setC_OrderLine_ID(0);
				pline.setQty(qty);
				pline.setER_MasterProduction_ID(mp.getER_MasterProduction_ID());
				pline.saveEx();	
			}	
		}
		return null;
	}
	
	private int getExistingRecords() {
		int existing_records = 0;
		String strSQL = "select count(*)::integer records\n"
				+ "from adempiere.er_masterproductionline\n"
				+ "where ER_MasterProduction_ID="+id;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement (strSQL.toString(), get_TrxName());
			rs = pstmt.executeQuery ();
			
			while (rs.next ())		//	Order
			{
				existing_records = rs.getInt("records");
			}
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		return existing_records;
	}
	
	public  void mp_against_order() {
		int MPID;
		int CID;
		MMasterProduction mp = new MMasterProduction(Env.getCtx(), id, get_TrxName());
		MPID = mp.getM_Parent_Product_ID();
		CID=mp.getC_Order_ID();
		String strSQL = "select oline.* from c_orderline oline \n" + 
				"                inner join adempiere.m_product p on p.m_product_id = oline.m_product_id\n" + 
				"                where  oline.C_Order_ID=" + CID + "\n" + 
				"                and  p.M_Parent_Product_ID="+MPID + 
				"	and oline.c_orderline_id \n" + 
				"		not in (select c_orderline_id from adempiere.er_masterproductionline pline where pline.er_masterproduction_id=" + mp.get_ID() + ")";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement (strSQL.toString(), get_TrxName());
			rs = pstmt.executeQuery ();
			
			while (rs.next ())		//	Order
			{
				MMasterProductionLine pline = new MMasterProductionLine(Env.getCtx(), 0, get_TrxName());
				pline.setAD_Org_ID(mp.getAD_Org_ID());
			    
				pline.setM_Product_ID(rs.getInt("M_Product_ID"));
				pline.setC_OrderLine_ID(rs.getInt("C_OrderLine_ID"));
				pline.setQty(rs.getBigDecimal("qtyEntered"));
				pline.setER_MasterProduction_ID(mp.getER_MasterProduction_ID());
				pline.save();
			}
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
	}
	

}
