package com.idlogix.callouts;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.adempiere.base.IColumnCallout;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import com.idlogix.models.X_ER_Article_Group1;
import com.idlogix.models.X_M_Brand;
import com.idlogix.models.X_M_Product_SubCategory;
import org.compiere.util.DB;

public class GenerateEmployeeKey implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		
		//ids below
		
//		String tabWhereClause = mTab.getWhereExtended();
//		if(tabWhereClause.contains("C_BPartner.IsEmployee = 'Y'")) 
//		{
//			String key ="";
//			String strSQL = "select coalesce(max(substring(value FROM '[0-9]+')::integer),0)+1 id \n"
//					+ "from adempiere.c_bpartner bp\n"
//					+ "where isemployee = 'Y'  and ad_org_id = "+mTab.getValue("AD_Org_ID") ;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			
//			try
//			{
//				pstmt = DB.prepareStatement (strSQL.toString(), null);
//				rs = pstmt.executeQuery ();
//				
//				while (rs.next ())	
//				{
//					key = rs.getString("id");
//					
//				}
//			}
//			catch (Exception e)
//			{
//				throw new AdempiereException(e);
//			}
//			mTab.setValue("Value", key);
//			
//		}
//		
		return null;
	}

}
