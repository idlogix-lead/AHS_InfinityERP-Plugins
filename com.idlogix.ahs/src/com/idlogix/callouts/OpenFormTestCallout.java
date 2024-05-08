package com.idlogix.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import com.idlogix.forms.CreateFromArticleUI;

public class OpenFormTestCallout implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
//		mTab.setValue("Description", "Description");
		
		
		
		if(mTab.isNew())
			return null;
		if(mTab.getValue("C_BPartner_ID") !=null) {
			CreateFromArticleUI cui = new CreateFromArticleUI(mTab);
		}
		return null;
	}

}
