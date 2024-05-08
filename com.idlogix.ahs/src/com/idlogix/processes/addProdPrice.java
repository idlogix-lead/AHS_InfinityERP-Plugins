package com.idlogix.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.idlogix.models.X_ER_Article;
import com.idlogix.models.X_M_Parent_Bom;
import com.idlogix.models.X_M_Parent_Product;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.Callback;
import org.compiere.model.MProductPrice;
public class addProdPrice  extends SvrProcess{

//	private int parentProdID;
	private int articleID;
	private int priceListVersion;
	private BigDecimal listPrice;
	private BigDecimal stdPrice;
	private BigDecimal limitPrice;
	private Map<Integer,Object> prodIds;

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
		ProcessInfoParameter[] paras = getParameter();
		for (ProcessInfoParameter p : paras) {
			String name = p.getParameterName();	
			//if (name.equalsIgnoreCase("M_Parent_Product_ID")) {
			if (name.equalsIgnoreCase("ER_Article_ID")) {
				articleID = p.getParameterAsInt();
//				parentProdID = p.getParameterAsInt();
			}else if (name.equalsIgnoreCase("M_PriceList_Version"))
				priceListVersion = p.getParameterAsInt();
			else if (name.equalsIgnoreCase("List_Price"))
				listPrice = p.getParameterAsBigDecimal();
			else if (name.equalsIgnoreCase("Standard_Price"))
				stdPrice = p.getParameterAsBigDecimal();
			else if (name.equalsIgnoreCase("limit_price"))
				limitPrice = p.getParameterAsBigDecimal();
			else {
				log.severe("Unknown Parameter: " + name);
			}
		}
		BigDecimal minus = new BigDecimal(-1);
		listPrice = listPrice.compareTo(BigDecimal.ZERO)==-1? listPrice.multiply(minus):listPrice;
		stdPrice = stdPrice.compareTo(BigDecimal.ZERO)==-1? stdPrice.multiply(minus):stdPrice;
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		prodIds = X_ER_Article.getProdPrices(articleID,priceListVersion);		
		updatePrice();
	
		return null;
	}
	private void updatePrice() {
		Iterator<Integer> itr = prodIds.keySet().iterator();
		while (itr.hasNext())
		{
		    int prodId = itr.next();
		    Object value = prodIds.get(prodId);
		    if(value != null) 
		    {
		    	MProductPrice price = new MProductPrice(getCtx(), (int) value, null);		
				price.set_ValueOfColumn("PriceList", listPrice);
				price.set_ValueOfColumn("PriceStd", stdPrice);
				price.set_ValueOfColumn("PriceLimit", limitPrice);
				price.saveEx();
		    }
		    else 
		    {
		    	insertPricePerProd(prodId);
		    }
		}
	}
	private void insertPricePerProd(int prodId) {
		
		MProductPrice price = new MProductPrice(getCtx(), 0, null);
		price.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
		price.set_ValueOfColumn("AD_Client_ID", Env.getAD_Client_ID(getCtx()));
		price.setM_Product_ID(prodId);
		price.setM_PriceList_Version_ID(priceListVersion);
		price.setPriceList(listPrice);
		price.setPriceStd(stdPrice);
		price.setPriceLimit(limitPrice);		
		price.save();
		
	}
}
