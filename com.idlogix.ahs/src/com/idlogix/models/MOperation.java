package com.idlogix.models;

import java.sql.ResultSet;
import java.util.Properties;

public class MOperation extends X_ER_Operation {
	
	public MOperation(Properties ctx, int ER_Operation_ID, String trxName) {
		super(ctx, ER_Operation_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MOperation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
