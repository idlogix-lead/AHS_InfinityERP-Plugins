package com.idlogix.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.webui.editor.WNumberEditor;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;



public class PriceFieldReadOnly implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub

//		
//		WNumberEditor c = new WNumberEditor(mField);
//		c.setReadWrite(false);
//	
		
		return "";
	}

}
