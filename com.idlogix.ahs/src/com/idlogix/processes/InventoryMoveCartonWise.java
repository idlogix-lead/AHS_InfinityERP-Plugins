package com.idlogix.processes;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.model.MMovementLine;
public class InventoryMoveCartonWise extends SvrProcess {
	
	int m_PPID;
	int orgID;
	int m_LocatorFromID;
	int mLocatorToID;
	int m_AssortID;
	int boxes;
	int m_ProductID;
	int m_MovementQty;
	int m_ColorID;
	int record_id;
	
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		record_id = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if(name.equalsIgnoreCase("AD_Org_ID"))
				orgID = para[i].getParameterAsInt();
			if(name.equalsIgnoreCase("M_Parent_Product_ID"))
				m_PPID = para[i].getParameterAsInt();
			else if(name.equalsIgnoreCase("M_LocatorFrom_ID"))
				m_LocatorFromID = para[i].getParameterAsInt();
			else if(name.equalsIgnoreCase("M_LocatorTo_ID"))
				mLocatorToID = para[i].getParameterAsInt();
			else if(name.equalsIgnoreCase("IDL_Assortment_ID"))
				m_AssortID = para[i].getParameterAsInt();
			else if(name.equalsIgnoreCase("ER_Color_ID"))
				m_ColorID = para[i].getParameterAsInt();
			else if(name.equalsIgnoreCase("Boxes"))
				boxes = para[i].getParameterAsInt();
			else 
				log.severe("Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		String strSQL =   
				"SELECT tr.m_product_id::integer,\n"
				+ "case when COALESCE(SUM(tr.movementqty),0) -(assl.qty * "+boxes+") <0 then  "
						+ "COALESCE(SUM(tr.movementqty),0)::integer else  (assl.qty * "+boxes+")::integer   end movement_qty\n"
				+ "FROM adempiere.rv_transaction tr\n"
				+ "LEFT JOIN adempiere.m_product p ON tr.m_product_id = p.m_product_id\n"
				+ "LEFT JOIN adempiere.er_size si ON si.er_size_id = p.er_size_id\n"
				+ "LEFT JOIN adempiere.idl_assortment asst ON p.idl_assortment_id=asst.idl_assortment_id\n"
				+ "LEFT JOIN adempiere.IDL_Assortment_Line assl ON asst.idl_assortment_id = assl.idl_assortment_id and p.er_size_id = assl.er_size_id\n"
				+ "WHERE  tr.ad_org_id = "+orgID+" \n"
				+ "and tr.M_Locator_ID="+m_LocatorFromID+"\n"
				+ "AND p.isactive ='Y' and tr.m_product_category_id = 1000004\n"
				+ "and p.M_Parent_Product_ID="+m_PPID+"\n"
				+ "and p.IDL_Assortment_ID="+m_AssortID+"\n"
				+ "GROUP BY tr.m_product_id, tr.value, tr.name,\n"
				+ "assl.qty,si.name,asst.alphabet_cat,asst.name\n"
				+ "having COALESCE(SUM(tr.movementqty),0) > 0" ;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;	
		 try
		 {
		 pstmt = DB.prepareStatement (strSQL.toString(), null);
		 rs = pstmt.executeQuery ();
			 while(rs.next()) {
			  			  
				 MMovementLine ml = new MMovementLine(getCtx(), 0, (String)null);
				 ml.setM_Movement_ID(record_id);
				 ml.setM_Product_ID(rs.getInt("m_product_id"));
				 ml.setM_Locator_ID(m_LocatorFromID);
				 ml.setM_LocatorTo_ID(mLocatorToID);
				 ml.setMovementQty(new BigDecimal(rs.getInt("movement_qty")));
				 ml.save();
				 
			 }
		 }
		 catch (Exception e)
		 {
		 	throw new AdempiereException(e);
		 }
		return null;
	}

}
