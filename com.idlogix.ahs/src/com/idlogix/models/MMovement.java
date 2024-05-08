package com.idlogix.models;

import java.sql.ResultSet;
import java.util.Properties;

public class MMovement extends org.compiere.model.MMovement {

	
	public MMovement(Properties ctx, int M_Movement_ID, String trxName) {
		super(ctx, M_Movement_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MMovement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
    public static final String COLUMNNAME_ER_DailyProduction_ID = "ER_DailyProduction_ID";
	public void setER_DailyProduction_ID (int ER_DailyProduction_ID)
	{
		if (ER_DailyProduction_ID < 1) 
			set_Value (COLUMNNAME_ER_DailyProduction_ID, null);
		else 
			set_Value (COLUMNNAME_ER_DailyProduction_ID, Integer.valueOf(ER_DailyProduction_ID));
	}

	/** Get Daily Production.
		@return Daily Production	  */
	public int getER_DailyProduction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_DailyProduction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String COLUMNNAME_ER_MasterProduction_ID = "ER_MasterProduction_ID";
	public void setER_MasterProduction_ID (int ER_MasterProduction_ID)
	{
		if (ER_MasterProduction_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_MasterProduction_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_MasterProduction_ID, Integer.valueOf(ER_MasterProduction_ID));
	}

	/** Get Master Production.
		@return Master Production	  */
	public int getER_MasterProduction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_MasterProduction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

}
