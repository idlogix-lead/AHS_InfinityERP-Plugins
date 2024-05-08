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

public class MCutting extends X_ER_Cutting {

	public MCutting(Properties ctx, int ER_Cutting_ID, String trxName) {
		super(ctx, ER_Cutting_ID, trxName);
	}

	  public MCutting (Properties ctx, ResultSet rs, String trxName)
	    {
	      super (ctx, rs, trxName);
	    }

	  
	private static final long serialVersionUID = 1L;

	protected boolean beforeSave(boolean newRecord)
	{
		int MPID = getM_Production_ID();
		MProduction p = new MProduction(Env.getCtx(), MPID, get_TrxName());
		BigDecimal plannedQty = p.getProductionQty();
		BigDecimal thisQty = Env.ZERO;
		BigDecimal otherQty = Env.ZERO;
		BigDecimal totalQty = Env.ZERO;
		
		StringBuilder sb = new StringBuilder("SELECT \n");
		sb.append(" coalesce(sum(qty), 0) totalQty ");
		sb.append("	from ER_cutting \n");
		sb.append(" where er_cutting.m_production_id = " + MPID + " and er_cutting.er_cutting_id <> " + get_ID());
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sb.toString(), get_TrxName());
			
			rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				otherQty = rs.getBigDecimal("totalQty");

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
		thisQty=getQty();
		totalQty = otherQty.add(thisQty);
		
		if (plannedQty.compareTo(totalQty) == -1)
		{
			log.saveError("Error", Msg.getMsg(getCtx(), "Qty Overflow"));
			return false;
		}
		return super.beforeSave(newRecord);
	}
	
	
	
}
