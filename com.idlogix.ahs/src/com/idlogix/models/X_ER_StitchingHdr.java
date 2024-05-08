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

/** Generated Model for ER_StitchingHdr
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_ER_StitchingHdr extends PO implements I_ER_StitchingHdr, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220423L;

    /** Standard Constructor */
    public X_ER_StitchingHdr (Properties ctx, int ER_StitchingHdr_ID, String trxName)
    {
      super (ctx, ER_StitchingHdr_ID, trxName);
      /** if (ER_StitchingHdr_ID == 0)
        {
			setER_StitchingHdr_ID (0);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_ER_StitchingHdr (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_ER_StitchingHdr[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Add Production.
		@param AddProduction Add Production	  */
	public void setAddProduction (String AddProduction)
	{
		set_Value (COLUMNNAME_AddProduction, AddProduction);
	}

	/** Get Add Production.
		@return Add Production	  */
	public String getAddProduction () 
	{
		return (String)get_Value(COLUMNNAME_AddProduction);
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

	public I_ER_DailyProduction getER_DailyProduction() throws RuntimeException
    {
		return (I_ER_DailyProduction)MTable.get(getCtx(), I_ER_DailyProduction.Table_Name)
			.getPO(getER_DailyProduction_ID(), get_TrxName());	}

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

	/** Set Stitching.
		@param ER_StitchingHdr_ID Stitching	  */
	public void setER_StitchingHdr_ID (int ER_StitchingHdr_ID)
	{
		if (ER_StitchingHdr_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_StitchingHdr_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_StitchingHdr_ID, Integer.valueOf(ER_StitchingHdr_ID));
	}

	/** Get Stitching.
		@return Stitching	  */
	public int getER_StitchingHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_StitchingHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ER_StitchingHdr_UU.
		@param ER_StitchingHdr_UU ER_StitchingHdr_UU	  */
	public void setER_StitchingHdr_UU (String ER_StitchingHdr_UU)
	{
		set_ValueNoCheck (COLUMNNAME_ER_StitchingHdr_UU, ER_StitchingHdr_UU);
	}

	/** Get ER_StitchingHdr_UU.
		@return ER_StitchingHdr_UU	  */
	public String getER_StitchingHdr_UU () 
	{
		return (String)get_Value(COLUMNNAME_ER_StitchingHdr_UU);
	}

	/** Set Movement Date.
		@param MovementDate 
		Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate)
	{
		set_ValueNoCheck (COLUMNNAME_MovementDate, MovementDate);
	}

	/** Get Movement Date.
		@return Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MovementDate);
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
}