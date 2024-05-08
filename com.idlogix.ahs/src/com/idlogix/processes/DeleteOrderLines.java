package com.idlogix.processes;

import java.sql.PreparedStatement;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;

public class DeleteOrderLines {
	public static void deleteOrderLines(int ppOrderId) {
		String strSQL = "DELETE\n"
				+ " FROM ADEMPIERE.C_ORDERLINE\n"
				+ " where c_orderline_id IN \n"
				+ "(select c_orderline_id \n"
				+ "from adempiere.c_orderline ol \n"
				+ "join adempiere.c_order od ON ol.c_order_id = od.c_order_id \n"
				+ "where od.docstatus <> 'CO' and ol.C_ORDER_PARENTPRODUCT_ID = " + ppOrderId+")";
			 PreparedStatement pstmt = null;	
			 try
			 {
			 pstmt = DB.prepareStatement (strSQL.toString(), null);
			 pstmt.executeUpdate();
			 }
			 catch (Exception e)
			 {
			 	throw new AdempiereException(e);
			 }		
	}
}
