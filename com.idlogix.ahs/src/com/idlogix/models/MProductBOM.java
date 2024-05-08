package com.idlogix.models;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.I_M_Product_BOM;
import org.compiere.model.Query;

public class MProductBOM extends org.compiere.model.MProductBOM {

	
	public MProductBOM(Properties ctx, int M_Product_BOM_ID, String trxName) {
		super(ctx, M_Product_BOM_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MProductBOM(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static MProductBOM[] getBOMLines (Properties ctx, int M_Product_ID, String trxName, int C_Activity_ID, int RMID)
	{
		final String whereClause = "M_Product_ID=? AND C_Activity_ID=? AND M_ProductBOM_ID=?";
		
		List <MProductBOM> list = new Query(ctx, I_M_Product_BOM.Table_Name, whereClause, trxName)
		.setParameters(M_Product_ID, C_Activity_ID, RMID)
		.setOrderBy("Line")
		.list();
 
		MProductBOM[] retValue = new MProductBOM[list.size()];
		list.toArray(retValue);
		return retValue;
	}	
	
	public static MProductBOM[] getBOMLines (Properties ctx, int M_Product_ID, String trxName, int C_Activity_ID)
	{
		final String whereClause = "M_Product_ID=? AND C_Activity_ID=?";
		
		List <MProductBOM> list = new Query(ctx, I_M_Product_BOM.Table_Name, whereClause, trxName)
		.setParameters(M_Product_ID, C_Activity_ID)
		.setOrderBy("Line")
		.list();
 
		MProductBOM[] retValue = new MProductBOM[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getBOMLines

	public static MProductBOM[] getBOMLines (Properties ctx, int M_Product_ID, String trxName)
	{
 		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		final String whereClause = "M_Product_ID=?";
		List <MProductBOM> list = new Query(ctx, I_M_Product_BOM.Table_Name, whereClause, trxName)
		.setParameters(M_Product_ID)
		.setOrderBy("Line")
		.list();
 
	//	s_log.fine("getBOMLines - #" + list.size() + " - M_Product_ID=" + M_Product_ID);
		MProductBOM[] retValue = new MProductBOM[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getBOMLines

}
