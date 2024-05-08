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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for ER_Coupon
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_ER_Coupon extends PO implements I_ER_Coupon, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210329L;

    /** Standard Constructor */
    public X_ER_Coupon (Properties ctx, int ER_Coupon_ID, String trxName)
    {
      super (ctx, ER_Coupon_ID, trxName);
      /** if (ER_Coupon_ID == 0)
        {
			setER_Coupon_ID (0);
        } */
    }

    /** Load Constructor */
    public X_ER_Coupon (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_ER_Coupon[")
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

	/** Set Coupon.
		@param ER_Coupon_ID Coupon	  */
	public void setER_Coupon_ID (int ER_Coupon_ID)
	{
		if (ER_Coupon_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_Coupon_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_Coupon_ID, Integer.valueOf(ER_Coupon_ID));
	}

	/** Get Coupon.
		@return Coupon	  */
	public int getER_Coupon_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_Coupon_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ER_Coupon_UU.
		@param ER_Coupon_UU ER_Coupon_UU	  */
	public void setER_Coupon_UU (String ER_Coupon_UU)
	{
		set_Value (COLUMNNAME_ER_Coupon_UU, ER_Coupon_UU);
	}

	/** Get ER_Coupon_UU.
		@return ER_Coupon_UU	  */
	public String getER_Coupon_UU () 
	{
		return (String)get_Value(COLUMNNAME_ER_Coupon_UU);
	}

	public I_ER_Operation getER_Operation() throws RuntimeException
    {
		return (I_ER_Operation)MTable.get(getCtx(), I_ER_Operation.Table_Name)
			.getPO(getER_Operation_ID(), get_TrxName());	}

	/** Set Operation.
		@param ER_Operation_ID Operation	  */
	public void setER_Operation_ID (int ER_Operation_ID)
	{
		if (ER_Operation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_Operation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_Operation_ID, Integer.valueOf(ER_Operation_ID));
	}

	/** Get Operation.
		@return Operation	  */
	public int getER_Operation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_Operation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Production getM_Production() throws RuntimeException
    {
		return (org.compiere.model.I_M_Production)MTable.get(getCtx(), org.compiere.model.I_M_Production.Table_Name)
			.getPO(getM_Production_ID(), get_TrxName());	}

	/** Set Production.
		@param M_Production_ID 
		Plan for producing a product
	  */
	public void setM_Production_ID (int M_Production_ID)
	{
		if (M_Production_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Production_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Production_ID, Integer.valueOf(M_Production_ID));
	}

	/** Get Production.
		@return Plan for producing a product
	  */
	public int getM_Production_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Production_ID);
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