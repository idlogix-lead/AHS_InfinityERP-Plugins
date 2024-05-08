package com.idlogix.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.idlogix.processes.AddProductOnDP_EvenSizes;
import com.idlogix.processes.AddProductOnDailyProduction;
import com.idlogix.processes.AddProductOnMasterProduction;
import com.idlogix.processes.CreateChildProducts;
import com.idlogix.processes.CreateSizeFromArticle;
import com.idlogix.processes.InventoryMoveCartonWise;
import com.idlogix.processes.ProductionCreate;
import com.idlogix.processes.addProdPrice;
import com.idlogix.processes.addProductsMasterManually;
import com.idlogix.processes.addSO_Lines;

public class MyProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		// TODO Auto-generated method stub
		

		if(className.equals("com.idlogix.processes.CreateChildProducts"))
			return new CreateChildProducts();
		if(className.equals("com.idlogix.processes.CreateSizeFromArticle"))
			return new CreateSizeFromArticle();
		if(className.equals("com.idlogix.processes.addProductsMasterManually"))
			return new addProductsMasterManually();
		if(className.equals("com.idlogix.processes.addProdPrice"))
			return new addProdPrice();
		if(className.equals("com.idlogix.processes.addSO_Lines"))
			return new addSO_Lines();
		if(className.equals("com.idlogix.processes.AddProductOnDP_EvenSizes"))
			return new AddProductOnDP_EvenSizes();
		if(className.equals("com.idlogix.processes.ProductionCreate"))
			return new ProductionCreate();
		
		if(className.equals("com.idlogix.processes.AddProductOnMasterProduction"))
			return new AddProductOnMasterProduction();
		if(className.equals("com.idlogix.processes.addProductsMasterManually"))
			return new AddProductOnDailyProduction();
		if(className.equals("com.idlogix.processes.InventoryMoveCartonWise"))
			return new InventoryMoveCartonWise();

		return null;
	}
	
	

}
