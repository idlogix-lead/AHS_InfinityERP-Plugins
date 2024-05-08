package com.idlogix.models;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MLaborActivity extends X_ER_LaborActivity {

	public MLaborActivity(Properties ctx, int ER_LaborActivity_ID, String trxName) {
		super(ctx, ER_LaborActivity_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MLaborActivity(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<MLaborActivityLine> getLines (boolean requery)
	{
		final String whereClause = "ER_LaborActivity_ID=?";
		List<MLaborActivityLine> list = new Query(getCtx(), MLaborActivityLine.Table_Name, whereClause, get_TrxName())
		 										.setParameters(getER_LaborActivity_ID())
		 										.list();
		return list;
	}	//	getLines

	
}
