package com.idlogix.models;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Util;
import org.eri.model.MProduction;



public class MDailyProduction extends X_ER_DailyProduction implements DocAction{


	public MDailyProduction(Properties ctx, int ER_DailyProduction_ID, String trxName) {
		super(ctx, ER_DailyProduction_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MDailyProduction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean processIt(String action) throws Exception {
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		return true;
	}

	@Override
	public boolean invalidateIt() {
		return false;
	}

	@Override
	public String prepareIt() {
		return DocAction.STATUS_InProgress;
	}

	@Override
	public boolean approveIt() {
		return true;
	}

	@Override
	public boolean rejectIt() {
		return true;
	}

	public MProduction[] getLines() {
		ArrayList<MProduction> list = new ArrayList<MProduction>();
		
		String sql = "SELECT p.M_Production_ID "
			+ "FROM M_Production p "
			+ "WHERE p.ER_DailyProduction_ID = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, get_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add( new MProduction( getCtx(), rs.getInt(1), get_TrxName() ) );	
		}
		catch (SQLException ex)
		{
			throw new AdempiereException("Unable to load production lines", ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		MProduction[] retValue = new MProduction[list.size()];
		list.toArray(retValue);
		return retValue;
	}
	
	
	@Override
	public String completeIt() {

		
		//Lets change date of movements
		
		List<MMovement> list = new Query(getCtx(), MMovement.Table_Name, "M_Movement_ID IN (select m_movement_id from m_movementline where er_dailyproduction_id=?)", get_TrxName())
				.setParameters(get_ID())
				.list();

		for (MMovement mov : list) {
			mov.setMovementDate(getDateDoc());
			mov.save();
		}
		
		MProduction[] plist = getLines();

		for (MProduction pro : plist) {
			
			DocumentEngine engine = new DocumentEngine (pro, getDocStatus());
			boolean str = engine.processIt ("CO", pro.getDocAction());

			if (!str)
			{
				return DocAction.STATUS_Invalid;
			}
			
		}
		
		
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		setDocStatus(DOCSTATUS_Completed);
		return DocAction.STATUS_Completed;
	}

	@Override
	public boolean voidIt() {
		return false;
	}

	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reActivateIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public MProduction[] getLines (String whereClause, String orderClause)
	{
		StringBuilder whereClauseFinal = new StringBuilder(MProduction.COLUMNNAME_ER_DailyProduction_ID +"=? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MMasterProductionLine.COLUMNNAME_M_Product_ID;
		
		List<MMasterProductionLine> list = new Query(getCtx(), MProduction.Table_Name, whereClauseFinal.toString(), get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(orderClause)
										.list();
		
		return list.toArray(new MProduction[list.size()]);		
	}	//	getLines

	
}
