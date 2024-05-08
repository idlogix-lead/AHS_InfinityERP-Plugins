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

/** Generated Model for ER_ProductionLineAttribute
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_ER_ProductionLineAttribute extends PO implements I_ER_ProductionLineAttribute, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20201225L;

    /** Standard Constructor */
    public X_ER_ProductionLineAttribute (Properties ctx, int ER_ProductionLineAttribute_ID, String trxName)
    {
      super (ctx, ER_ProductionLineAttribute_ID, trxName);
      /** if (ER_ProductionLineAttribute_ID == 0)
        {
			setER_MasterProductionLine_ID (0);
			setER_ProductionLineAttribute_ID (0);
			setProcessed (false);
			setQty (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_ER_ProductionLineAttribute (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuilder sb = new StringBuilder ("X_ER_ProductionLineAttribute[")
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

	public I_ER_MasterProductionLine getER_MasterProductionLine() throws RuntimeException
    {
		return (I_ER_MasterProductionLine)MTable.get(getCtx(), I_ER_MasterProductionLine.Table_Name)
			.getPO(getER_MasterProductionLine_ID(), get_TrxName());	}

	/** Set Master Production Line.
		@param ER_MasterProductionLine_ID Master Production Line	  */
	public void setER_MasterProductionLine_ID (int ER_MasterProductionLine_ID)
	{
		if (ER_MasterProductionLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_MasterProductionLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_MasterProductionLine_ID, Integer.valueOf(ER_MasterProductionLine_ID));
	}

	/** Get Master Production Line.
		@return Master Production Line	  */
	public int getER_MasterProductionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_MasterProductionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Production Line Attribute.
		@param ER_ProductionLineAttribute_ID Production Line Attribute	  */
	public void setER_ProductionLineAttribute_ID (int ER_ProductionLineAttribute_ID)
	{
		if (ER_ProductionLineAttribute_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_ProductionLineAttribute_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_ProductionLineAttribute_ID, Integer.valueOf(ER_ProductionLineAttribute_ID));
	}

	/** Get Production Line Attribute.
		@return Production Line Attribute	  */
	public int getER_ProductionLineAttribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_ProductionLineAttribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ER_ProductionLineAttribute_UU.
		@param ER_ProductionLineAttribute_UU ER_ProductionLineAttribute_UU	  */
	public void setER_ProductionLineAttribute_UU (String ER_ProductionLineAttribute_UU)
	{
		set_Value (COLUMNNAME_ER_ProductionLineAttribute_UU, ER_ProductionLineAttribute_UU);
	}

	/** Get ER_ProductionLineAttribute_UU.
		@return ER_ProductionLineAttribute_UU	  */
	public String getER_ProductionLineAttribute_UU () 
	{
		return (String)get_Value(COLUMNNAME_ER_ProductionLineAttribute_UU);
	}

	public org.compiere.model.I_M_AttributeValue getM_AttributeValue() throws RuntimeException
    {
		return (org.compiere.model.I_M_AttributeValue)MTable.get(getCtx(), org.compiere.model.I_M_AttributeValue.Table_Name)
			.getPO(getM_AttributeValue_ID(), get_TrxName());	}

	/** Set Attribute Value.
		@param M_AttributeValue_ID 
		Product Attribute Value
	  */
	public void setM_AttributeValue_ID (int M_AttributeValue_ID)
	{
		if (M_AttributeValue_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeValue_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeValue_ID, Integer.valueOf(M_AttributeValue_ID));
	}

	/** Get Attribute Value.
		@return Product Attribute Value
	  */
	public int getM_AttributeValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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