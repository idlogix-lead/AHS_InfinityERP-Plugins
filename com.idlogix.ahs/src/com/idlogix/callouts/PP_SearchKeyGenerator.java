package com.idlogix.callouts;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.adempiere.base.IColumnCallout;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

import com.idlogix.models.X_ER_Article;
import com.idlogix.models.X_ER_Article_Group1;
import com.idlogix.models.X_ER_Color;
import com.idlogix.models.X_M_Brand;
import com.idlogix.models.X_M_Product_SubCategory;
import org.compiere.util.DB;

public class PP_SearchKeyGenerator implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		
		//ids below
		
		//int article_id = 999999;
		int article_id = mTab.getValue("ER_Article_ID")==null?-1:(Integer)(mTab.getValue("ER_Article_ID"));
		int color_id = mTab.getValue("ER_Color_ID")==null?-1:(Integer)(mTab.getValue("ER_Color_ID"));
		
		X_ER_Article article = new X_ER_Article ( ctx, article_id, null);
		X_ER_Color color = new X_ER_Color ( ctx, color_id, null);
		
		String prod_code = "";
		prod_code+=article.getValue()+" ";
		prod_code+=color.getName()==null?"":color.getName();
		
		mTab.setValue("Value", prod_code);
		mTab.setValue("Name", prod_code);

		
		
		
		
		
		return null;
	}

}
