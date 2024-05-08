package com.idlogix.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.eri.model.X_ER_Size;
import org.eri.model.X_M_Parent_Product;

import com.idlogix.models.X_IDL_Assortment;

public class GenerateProdName implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		int PID = (int) mTab.getValue("M_Parent_Product_ID");
		int SizeID = (int) mTab.getValue("ER_Size_ID");
		int assortID = (int) mTab.getValue("IDL_Assortment_ID");
		X_M_Parent_Product pproudct = null;
		X_ER_Size size = null;
		X_IDL_Assortment assort = null;
		if (PID > 0)
		{
			pproudct = new X_M_Parent_Product(Env.getCtx(), PID, "noname");
		}

		if ( SizeID > 0)
		{
			size = new X_ER_Size(Env.getCtx(), SizeID, "noname");
		}
		if ( assortID > 0)
		{
			assort = new X_IDL_Assortment(Env.getCtx(), assortID, "noname");
		}
		String alpha = assort.get_ValueAsString("Alphabet_Cat");
		String prodCode = pproudct.getName() + "-" + size.getName()+"-"+alpha;
		mTab.setValue("Value" , prodCode);
		mTab.setValue("Name" , prodCode);
		return null;
	}

}
