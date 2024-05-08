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

/** Generated Model for IDL_SMS
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_IDL_SMS extends PO implements I_IDL_SMS, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220831L;

    /** Standard Constructor */
    public X_IDL_SMS (Properties ctx, int IDL_SMS_ID, String trxName)
    {
      super (ctx, IDL_SMS_ID, trxName);
      /** if (IDL_SMS_ID == 0)
        {
			setdocumentname (null);
			setIDL_SMS_ID (0);
        } */
    }

    /** Load Constructor */
    public X_IDL_SMS (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_IDL_SMS[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set documentname.
		@param documentname documentname	  */
	public void setdocumentname (String documentname)
	{
		set_Value (COLUMNNAME_documentname, documentname);
	}

	/** Get documentname.
		@return documentname	  */
	public String getdocumentname () 
	{
		return (String)get_Value(COLUMNNAME_documentname);
	}

	/** Set errormessage.
		@param errormessage errormessage	  */
	public void seterrormessage (String errormessage)
	{
		set_Value (COLUMNNAME_errormessage, errormessage);
	}

	/** Get errormessage.
		@return errormessage	  */
	public String geterrormessage () 
	{
		return (String)get_Value(COLUMNNAME_errormessage);
	}

	/** Set IDL_SMS.
		@param IDL_SMS_ID IDL_SMS	  */
	public void setIDL_SMS_ID (int IDL_SMS_ID)
	{
		if (IDL_SMS_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_IDL_SMS_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_IDL_SMS_ID, Integer.valueOf(IDL_SMS_ID));
	}

	/** Get IDL_SMS.
		@return IDL_SMS	  */
	public int getIDL_SMS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IDL_SMS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IDL_SMS_UU.
		@param IDL_SMS_UU IDL_SMS_UU	  */
	public void setIDL_SMS_UU (String IDL_SMS_UU)
	{
		set_ValueNoCheck (COLUMNNAME_IDL_SMS_UU, IDL_SMS_UU);
	}

	/** Get IDL_SMS_UU.
		@return IDL_SMS_UU	  */
	public String getIDL_SMS_UU () 
	{
		return (String)get_Value(COLUMNNAME_IDL_SMS_UU);
	}

	/** Set issent.
		@param issent issent	  */
	public void setissent (boolean issent)
	{
		set_Value (COLUMNNAME_issent, Boolean.valueOf(issent));
	}

	/** Get issent.
		@return issent	  */
	public boolean issent () 
	{
		Object oo = get_Value(COLUMNNAME_issent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set reciever_no.
		@param reciever_no reciever_no	  */
	public void setreciever_no (String reciever_no)
	{
		set_Value (COLUMNNAME_reciever_no, reciever_no);
	}

	/** Get reciever_no.
		@return reciever_no	  */
	public String getreciever_no () 
	{
		return (String)get_Value(COLUMNNAME_reciever_no);
	}

	/** Set responsecode.
		@param responsecode responsecode	  */
	public void setresponsecode (String responsecode)
	{
		set_Value (COLUMNNAME_responsecode, responsecode);
	}

	/** Get responsecode.
		@return responsecode	  */
	public String getresponsecode () 
	{
		return (String)get_Value(COLUMNNAME_responsecode);
	}

	/** Set smsbody.
		@param smsbody smsbody	  */
	public void setsmsbody (String smsbody)
	{
		set_Value (COLUMNNAME_smsbody, smsbody);
	}

	/** Get smsbody.
		@return smsbody	  */
	public String getsmsbody () 
	{
		return (String)get_Value(COLUMNNAME_smsbody);
	}
}