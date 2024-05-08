package com.idlogix.models;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;

public class MProduct extends org.compiere.model.MProduct {


	public MProduct(Properties ctx, int M_Product_ID, String trxName) {
		super(ctx, M_Product_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MProduct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    public static final String COLUMNNAME_ER_Article_ID = "ER_Article_ID";
    public static final String COLUMNNAME_ER_Color_ID = "ER_Color_ID";
    public static final String COLUMNNAME_ER_Size_ID = "ER_Size_ID";
    public static final String COLUMNNAME_M_Parent_Product_ID = "M_Parent_Product_ID";

    
	public void setER_Article_ID (int ER_Article_ID)
	{
		if (ER_Article_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_Article_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_Article_ID, Integer.valueOf(ER_Article_ID));
	}

	public int getER_Article_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_Article_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public void setER_Color_ID (int ER_Color_ID)
	{
		if (ER_Color_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_Color_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_Color_ID, Integer.valueOf(ER_Color_ID));
	}

	public int getER_Color_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_Color_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public void setER_Size_ID (int ER_Size_ID)
	{
		if (ER_Size_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_Size_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_Size_ID, Integer.valueOf(ER_Size_ID));
	}

	public int getER_Size_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_Size_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public void setM_Parent_Product_ID (int M_Parent_Product_ID)
	{
		if (M_Parent_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Parent_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Parent_Product_ID, Integer.valueOf(M_Parent_Product_ID));
	}

	public int getM_Parent_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Parent_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public int bomLines()
	{
		int materials = DB.getSQLValue(get_TrxName(), "SELECT count(M_Product_BOM_ID) FROM M_Product_BOM WHERE M_Product_ID = ?", get_ID());
		return materials;
	}
    
}
