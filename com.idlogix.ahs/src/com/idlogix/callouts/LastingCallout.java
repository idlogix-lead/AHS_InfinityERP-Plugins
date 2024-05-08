package com.idlogix.callouts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import org.adempiere.base.IColumnCallout;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.adwindow.ADWindow;
import org.adempiere.webui.adwindow.ADWindowContent;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;
import com.idlogix.models.X_C_Order_ParentProduct;
import org.adempiere.webui.editor.IProcessButton;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.eri.model.MLasting;
import java.sql.Timestamp;
import java.time.Instant;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.eri.model.MLastingHdr;



public class LastingCallout implements IColumnCallout {
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub

		int rows = 0; 
		String resultMsg ="";
		String id=""; int hdrid=1000019;
			MLastingHdr lastingHdr= new MLastingHdr(Env.getCtx(), 1000019, null);
				String strSQL = "select * from  M_Production where  ER_DailyProduction_ID=" + 1000430  ; 
								PreparedStatement pstmt = null;
				ResultSet rs = null;
		PreparedStatement pstmtbal = null;
				ResultSet rs_bal = null;
		Timestamp movementDate=Timestamp.from(Instant.now());
		int balqty =0;
		try
				{
					
					
		//balqty =10;
		pstmt = DB.prepareStatement (strSQL.toString(), null);
					rs = pstmt.executeQuery ();
					while (rs.next ())		//	Order
					{
		String strSQLbal = "select * from er_Lasting_balance_size_v where balanceqty>0 and M_Production_ID=" + rs.getInt("M_Production_ID"); 
					pstmtbal = DB.prepareStatement (strSQLbal.toString(), null);
					rs_bal = pstmtbal.executeQuery ();
		balqty=0;
		while (rs_bal.next ())		//	Order
					{
		balqty = rs_bal.getInt("balanceqty");
					
		}
						rows =rows+1;				
						MLasting pline = new MLasting(Env.getCtx(), 0, null);
						pline.setAD_Org_ID(rs.getInt("AD_Org_ID"));
						pline.setM_Production_ID(rs.getInt("M_Production_ID"));
						pline.setQty(new BigDecimal(balqty));
						pline.set_ValueOfColumn("M_Product_ID", rs.getInt("m_product_id"));
		                                pline.set_ValueOfColumn("PP_Production_Line_ID", 1000001);
						pline.set_ValueOfColumn("ER_LastingHdr_ID", hdrid);
						pline.set_ValueOfColumn("Value",new Timestamp(movementDate.getTime()));
		                                if(balqty>0)				
		                                    pline.save();
								
		}
//		lastingHdr.set_ValueOfColumn("Processed",'Y');
		lastingHdr.save();	
					resultMsg="product"+rows+" Production Saved Successfully.";


				}
				catch (Exception e)
				{
				resultMsg="exception:"+e.getMessage();
				}

		return null;
	}
}
