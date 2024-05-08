package com.idlogix.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eri.model.MMasterProduction;
import org.eri.model.MMasterProductionLine;

public class AddProductOnMasterProduction extends SvrProcess {

	int id = 0;
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		id = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {

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
		
		
		return "";
	}

}
