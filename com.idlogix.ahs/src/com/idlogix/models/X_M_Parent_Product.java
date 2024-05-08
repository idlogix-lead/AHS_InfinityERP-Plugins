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

/** Generated Model for M_Parent_Product
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_M_Parent_Product extends PO implements I_M_Parent_Product, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20201221L;

    /** Standard Constructor */
    public X_M_Parent_Product (Properties ctx, int M_Parent_Product_ID, String trxName)
    {
      super (ctx, M_Parent_Product_ID, trxName);
      /** if (M_Parent_Product_ID == 0)
        {
			setC_BPartner_ID (0);
			setM_Parent_Product_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_M_Parent_Product (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_M_Parent_Product[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
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

	/** Set M_Parent_Product_UU.
		@param M_Parent_Product_UU M_Parent_Product_UU	  */
	public void setM_Parent_Product_UU (String M_Parent_Product_UU)
	{
		set_ValueNoCheck (COLUMNNAME_M_Parent_Product_UU, M_Parent_Product_UU);
	}

	/** Get M_Parent_Product_UU.
		@return M_Parent_Product_UU	  */
	public String getM_Parent_Product_UU () 
	{
		return (String)get_Value(COLUMNNAME_M_Parent_Product_UU);
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
	public static MProduct getChildProd(int ppID ,int sizeID) {
		MProduct obj = null;
		String strSQL = "select m_product_id::integer from adempiere.m_product\n"
				+ "where m_parent_product_id = "+ ppID+" and er_size_id = "+sizeID  ;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;	
		 try
		 {
		 pstmt = DB.prepareStatement (strSQL.toString(), null);
		 rs = pstmt.executeQuery ();
			 rs.next();
			  int prodID = rs.getInt("m_product_id");
			  obj = new MProduct(Env.getCtx(), prodID, null);
			  
			 
		 }
		 catch (Exception e)
		 {
		 	throw new AdempiereException(e);
		 }
		return obj;
	}
	public static List<Integer> getChildProdIds(int parentId){
		List<Integer> list = new ArrayList<Integer>();
		String strSQL = "select m_product_id::integer "
				+ "from adempiere.m_product \n"
				+ "where M_Parent_Product_ID = "+parentId;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement (strSQL.toString(), null);
			rs = pstmt.executeQuery ();
			
			while (rs.next ())	
			{
				list.add(rs.getInt("m_product_id"));
			}
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		return list;
	}
	public static Map<Integer,Integer> prodToSizeMapper(int parentId){
		Map<Integer,Integer> mapper = new HashMap<Integer,Integer>();
		String strSQL = "select er_size_id::integer,idl_assortment_id::integer\n"
						+ "from adempiere.m_product\n"
						+ "where m_parent_product_id =" + parentId ;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;	
		 try
		 {
		 pstmt = DB.prepareStatement (strSQL.toString(), null);
		 rs = pstmt.executeQuery ();
			 while(rs.next()) {
				 
			  int assortID = rs.getInt("idl_assortment_id");
			  int sizeID = rs.getInt("er_size_id");
			  mapper.put(sizeID,assortID);
			 }
		 }
		 catch (Exception e)
		 {
		 	throw new AdempiereException(e);
		 }
		
		return mapper;
		
	}
	public static Map<Integer,Integer> getChildProdIds(int parentId,int assortId){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		String strSQL = "select m_product_id::integer,er_size_id::integer \n"
				+ "from adempiere.m_product \n"
				+ "where M_Parent_Product_ID = "+parentId+"\n"
				+ "and idl_assortment_id = "+assortId+"\n"
				+ "and isActive = 'Y' ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement (strSQL.toString(), null);
			rs = pstmt.executeQuery ();
			
			while (rs.next ())	
			{
				map.put(rs.getInt("m_product_id"),rs.getInt("er_size_id"));
			}
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		return map;
	}
}