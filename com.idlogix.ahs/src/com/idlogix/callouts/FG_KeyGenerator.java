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

public class FG_KeyGenerator implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		
		//ids below
		
		int article_id = 0;
		int brand_id = mTab.getValue("M_Brand_ID")==null?-1:(Integer)(mTab.getValue("M_Brand_ID"));
		int sole_id = mTab.getValue("ER_Article_Group1_ID")==null?-1:(Integer)(mTab.getValue("ER_Article_Group1_ID"));
		int psc_id = mTab.getValue("M_Product_SubCategory_ID")==null?-1:(Integer)(mTab.getValue("M_Product_SubCategory_ID"));
		//models below
		X_M_Brand brand = new X_M_Brand ( ctx, brand_id, null);
		X_ER_Article_Group1 sole = new X_ER_Article_Group1 ( ctx, sole_id, null);
		X_M_Product_SubCategory psc = new X_M_Product_SubCategory ( ctx, psc_id, null);
		
		ArrayList<String> tokens = new ArrayList<String>();
		
		String prod_code = "";
		if(mTab.getValue("Gender")!=null)tokens.add(mTab.getValue("Gender").toString());
		if(brand_id!=-1)tokens.add(brand.getValue().toString());
		//if(psc_id!=-1)tokens.add(psc.getValue().toString());
		if(psc_id!=-1)tokens.add("-"+psc.getValue().toString());
		if(sole_id!=-1)tokens.add("-"+sole.getValue().toString());
		for(String s:tokens) {
			prod_code+=s+"";
		}
		
		//if(mTab.getValue("Value")==null) {
		String strSQL = "select MAX(NULLIF(regexp_replace(RIGHT(value,4), '\\D','','g'), ''))::integer as id \n" + 
				"		from adempiere.er_article \n"+
				" where er_article_group1_id = "+sole_id  ;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement (strSQL.toString(), null);
			rs = pstmt.executeQuery ();
			
			while (rs.next ())		//	Order
			{
				article_id = rs.getInt("id")+1;
			}
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		//}
		//else {
			//String val = mTab.getValue("Value").toString();
		//	article_id = Integer.parseInt(val.substring(val.length()-7,val.length()));
		//}
//		if(mTab.getValue("Value")!=null && !(mTab.getValue("Value").toString().contains("-")))
		String.format("%04d", article_id);
		prod_code+="-"+ String.format("%05d", article_id);
		mTab.setValue("Value", prod_code);
		mTab.setValue("Name", prod_code);

		
		
	
		
		
		
		
		return null;
	}

}
