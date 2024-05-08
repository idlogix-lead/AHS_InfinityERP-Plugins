package com.idlogix.factories;


import org.adempiere.base.IColumnCalloutFactory;
import org.adempiere.base.IColumnCallout;
import org.compiere.model.MProduct;

import com.idlogix.callouts.ChangeArticleOnPO;
import com.idlogix.callouts.FG_KeyGenerator;
import com.idlogix.callouts.GenerateEmployeeKey;
import com.idlogix.callouts.GenerateProdName;
import com.idlogix.callouts.LastingCallout;
import com.idlogix.callouts.OpenFormTestCallout;
import com.idlogix.callouts.OpenWindowCallout;
import com.idlogix.callouts.PP_SearchKeyGenerator;
import com.idlogix.callouts.PaymentDescriptionChange;
import com.idlogix.callouts.PriceFieldReadOnly;

import java.util.ArrayList;
import java.util.List;
import org.compiere.model.MOrder;


public class CalloutFactory implements IColumnCalloutFactory
{

	public IColumnCallout[] getColumnCallouts(String tableName,String columnName) 
	{
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
		
		
		if(tableName.equalsIgnoreCase("ER_Article") )
				 
		{
			if(columnName.equalsIgnoreCase("ER_Article_Group1_ID")||
			   columnName.equalsIgnoreCase("M_Product_SubCategory_ID")||
			   columnName.equalsIgnoreCase("M_Brand_ID")||
      		   columnName.equalsIgnoreCase("Gender")
			  )
			
			{list.add(new FG_KeyGenerator());}
			
				
		}
		if(tableName.equalsIgnoreCase("M_Parent_Product") )
			 
		{
			if(columnName.equalsIgnoreCase("ER_Color_ID")
			   
			  )
			
			{list.add(new PP_SearchKeyGenerator());}
			
				
		}
		if(tableName.equalsIgnoreCase("C_BPartner") && columnName.equalsIgnoreCase("AD_Client_ID" )) 
		{
			list.add(new GenerateEmployeeKey());
		}
		if(tableName.equalsIgnoreCase("M_Product") && columnName.equalsIgnoreCase("IDL_Assortment_ID" )) 
		{
			list.add(new GenerateProdName());
		}
		if(tableName.equalsIgnoreCase("C_Payment") && columnName.equalsIgnoreCase("Description" )) 
		{
			list.add(new PaymentDescriptionChange());
		}
//		if(tableName.equalsIgnoreCase("C_Order_ParentProduct") && columnName.equalsIgnoreCase("M_Parent_Product_ID" )) 
//		{
//			list.add(new ChangeArticleOnPO());
//		}
		
		if(tableName.equalsIgnoreCase("M_InOut") && columnName.equalsIgnoreCase("CreateInoutCartonWise" )) 
		{
			list.add(new OpenFormTestCallout());
		}
//		if(tableName.equalsIgnoreCase("M_Product") && columnName.equalsIgnoreCase("Description" )) 
//		{
//			list.add(new OpenFormTestCallout());
//		}
		if(tableName.equalsIgnoreCase("C_OrderLine") && columnName.equalsIgnoreCase("M_Product_ID" )) 
		{
			list.add(new PriceFieldReadOnly());
		}
		
		return list!= null ? list.toArray(new IColumnCallout[0]): new IColumnCallout[0];
	}
}