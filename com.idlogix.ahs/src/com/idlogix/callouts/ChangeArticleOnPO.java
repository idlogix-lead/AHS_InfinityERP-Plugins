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

public class ChangeArticleOnPO implements IColumnCallout {
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		boolean goAhead = false;
		goAhead = oldValue != null &&  (int)oldValue != (int)value ? true :goAhead;
		GridField button = mTab.getField("CreateOrderLines");
		if(goAhead) 
		{
			int pp_id = (int)oldValue;
			int cpp_id = mTab.getRecord_ID();	
			X_C_Order_ParentProduct opp = new X_C_Order_ParentProduct(Env.getCtx(),cpp_id,null);
			opp.get_ValueAsInt("M_Parent_Product_ID");
			opp.set_ValueOfColumn("M_Parent_Product_ID", pp_id);
			opp.save();
			WEditor editor = WebEditorFactory.getEditor(mTab,button,false);
			IProcessButton pb = (IProcessButton) editor;
			ADWindow adWindow = ADWindow.get(WindowNo);
			ADWindowContent content = adWindow.getADWindowContent();		
			content.executeButtonProcess(pb, true, mTab.getAD_Tab_ID(), mTab.getRecord_ID(), true);
			String strSQL = "Update adempiere.C_Order_ParentProduct "
							+ "set m_parent_product_id = "+pp_id+" \n"
					+" where C_Order_ParentProduct_id = "+cpp_id;
			PreparedStatement pstmt = null;
			 ResultSet rs = null;	
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
//		mTab.setValue("M_Parent_Product_ID", oldValue);
		
		
		return null;
	}
}
