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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.util.DB;
import org.compiere.util.Env;

/** Generated Model for IDL_Assortment_Line
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_IDL_Assortment_Line extends PO implements I_IDL_Assortment_Line, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220604L;

    /** Standard Constructor */
    public X_IDL_Assortment_Line (Properties ctx, int IDL_Assortment_Line_ID, String trxName)
    {
      super (ctx, IDL_Assortment_Line_ID, trxName);
      /** if (IDL_Assortment_Line_ID == 0)
        {
			setIDL_Assortment_Line_ID (0);
        } */
    }

    /** Load Constructor */
    public X_IDL_Assortment_Line (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_IDL_Assortment_Line[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
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

	/** Set Size.
		@param ER_Size_ID Size	  */
	public void setER_Size_ID (int ER_Size_ID)
	{
		if (ER_Size_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_Size_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_Size_ID, Integer.valueOf(ER_Size_ID));
	}

	/** Get Size.
		@return Size	  */
	public int getER_Size_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_Size_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_IDL_Assortment getIDL_Assortment() throws RuntimeException
    {
		return (I_IDL_Assortment)MTable.get(getCtx(), I_IDL_Assortment.Table_Name)
			.getPO(getIDL_Assortment_ID(), get_TrxName());	}

	/** Set IDL_Assortment.
		@param IDL_Assortment_ID IDL_Assortment	  */
	public void setIDL_Assortment_ID (int IDL_Assortment_ID)
	{
		if (IDL_Assortment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_IDL_Assortment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_IDL_Assortment_ID, Integer.valueOf(IDL_Assortment_ID));
	}

	/** Get IDL_Assortment.
		@return IDL_Assortment	  */
	public int getIDL_Assortment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IDL_Assortment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IDL_Assortment_Line.
		@param IDL_Assortment_Line_ID IDL_Assortment_Line	  */
	public void setIDL_Assortment_Line_ID (int IDL_Assortment_Line_ID)
	{
		if (IDL_Assortment_Line_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_IDL_Assortment_Line_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_IDL_Assortment_Line_ID, Integer.valueOf(IDL_Assortment_Line_ID));
	}

	/** Get IDL_Assortment_Line.
		@return IDL_Assortment_Line	  */
	public int getIDL_Assortment_Line_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IDL_Assortment_Line_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IDL_Assortment_Line_UU.
		@param IDL_Assortment_Line_UU IDL_Assortment_Line_UU	  */
	public void setIDL_Assortment_Line_UU (String IDL_Assortment_Line_UU)
	{
		set_ValueNoCheck (COLUMNNAME_IDL_Assortment_Line_UU, IDL_Assortment_Line_UU);
	}

	/** Get IDL_Assortment_Line_UU.
		@return IDL_Assortment_Line_UU	  */
	public String getIDL_Assortment_Line_UU () 
	{
		return (String)get_Value(COLUMNNAME_IDL_Assortment_Line_UU);
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
	public static List<X_IDL_Assortment_Line> getAssortmentLines(int assortmentID){
		List<X_IDL_Assortment_Line> list = new ArrayList<X_IDL_Assortment_Line>();
		String strSQL = "select idl_assortment_line_id::integer from adempiere.idl_assortment_line\n"
				+ "where idl_assortment_id = " + assortmentID ;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;	
		 try
		 {
		 pstmt = DB.prepareStatement (strSQL.toString(), null);
		 rs = pstmt.executeQuery ();
			 while(rs.next()) {
			  int lineID = rs.getInt("idl_assortment_line_id");
			  X_IDL_Assortment_Line obj = new X_IDL_Assortment_Line(Env.getCtx(), lineID, null);
			  list.add(obj);
			 }
		 }
		 catch (Exception e)
		 {
		 	throw new AdempiereException(e);
		 }
		
		return list;
		
	}
	public static Map<Integer,Integer> sizeToQtyMapper(int assortmentID){
		Map mapper = new HashMap<Integer,Integer>();
		List<X_IDL_Assortment_Line> list = new ArrayList<X_IDL_Assortment_Line>();
		String strSQL = "select er_size_id::integer,qty::integer from adempiere.idl_assortment_line\n"
				+ "where idl_assortment_id = " + assortmentID ;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;	
		 try
		 {
		 pstmt = DB.prepareStatement (strSQL.toString(), null);
		 rs = pstmt.executeQuery ();
			 while(rs.next()) {
			  int sizeID = rs.getInt("er_size_id");
			  int qty = rs.getInt("qty");
			  mapper.put(sizeID, qty);
			 }
		 }
		 catch (Exception e)
		 {
		 	throw new AdempiereException(e);
		 }
		
		return mapper;
		
	}
}