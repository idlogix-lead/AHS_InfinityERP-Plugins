package com.idlogix.models;

import java.sql.ResultSet;
import java.util.Properties;

public class MOrderCustom extends X_ER_Coupon {

	public MOrderCustom(Properties ctx, int ER_Coupon_ID, String trxName) {
		super(ctx, ER_Coupon_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MOrderCustom(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
