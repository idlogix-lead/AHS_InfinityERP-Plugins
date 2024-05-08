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
import org.compiere.util.KeyNamePair;

/** Generated Model for ER_LaborActivityLine
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_ER_LaborActivityLine extends PO implements I_ER_LaborActivityLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210329L;

    /** Standard Constructor */
    public X_ER_LaborActivityLine (Properties ctx, int ER_LaborActivityLine_ID, String trxName)
    {
      super (ctx, ER_LaborActivityLine_ID, trxName);
      /** if (ER_LaborActivityLine_ID == 0)
        {
			setER_LaborActivity_ID (0);
			setER_LaborActivityLine_ID (0);
			setLine (0);
// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM M_RequisitionLine WHERE M_Requisition_ID=@M_Requisition_ID@
			setQty (Env.ZERO);
// 1
        } */
    }

    /** Load Constructor */
    public X_ER_LaborActivityLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_ER_LaborActivityLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_UOM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_ER_Coupon getER_Coupon() throws RuntimeException
    {
		return (I_ER_Coupon)MTable.get(getCtx(), I_ER_Coupon.Table_Name)
			.getPO(getER_Coupon_ID(), get_TrxName());	}

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

	public I_ER_LaborActivity getER_LaborActivity() throws RuntimeException
    {
		return (I_ER_LaborActivity)MTable.get(getCtx(), I_ER_LaborActivity.Table_Name)
			.getPO(getER_LaborActivity_ID(), get_TrxName());	}

	/** Set Labor Activity.
		@param ER_LaborActivity_ID Labor Activity	  */
	public void setER_LaborActivity_ID (int ER_LaborActivity_ID)
	{
		if (ER_LaborActivity_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_LaborActivity_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_LaborActivity_ID, Integer.valueOf(ER_LaborActivity_ID));
	}

	/** Get Labor Activity.
		@return Labor Activity	  */
	public int getER_LaborActivity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_LaborActivity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Labor Activity Line.
		@param ER_LaborActivityLine_ID Labor Activity Line	  */
	public void setER_LaborActivityLine_ID (int ER_LaborActivityLine_ID)
	{
		if (ER_LaborActivityLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_LaborActivityLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_LaborActivityLine_ID, Integer.valueOf(ER_LaborActivityLine_ID));
	}

	/** Get Labor Activity Line.
		@return Labor Activity Line	  */
	public int getER_LaborActivityLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_LaborActivityLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ER_LaborActivityLine_UU.
		@param ER_LaborActivityLine_UU ER_LaborActivityLine_UU	  */
	public void setER_LaborActivityLine_UU (String ER_LaborActivityLine_UU)
	{
		set_Value (COLUMNNAME_ER_LaborActivityLine_UU, ER_LaborActivityLine_UU);
	}

	/** Get ER_LaborActivityLine_UU.
		@return ER_LaborActivityLine_UU	  */
	public String getER_LaborActivityLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_ER_LaborActivityLine_UU);
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getLine()));
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