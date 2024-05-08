package com.idlogix.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MMovement;
import org.compiere.util.DB;

public class MMovementLine extends org.compiere.model.MMovementLine {


	public static final String COLUMNNAME_ER_DailyProduction_ID = "ER_DailyProduction_ID";
	public void setER_DailyProduction_ID (int ER_DailyProduction_ID)
	{
		if (ER_DailyProduction_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_DailyProduction_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_DailyProduction_ID, Integer.valueOf(ER_DailyProduction_ID));
	}

	/** Get Production Line.
		@return Document Line representing a production
	  */
	public int getER_DailyProduction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_DailyProduction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	public static final String COLUMNNAME_M_Production_ID = "M_Production_ID";

	public MMovementLine(MMovement parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	public MMovementLine(Properties ctx, int M_MovementLine_ID, String trxName) {
		super(ctx, M_MovementLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MMovementLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setM_Production_ID (int M_Production_ID)
	{
		if (M_Production_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Production_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Production_ID, Integer.valueOf(M_Production_ID));
	}

	/** Get Production Line.
		@return Document Line representing a production
	  */
	public int getM_Production_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Production_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	
	protected boolean beforeSave (boolean newRecord)
	{
		//Lets check stock.
		
		int ProductID = getM_Product_ID();
		String strSQL = "SELECT Coalesce(SUM(QtyOnHand), 0) From M_StorageOnHand WHERE M_Product_ID = " + ProductID
				+ " AND M_Locator_ID = " + getM_Locator_ID();
		
		BigDecimal qtyOnHand = DB.getSQLValueBD(get_TrxName(), strSQL);
		if (qtyOnHand.compareTo(getMovementQty())== -1)
		{
//			log.saveError("SaveError", "Stock not available");
//			return false;
		}
		return super.beforeSave(newRecord);
	}
}
