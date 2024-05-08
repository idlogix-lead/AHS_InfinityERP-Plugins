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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for C_Order_ParentProduct
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_C_Order_ParentProduct extends PO implements I_C_Order_ParentProduct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220909L;

    /** Standard Constructor */
    public X_C_Order_ParentProduct (Properties ctx, int C_Order_ParentProduct_ID, String trxName)
    {
      super (ctx, C_Order_ParentProduct_ID, trxName);
      /** if (C_Order_ParentProduct_ID == 0)
        {
			setC_Order_ID (0);
			setC_Order_ParentProduct_ID (0);
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
// @SQL=SELECT DateOrdered AS DefaultValue FROM C_Order WHERE C_Order_ID=@C_Order_ID@
			setDatePromised (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setM_Parent_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_Order_ParentProduct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_C_Order_ParentProduct[")
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

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Order Parent Product.
		@param C_Order_ParentProduct_ID Sales Order Parent Product	  */
	public void setC_Order_ParentProduct_ID (int C_Order_ParentProduct_ID)
	{
		if (C_Order_ParentProduct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Order_ParentProduct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Order_ParentProduct_ID, Integer.valueOf(C_Order_ParentProduct_ID));
	}

	/** Get Sales Order Parent Product.
		@return Sales Order Parent Product	  */
	public int getC_Order_ParentProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ParentProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Order_ParentProduct_UU.
		@param C_Order_ParentProduct_UU C_Order_ParentProduct_UU	  */
	public void setC_Order_ParentProduct_UU (String C_Order_ParentProduct_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_Order_ParentProduct_UU, C_Order_ParentProduct_UU);
	}

	/** Get C_Order_ParentProduct_UU.
		@return C_Order_ParentProduct_UU	  */
	public String getC_Order_ParentProduct_UU () 
	{
		return (String)get_Value(COLUMNNAME_C_Order_ParentProduct_UU);
	}

	/** Set Create Order Lines.
		@param CreateOrderLines 
		Create Order Lines (Custom) Field
	  */
	public void setCreateOrderLines (String CreateOrderLines)
	{
		set_Value (COLUMNNAME_CreateOrderLines, CreateOrderLines);
	}

	/** Get Create Order Lines.
		@return Create Order Lines (Custom) Field
	  */
	public String getCreateOrderLines () 
	{
		return (String)get_Value(COLUMNNAME_CreateOrderLines);
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		set_ValueNoCheck (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Date Promised.
		@param DatePromised 
		Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised)
	{
		set_ValueNoCheck (COLUMNNAME_DatePromised, DatePromised);
	}

	/** Get Date Promised.
		@return Date Order was promised
	  */
	public Timestamp getDatePromised () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DatePromised);
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

	/** Set Expected Delivery Date.
		@param Exp_Deliver_Date 
		Expected Delivery Date of Sales Order
	  */
	public void setExp_Deliver_Date (Timestamp Exp_Deliver_Date)
	{
		set_Value (COLUMNNAME_Exp_Deliver_Date, Exp_Deliver_Date);
	}

	/** Get Expected Delivery Date.
		@return Expected Delivery Date of Sales Order
	  */
	public Timestamp getExp_Deliver_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Exp_Deliver_Date);
	}

	public I_M_Parent_Product getM_Parent_Product() throws RuntimeException
    {
		return (I_M_Parent_Product)MTable.get(getCtx(), I_M_Parent_Product.Table_Name)
			.getPO(getM_Parent_Product_ID(), get_TrxName());	}

	/** Set Parent Product.
		@param M_Parent_Product_ID Parent Product	  */
	public void setM_Parent_Product_ID (int M_Parent_Product_ID)
	{
		if (M_Parent_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Parent_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Parent_Product_ID, Integer.valueOf(M_Parent_Product_ID));
	}

	/** Get Parent Product.
		@return Parent Product	  */
	public int getM_Parent_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Parent_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Reference.
		@param POReference 
		Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public void setPOReference (String POReference)
	{
		set_ValueNoCheck (COLUMNNAME_POReference, POReference);
	}

	/** Get Order Reference.
		@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference () 
	{
		return (String)get_Value(COLUMNNAME_POReference);
	}

	/** Set strobelno.
		@param strobelno strobelno	  */
	public void setstrobelno (String strobelno)
	{
		set_Value (COLUMNNAME_strobelno, strobelno);
	}

	/** Get strobelno.
		@return strobelno	  */
	public String getstrobelno () 
	{
		return (String)get_Value(COLUMNNAME_strobelno);
	}
}