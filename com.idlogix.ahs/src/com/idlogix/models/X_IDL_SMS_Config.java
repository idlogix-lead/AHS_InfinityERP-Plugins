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
import org.compiere.model.*;

/** Generated Model for IDL_SMS_Config
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_IDL_SMS_Config extends PO implements I_IDL_SMS_Config, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220831L;

    /** Standard Constructor */
    public X_IDL_SMS_Config (Properties ctx, int IDL_SMS_Config_ID, String trxName)
    {
      super (ctx, IDL_SMS_Config_ID, trxName);
      /** if (IDL_SMS_Config_ID == 0)
        {
			setIDL_SMS_Config_ID (0);
			setsenderid (null);
        } */
    }

    /** Load Constructor */
    public X_IDL_SMS_Config (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_IDL_SMS_Config[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set IDL_SMS_Config.
		@param IDL_SMS_Config_ID IDL_SMS_Config	  */
	public void setIDL_SMS_Config_ID (int IDL_SMS_Config_ID)
	{
		if (IDL_SMS_Config_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_IDL_SMS_Config_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_IDL_SMS_Config_ID, Integer.valueOf(IDL_SMS_Config_ID));
	}

	/** Get IDL_SMS_Config.
		@return IDL_SMS_Config	  */
	public int getIDL_SMS_Config_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IDL_SMS_Config_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IDL_SMS_Config_UU.
		@param IDL_SMS_Config_UU IDL_SMS_Config_UU	  */
	public void setIDL_SMS_Config_UU (String IDL_SMS_Config_UU)
	{
		set_ValueNoCheck (COLUMNNAME_IDL_SMS_Config_UU, IDL_SMS_Config_UU);
	}

	/** Get IDL_SMS_Config_UU.
		@return IDL_SMS_Config_UU	  */
	public String getIDL_SMS_Config_UU () 
	{
		return (String)get_Value(COLUMNNAME_IDL_SMS_Config_UU);
	}

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
	}

	/** Set senderid.
		@param senderid senderid	  */
	public void setsenderid (String senderid)
	{
		set_Value (COLUMNNAME_senderid, senderid);
	}

	/** Get senderid.
		@return senderid	  */
	public String getsenderid () 
	{
		return (String)get_Value(COLUMNNAME_senderid);
	}

	/** Set User Name.
		@param UserName User Name	  */
	public void setUserName (String UserName)
	{
		set_Value (COLUMNNAME_UserName, UserName);
	}

	/** Get User Name.
		@return User Name	  */
	public String getUserName () 
	{
		return (String)get_Value(COLUMNNAME_UserName);
	}
}