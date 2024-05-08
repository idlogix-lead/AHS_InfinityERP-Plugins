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
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for ER_DailyProduction
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_ER_DailyProduction extends PO implements I_ER_DailyProduction, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210506L;

    /** Standard Constructor */
    public X_ER_DailyProduction (Properties ctx, int ER_DailyProduction_ID, String trxName)
    {
      super (ctx, ER_DailyProduction_ID, trxName);
      /** if (ER_DailyProduction_ID == 0)
        {
			setDateDoc (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocumentNo (null);
			setER_DailyProduction_ID (0);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_ER_DailyProduction (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_ER_DailyProduction[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Add Daily Production.
		@param AddDailyProduction Add Daily Production	  */
	public void setAddDailyProduction (String AddDailyProduction)
	{
		set_Value (COLUMNNAME_AddDailyProduction, AddDailyProduction);
	}

	/** Get Add Daily Production.
		@return Add Daily Production	  */
	public String getAddDailyProduction () 
	{
		return (String)get_Value(COLUMNNAME_AddDailyProduction);
	}

	/** Set Sales Order Parent Product.
		@param C_Order_ParentProduct_ID Sales Order Parent Product	  */
	public void setC_Order_ParentProduct_ID (int C_Order_ParentProduct_ID)
	{
		if (C_Order_ParentProduct_ID < 1) 
			set_Value (COLUMNNAME_C_Order_ParentProduct_ID, null);
		else 
			set_Value (COLUMNNAME_C_Order_ParentProduct_ID, Integer.valueOf(C_Order_ParentProduct_ID));
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

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
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

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getDocumentNo());
    }

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

	/** Set ER_DailyProduction_UU.
		@param ER_DailyProduction_UU ER_DailyProduction_UU	  */
	public void setER_DailyProduction_UU (String ER_DailyProduction_UU)
	{
		set_Value (COLUMNNAME_ER_DailyProduction_UU, ER_DailyProduction_UU);
	}

	/** Get ER_DailyProduction_UU.
		@return ER_DailyProduction_UU	  */
	public String getER_DailyProduction_UU () 
	{
		return (String)get_Value(COLUMNNAME_ER_DailyProduction_UU);
	}

	public I_ER_MasterProduction getER_MasterProduction() throws RuntimeException
    {
		return (I_ER_MasterProduction)MTable.get(getCtx(), I_ER_MasterProduction.Table_Name)
			.getPO(getER_MasterProduction_ID(), get_TrxName());	}

	/** Set Master Production.
		@param ER_MasterProduction_ID Master Production	  */
	public void setER_MasterProduction_ID (int ER_MasterProduction_ID)
	{
		if (ER_MasterProduction_ID < 1) 
			set_Value (COLUMNNAME_ER_MasterProduction_ID, null);
		else 
			set_Value (COLUMNNAME_ER_MasterProduction_ID, Integer.valueOf(ER_MasterProduction_ID));
	}

	/** Get Master Production.
		@return Master Production	  */
	public int getER_MasterProduction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_MasterProduction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_M_Parent_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Parent_Product_ID, Integer.valueOf(M_Parent_Product_ID));
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

	/** Set Processed On.
		@param ProcessedOn 
		The date+time (expressed in decimal format) when the document has been processed
	  */
	public void setProcessedOn (BigDecimal ProcessedOn)
	{
		set_Value (COLUMNNAME_ProcessedOn, ProcessedOn);
	}

	/** Get Processed On.
		@return The date+time (expressed in decimal format) when the document has been processed
	  */
	public BigDecimal getProcessedOn () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ProcessedOn);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}