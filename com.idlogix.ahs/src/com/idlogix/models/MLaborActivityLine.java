package com.idlogix.models;

import java.sql.ResultSet;
import java.util.Properties;

public class MLaborActivityLine extends X_ER_LaborActivityLine {

	public MLaborActivityLine(Properties ctx, int ER_LaborActivityLine_ID, String trxName) {
		super(ctx, ER_LaborActivityLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MLaborActivityLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
