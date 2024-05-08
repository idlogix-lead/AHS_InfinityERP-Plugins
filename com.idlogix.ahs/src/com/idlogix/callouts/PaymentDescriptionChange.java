package com.idlogix.callouts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.event.EventManager;
import org.adempiere.base.event.IEventManager;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MPayment;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventProperties;

public class PaymentDescriptionChange implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Map<String,Object> props =new HashMap<String, Object>();
		MPayment payment = new MPayment(ctx, 1000041, (String)null);
		List<String> errorMsgs = new ArrayList<String>();
		String tableName = "C_Payment";
		String topic = IEventTopics.DOC_AFTER_COMPLETE;
		props.put("event.data", payment);
		props.put("event.errorMessages", errorMsgs);
		props.put("tableName", tableName);
		EventProperties eventProperties = new EventProperties(props);
		Event e = new Event (topic,props);
		EventManager.getInstance().sendEvent(e);
		
		return null;
	}

}
