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
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for PP_Production_Line
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_PP_Production_Line extends PO implements I_PP_Production_Line, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210423L;

    /** Standard Constructor */
    public X_PP_Production_Line (Properties ctx, int PP_Production_Line_ID, String trxName)
    {
      super (ctx, PP_Production_Line_ID, trxName);
      /** if (PP_Production_Line_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_PP_Production_Line (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_PP_Production_Line[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_Name)
			.getPO(getC_Activity_ID(), get_TrxName());	}

	/** Set Activity.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

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

	/** Set PP_Production_Line_UU.
		@param PP_Production_Line_UU PP_Production_Line_UU	  */
	public void setPP_Production_Line_UU (String PP_Production_Line_UU)
	{
		set_ValueNoCheck (COLUMNNAME_PP_Production_Line_UU, PP_Production_Line_UU);
	}

	/** Get PP_Production_Line_UU.
		@return PP_Production_Line_UU	  */
	public String getPP_Production_Line_UU () 
	{
		return (String)get_Value(COLUMNNAME_PP_Production_Line_UU);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}