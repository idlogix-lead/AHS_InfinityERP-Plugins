package com.idlogix.processes;

import org.compiere.process.SvrProcess;
import org.compiere.util.DisplayType;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.adempiere.util.Callback;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupInfo;


public class TestProcess extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		CompletableFuture<String> result = new CompletableFuture<>();
		final BigDecimal bd;
		String s = "";
		
		final StringBuffer answer = new StringBuffer();
		//answer.append("");
		MLookupInfo li = new MLookupInfo("SELECT M_Product_ID, Value,Name, IsActive FROM M_Product WHERE IsActive = 'Y'", "M_Product", "M_Product.M_Product_ID", 0, 0, null);
		
		processUI.askForInput("Choose Products", null , DisplayType.Amount, new Callback<Object>() {
			
			@Override
			public void onCallback(Object result) {
				answer.append(result.toString());
				// TODO Auto-generated method stub
				
			}
		
		});
		return answer.toString();
		
		
		
	}

}
