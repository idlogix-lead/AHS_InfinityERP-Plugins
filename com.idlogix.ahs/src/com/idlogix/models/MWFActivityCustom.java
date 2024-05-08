package com.idlogix.models;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MUser;
import org.compiere.model.PO;
import org.compiere.process.StateEngine;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.wf.MWFEventAudit;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFProcess;

public class MWFActivityCustom extends org.compiere.wf.MWFActivity {

	public MWFActivityCustom (Properties ctx, int AD_WF_Activity_ID, String trxName)
	{
		super (ctx, AD_WF_Activity_ID, trxName);
		if (AD_WF_Activity_ID == 0)
			throw new IllegalArgumentException ("Cannot create new WF Activity directly");
	
	}	//	MWFActivity

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MWFActivityCustom (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	
	}	//	MWFActivity

	/**
	 * 	Parent Contructor
	 *	@param process process
	 *	@param AD_WF_Node_ID start node
	 */
	public MWFActivityCustom (MWFProcess process, int AD_WF_Node_ID)
	{
		super (process, AD_WF_Node_ID);
		
	}	//	MWFActivity
	
	/**
	 * 	Process-aware Parent Contructor
	 *	@param process process
	 *	@param ctx context
	 *	@param rs record to load
	 *  @param trx transaction name
	 */
	public MWFActivityCustom (MWFProcess process, Properties ctx, ResultSet rs, String trxName)
	{
		super(process,ctx, rs, trxName);
		
	}
	
	/**
	 * 	Parent Contructor
	 *	@param process process
	 *	@param AD_WF_Node_ID start node
	 *	@param lastPO PO from the previously executed node
	 */
	public MWFActivityCustom(MWFProcess process, int next_ID, PO lastPO) {
		super(process,next_ID,lastPO);
	}
	public String getSummary()
	{
		PO po = getPO();
		if (po == null)
			return null;
		StringBuilder sb = new StringBuilder();
		String[] keyColumns = po.get_KeyColumns();
		if ((keyColumns != null) && (keyColumns.length > 0))
			sb.append(Msg.getElement(getCtx(), keyColumns[0])).append(" ");
		int index = po.get_ColumnIndex("DocumentNo");
		if (index != -1)
			sb.append(po.get_Value(index)).append(": ");
		
		index = po.get_ColumnIndex("Description");
		if (index != -1)
			sb.append("").append(po.get_Value(index)).append(": ");
		
		
		
		index = po.get_ColumnIndex("SalesRep_ID");
		Integer sr = null;
		if (index != -1)
			sr = (Integer)po.get_Value(index);
		else
		{
			index = po.get_ColumnIndex("AD_User_ID");
			if (index != -1)
				sr = (Integer)po.get_Value(index);
		}
		if (sr != null)
		{
			MUser user = MUser.get(getCtx(), sr.intValue());
			if (user != null)
				sb.append(user.getName()).append(" ");
		}
		//
		index = po.get_ColumnIndex("C_BPartner_ID");
		if (index != -1)
		{
			Integer bp = (Integer)po.get_Value(index);
			if (bp != null)
			{
				MBPartner partner = MBPartner.get(getCtx(), bp.intValue());
				if (partner != null)
					sb.append(partner.getName()).append(" ");
			}
		}
		return sb.toString();
	}	//	getSummary
	
}
