package com.idlogix.eventHandlers;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProductPrice;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.osgi.service.event.Event;

import com.idlogix.processes.DelegateNameValueChange;
import com.idlogix.processes.DeleteOrderLines;

public class AttachLocator extends AbstractEventHandler {

	@Override
	protected void doHandleEvent(Event event) {
		// TODO Auto-generated method stub
		
		PO po = getPO(event);
		int ad_org_id = po.get_ValueAsInt("AD_Org_ID");
		if(ad_org_id != 1000001)
			return;
		
		if(event.getProperty("tableName").equals("M_InOutLine") ) 
		{
				
			int orderlineID = po.get_ValueAsInt("C_OrderLine_ID");
			MOrderLine mOrderLine = new MOrderLine(Env.getCtx(), orderlineID, null);
			MOrder mOrder = new MOrder(Env.getCtx(),mOrderLine.getC_Order_ID(),null);
			int docTypeID = mOrder.getC_DocTypeTarget_ID();
			if(docTypeID == 1000033 || docTypeID == 1000034) {
				int locatorID = mOrderLine.get_ValueAsInt("M_Locator_ID");
				if(locatorID>0) {
					po.set_ValueOfColumn("M_Locator_ID", locatorID);
					return;
				}
			}
			
		}
		if(event.getProperty("tableName").equals("C_InvoiceLine") ) 
		{
				
			int orderlineID = po.get_ValueAsInt("C_OrderLine_ID");
			MOrderLine mOrderLine = new MOrderLine(Env.getCtx(), orderlineID, null);
			int locatorID = mOrderLine.get_ValueAsInt("M_Locator_ID");
			MOrder mOrder = new MOrder(Env.getCtx(),mOrderLine.getC_Order_ID(),null);
			int docTypeID = mOrder.getC_DocTypeTarget_ID();
			if(docTypeID == 1000033 || docTypeID == 1000034) {
				if(locatorID>0) {
					po.set_ValueOfColumn("M_Locator_ID", locatorID);
					return;
				}
			}
			int inoutlineID = po.get_ValueAsInt("M_InOutLine_ID");
			MInOutLine mInoutLine = new MInOutLine(Env.getCtx(), inoutlineID, null);
			locatorID = mInoutLine.get_ValueAsInt("M_Locator_ID");
			
			if(locatorID>0) {
				po.set_ValueOfColumn("M_Locator_ID", locatorID);
				return;
			}
				
			
			
		}
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInOutLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInvoiceLine.Table_Name);
		
		
	}

}
