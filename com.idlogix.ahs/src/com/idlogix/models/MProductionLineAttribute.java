package com.idlogix.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MProductionLineAttribute extends X_ER_ProductionLineAttribute {

	public MProductionLineAttribute(Properties ctx, int ER_ProductionLineAttribute_ID, String trxName) {
		super(ctx, ER_ProductionLineAttribute_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MProductionLineAttribute(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected boolean afterSave (boolean newRecord, boolean success) {
		BigDecimal totalQty = Env.ZERO;
		MMasterProductionLine pline = (MMasterProductionLine)getER_MasterProductionLine();
		String strSQL = "SELECT Coalesce(SUM(Qty), 0) FROM " + get_TableName() + " WHERE " + COLUMNNAME_ER_MasterProductionLine_ID + " = " + getER_MasterProductionLine_ID();
		totalQty = DB.getSQLValueBD(get_TrxName(), strSQL);
		pline.setQty(totalQty);
		pline.save();
		return super.afterSave(newRecord, success);
	}
}
