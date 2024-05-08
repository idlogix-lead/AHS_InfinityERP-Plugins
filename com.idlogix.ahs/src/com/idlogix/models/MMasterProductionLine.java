package com.idlogix.models;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MTable;

public class MMasterProductionLine extends X_ER_MasterProductionLine {

	public MMasterProductionLine(Properties ctx, int ER_MasterProductionLine_ID, String trxName) {
		super(ctx, ER_MasterProductionLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMasterProductionLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MProduct getM_Product() throws RuntimeException
    {
		return (MProduct)MTable.get(getCtx(), MProduct.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}
	
}
