package com.idlogix.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MOrderLine;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MOrderLineAttribute extends X_ER_OrderLineAttribute {

	
	public MOrderLineAttribute(Properties ctx, int ER_OrderLineAttribute_ID, String trxName) {
		super(ctx, ER_OrderLineAttribute_ID, trxName);
	}
	public MOrderLineAttribute(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private static final long serialVersionUID = 1L;

	
	protected boolean afterSave (boolean newRecord, boolean success) {
		
		
		BigDecimal totalQty = Env.ZERO;
		
		MOrderLine oline = (MOrderLine)getC_OrderLine();

		String strSQL = "SELECT Coalesce(SUM(Qty), 0) FROM " + get_TableName() + " WHERE C_OrderLine_ID = " + getC_OrderLine_ID();
		
		totalQty = DB.getSQLValueBD(get_TrxName(), strSQL);
		
		oline.setDescription("test");
		oline.setQtyEntered(totalQty);
		oline.save();
		oline.getC_OrderLine_ID();
		return super.afterSave(newRecord, success);
	}
}
