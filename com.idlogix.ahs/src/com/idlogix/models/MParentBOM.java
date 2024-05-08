package com.idlogix.models;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
//import org.eri.model.I_M_Parent_Bom;
import org.compiere.model.Query;

public class MParentBOM extends com.idlogix.models.X_M_Parent_Bom {

	
	public MParentBOM(Properties ctx, int M_Product_BOM_ID, String trxName) {
		super(ctx, M_Product_BOM_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MParentBOM(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static MParentBOM[] getBOMLines (Properties ctx, int M_Parent_Product_ID, String trxName, int C_Activity_ID)
	{
		final String whereClause = "M_Parent_Product_ID=? and C_Activity_ID=?";
		List <MParentBOM> list = new Query(ctx, I_M_Parent_Bom.Table_Name, whereClause, trxName)
		.setParameters(M_Parent_Product_ID)
		.setOrderBy("Line")
		.list();
 
		MParentBOM[] retValue = new MParentBOM[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getBOMLines

	public static MParentBOM[] getBOMLines (Properties ctx, int M_Parent_Product_ID, String trxName)
	{
 		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		final String whereClause = "M_Parent_Product_ID=?";
		List <MParentBOM> list = new Query(ctx, I_M_Parent_Bom.Table_Name, whereClause, trxName)
		.setParameters(M_Parent_Product_ID)
		.setOrderBy("Line")
		.list();
 
	//	s_log.fine("getBOMLines - #" + list.size() + " - M_Product_ID=" + M_Product_ID);
		MParentBOM[] retValue = new MParentBOM[list.size()];
		list.toArray(retValue);
		
		return retValue;
//		return list;
	}	//	getBOMLines

}