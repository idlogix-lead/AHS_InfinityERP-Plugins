package com.idlogix.factories;

import org.adempiere.webui.factory.IFormFactory;
import org.adempiere.webui.panel.ADForm;


public class MyFormFactory implements IFormFactory {

	@Override
	public ADForm newFormInstance(String formName) {
	
		if(formName.trim().equalsIgnoreCase("MyForm"))
			return  null;
		return null;
	}

}
