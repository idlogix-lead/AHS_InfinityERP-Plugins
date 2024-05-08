package com.idlogix.models;

import java.sql.ResultSet;
import java.util.Properties;

public class MCoupon extends X_ER_Coupon {

	public MCoupon(Properties ctx, int ER_Coupon_ID, String trxName) {
		super(ctx, ER_Coupon_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MCoupon(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
