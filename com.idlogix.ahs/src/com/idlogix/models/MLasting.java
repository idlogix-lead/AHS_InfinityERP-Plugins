package com.idlogix.models;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MLasting extends X_ER_Lasting {

	public MLasting(Properties ctx, int ER_Lasting_ID, String trxName) {
		super(ctx, ER_Lasting_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MLasting(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected boolean beforeSave(boolean newRecord)
	{
		int MPID = getM_Production_ID();
		
		BigDecimal StitchingQty = Env.ZERO;
		BigDecimal StrobelQty = Env.ZERO;

		StringBuilder sb = new StringBuilder("SELECT \n");

		sb.append("coalesce(sum(qty), 0) as totalStrobelqty,\n" + 
				"        (SELECT coalesce(sum(qty), 0) from er_strobel where m_production_id = " + MPID + ") as totalstitchingqty\n" + 
				"from er_lasting where er_lasting_id <> " + get_ID() + " AND m_production_id=" + MPID);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sb.toString(), get_TrxName());
			
			rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				StitchingQty = rs.getBigDecimal("totalstitchingqty");
				StrobelQty = rs.getBigDecimal("totalstrobelqty");

			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sb.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (StitchingQty.compareTo(StrobelQty.add(getQty())) == -1)
		{
			log.saveError("Error", Msg.getMsg(getCtx(), "Qty Overflow"));
			return false;
		}
		return super.beforeSave(newRecord);
	}
	
}
