/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package com.idlogix.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;

/** Generated Model for ER_Lasting_Plan
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_ER_Lasting_Plan extends PO implements I_ER_Lasting_Plan, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210423L;

    /** Standard Constructor */
    public X_ER_Lasting_Plan (Properties ctx, int ER_Lasting_Plan_ID, String trxName)
    {
      super (ctx, ER_Lasting_Plan_ID, trxName);
      /** if (ER_Lasting_Plan_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_ER_Lasting_Plan (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_ER_Lasting_Plan[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public I_ER_DailyProduction getER_DailyProduction() throws RuntimeException
    {
		return (I_ER_DailyProduction)MTable.get(getCtx(), I_ER_DailyProduction.Table_Name)
			.getPO(getER_DailyProduction_ID(), get_TrxName());	}

	/** Set Daily Production.
		@param ER_DailyProduction_ID Daily Production	  */
	public void setER_DailyProduction_ID (int ER_DailyProduction_ID)
	{
		if (ER_DailyProduction_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_DailyProduction_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_DailyProduction_ID, Integer.valueOf(ER_DailyProduction_ID));
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

	/** Set Lasting Plan.
		@param ER_Lasting_Plan_ID Lasting Plan	  */
	public void setER_Lasting_Plan_ID (int ER_Lasting_Plan_ID)
	{
		if (ER_Lasting_Plan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_Lasting_Plan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_Lasting_Plan_ID, Integer.valueOf(ER_Lasting_Plan_ID));
	}

	/** Get Lasting Plan.
		@return Lasting Plan	  */
	public int getER_Lasting_Plan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_Lasting_Plan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ER_Lasting_Plan_UU.
		@param ER_Lasting_Plan_UU ER_Lasting_Plan_UU	  */
	public void setER_Lasting_Plan_UU (String ER_Lasting_Plan_UU)
	{
		set_ValueNoCheck (COLUMNNAME_ER_Lasting_Plan_UU, ER_Lasting_Plan_UU);
	}

	/** Get ER_Lasting_Plan_UU.
		@return ER_Lasting_Plan_UU	  */
	public String getER_Lasting_Plan_UU () 
	{
		return (String)get_Value(COLUMNNAME_ER_Lasting_Plan_UU);
	}

	/** Set Plan Date.
		@param PlanDate 
		Plan Date for a record
	  */
	public void setPlanDate (Timestamp PlanDate)
	{
		set_Value (COLUMNNAME_PlanDate, PlanDate);
	}

	/** Get Plan Date.
		@return Plan Date for a record
	  */
	public Timestamp getPlanDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PlanDate);
	}

	public I_PP_Production_Line getPP_Production_Line() throws RuntimeException
    {
		return (I_PP_Production_Line)MTable.get(getCtx(), I_PP_Production_Line.Table_Name)
			.getPO(getPP_Production_Line_ID(), get_TrxName());	}

	/** Set Production Floor Line.
		@param PP_Production_Line_ID Production Floor Line	  */
	public void setPP_Production_Line_ID (int PP_Production_Line_ID)
	{
		if (PP_Production_Line_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_Production_Line_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Production_Line_ID, Integer.valueOf(PP_Production_Line_ID));
	}

	/** Get Production Floor Line.
		@return Production Floor Line	  */
	public int getPP_Production_Line_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Production_Line_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}