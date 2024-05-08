package com.idlogix.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MQuery;
import org.compiere.model.MWindow;
import org.compiere.model.MQuery;

public class OpenWindowCallout implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
//		MWindow mWindow = MWindow.get(ctx,140);
//		SessionManager.getAppDesktop().openWindow(140, null, null);
//		MQuery query= MQuery.getEqualQuery("M_Product", true);
//		SessionManager.getAppDesktop().openWindow(140, query, null);
		return null;
	}

}
