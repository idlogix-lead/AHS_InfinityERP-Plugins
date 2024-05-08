package com.idlogix.processes;

import java.util.List;
import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.util.Env;
import com.idlogix.models.X_ER_Article;
import com.idlogix.models.X_ER_Color;
import com.idlogix.models.X_ER_Size;
import com.idlogix.models.X_IDL_Assortment;
import com.idlogix.models.X_M_Parent_Product;

public class DelegateNameValueChange {
	PO po;
	int articleID;
	public DelegateNameValueChange(PO po){
		this.po=po;
	}
	
	public void doChange() {
		
		String artCode = (String)po.get_Value("Value");
		articleID = (int)po.get_Value("ER_Article_ID");
		List<Integer> parentprods = X_ER_Article.getArticleColors(articleID);
		
		for(int ppId:parentprods) {
			X_M_Parent_Product pp = new X_M_Parent_Product(Env.getCtx(), ppId, (String) null);
			int colorId = pp.get_ValueAsInt("ER_Color_ID");
			X_ER_Color color = new X_ER_Color(Env.getCtx(), colorId, (String) null);
			String colorName = color.getName();
			String ppCode = artCode+" "+colorName;
			pp.setValue(ppCode);
			pp.setName(ppCode);
			pp.save();
			List<Integer> prodIds = X_M_Parent_Product.getChildProdIds(ppId);
			for(int prodId:prodIds) {
				MProduct prod = new MProduct(Env.getCtx(), prodId, (String)null);
				int sizeID = prod.get_ValueAsInt("ER_Size_ID");		
				int assorId = prod.get_ValueAsInt("IDL_Assortment_ID");
				X_ER_Size size = new X_ER_Size(Env.getCtx(),sizeID,null);
				X_IDL_Assortment assortment = new X_IDL_Assortment(Env.getCtx(), assorId, (String)null);
				String sizeName = size.getName();
				String alphabet = assortment.get_Value("Alphabet_Cat").toString();
				String childCode = ppCode + "-"+sizeName+"-"+alphabet;
				prod.setValue(childCode);
				prod.setName(childCode);
				prod.save();		
			}
			
		}
	}
	

}
