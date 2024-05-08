package com.idlogix.eventHandlers;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MProductPrice;
import org.osgi.service.event.Event;

import com.idlogix.processes.DelegateNameValueChange;
import com.idlogix.processes.DeleteOrderLines;

public class ArticleNameValueChanged extends AbstractEventHandler {

	@Override
	protected void doHandleEvent(Event event) {
		// TODO Auto-generated method stub
		
		if(event.getProperty("tableName").equals("ER_Article")) {
		DelegateNameValueChange dnvc = new DelegateNameValueChange(getPO(event));
		dnvc.doChange();
		}
		if(event.getProperty("tableName").equals("C_Order_ParentProduct")) 
		{
			DeleteOrderLines.deleteOrderLines(getPO(event).get_ValueAsInt("C_Order_ParentProduct_ID"));
		}
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, "ER_Article");
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, "C_Order_ParentProduct");
		
	}

}
