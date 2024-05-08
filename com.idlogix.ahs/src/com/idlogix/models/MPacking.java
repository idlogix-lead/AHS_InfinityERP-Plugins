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

public class MPacking extends X_ER_Packing {

	public MPacking(Properties ctx, int ER_Packing_ID, String trxName) {
		super(ctx, ER_Packing_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPacking(Properties ctx, ResultSet rs, String trxName) {
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
		
		BigDecimal StrobelQty = Env.ZERO;
		BigDecimal PackQty = Env.ZERO;

		StringBuilder sb = new StringBuilder("SELECT \n");

		sb.append("coalesce(sum(qty), 0) as totalpackqty,\n" + 
				"        (SELECT coalesce(sum(qty), 0) from er_lasting where m_production_id = " + MPID + ") as totalstrobelqty\n" + 
				"from er_packing where er_packing_id <> " + get_ID() + " AND m_production_id=" + MPID);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sb.toString(), get_TrxName());
			
			rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				StrobelQty = rs.getBigDecimal("totalstrobelqty");
				PackQty = rs.getBigDecimal("totalpackqty");

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
		if (StrobelQty.compareTo(PackQty.add(getQty())) == -1)
		{
			log.saveError("Error", Msg.getMsg(getCtx(), "Qty Overflow"));
			return false;
		}
		return super.beforeSave(newRecord);
	}
}
