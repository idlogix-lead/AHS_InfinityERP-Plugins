package com.idlogix.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.Callback;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import com.idlogix.models.X_ER_Article;
import com.idlogix.models.X_ER_Color;
import com.idlogix.models.X_ER_Size;
import com.idlogix.models.X_IDL_Assortment;
import com.idlogix.models.X_IDL_Assortment_Line;
import com.idlogix.models.X_M_Parent_Product;

public class CreateSizeFromArticle extends SvrProcess {
	int parentProdID;
	int assortID;
	String parentProdName;
	String parentProdCode;
	int articleID;
	String pp_Name;
	String pp_Code;
	int colorID;
	String colorName;
	String colorCode;
	String childCode;
	String childName;
	String sizeName;
	String sizeCode;
	String  sizeParam;
	String[] sizeList;
	List<Integer> existingAssortments;
	Map<Integer,Integer> prodMapper;
	
	@Override
	protected void prepare() {

		ProcessInfoParameter[] paras = getParameter();
		for (ProcessInfoParameter p : paras) {
			String name = p.getParameterName();	
//			if (name.equalsIgnoreCase("M_Parent_Product_ID")) 
			if (name.equalsIgnoreCase("ER_Article_ID"))
				articleID = p.getParameterAsInt();
			else if (name.equalsIgnoreCase("IDL_Assortment_ID"))
				assortID = p.getParameterAsInt();
			else {
				log.severe("Unknown Parameter: " + name);
			}
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		
		List<Integer> colors = X_ER_Article.getArticleColors(articleID);
		this.addSizes(colors);
		
		//Creating Parent Prod Object and creating color and article Object with respective IDs
		
		return null;
	}
	public List<Integer> getexistingAssortment(int pp_ID,int color_ID,int assort_id) {
		List<Integer> list = new ArrayList<Integer>();
		String strSQL = "select idl_assortment_id::integer existing_id \n"
				+ "from adempiere.m_product\n"
				+ "where m_parent_product_id = "+ pp_ID +"\n"
				+ "and er_color_id = "+ colorID
				+"\n and idl_assortment_id =   "+assort_id;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try
		{
			pstmt = DB.prepareStatement (strSQL.toString(), null);
			rs = pstmt.executeQuery ();
			
			while (rs.next ())		//	Order
			{
				list.add(rs.getInt("existing_id"));
			}
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		return  list;
	}	
	
	
	public void addSizes(List<Integer> colors)
	{
		for(int color_id : colors) 
		{
			
			X_M_Parent_Product parent_Prod = new X_M_Parent_Product(getCtx(),color_id,null);
			X_IDL_Assortment assortment = new X_IDL_Assortment(getCtx(), assortID, null);
			prodMapper = X_M_Parent_Product.prodToSizeMapper(color_id);
			List<X_IDL_Assortment_Line> assortLines = X_IDL_Assortment_Line.getAssortmentLines(assortID);
//			articleID = parent_Prod.get_ValueAsInt("ER_Article_ID");
			X_ER_Article article =new X_ER_Article(getCtx(),articleID,null);
			colorID = parent_Prod.get_ValueAsInt("ER_Color_ID");
			X_ER_Color color =new X_ER_Color(getCtx(),colorID,null);
			parentProdName = parent_Prod.getName();
			parentProdCode = parent_Prod.getValue();
			colorName = color.getName();
			colorCode = color.getValue();
			pp_Name = parent_Prod.getValue();
			pp_Code = parent_Prod.getValue();
//			existingAssortments = this.getexistingAssortment(color_id, colorID,assortID);
//			if(existingAssortments.size()>0) {
//				processUI.ask("Products Against This Assortment Already Created!", new Callback<Boolean>() {
//					@Override
//					public void onCallback(Boolean result) {
//						
//					}
//					});
//			}
//			else {
				for(X_IDL_Assortment_Line line:assortLines) {
					if(prodMapper.get(line.get_ValueAsInt("ER_Size_ID"))==null || 
					   prodMapper.get(line.get_ValueAsInt("ER_Size_ID"))!= line.getIDL_Assortment_ID()) {
						int sizeID = line.get_ValueAsInt("ER_Size_ID");		
						X_ER_Size size = new X_ER_Size(getCtx(),sizeID,null);
						sizeName = size.getName();
						sizeCode = size.getValue();
						String alphabet = assortment.get_Value("Alphabet_Cat").toString();
						childCode = pp_Code + "-"+sizeCode+"-"+alphabet;
						childName = parentProdName +"-"+sizeName+"-"+alphabet;; 
						MProduct prod =new MProduct(Env.getCtx(),0,null);
						prod.setName(childName);
						prod.setValue(childCode);
						prod.set_ValueNoCheck("M_Parent_Product_ID", color_id);
						prod.set_ValueNoCheck("ER_Color_ID", colorID);
						prod.set_ValueNoCheck("ER_Size_ID", sizeID);
						prod.set_ValueNoCheck("ER_Article_ID", articleID);
						prod.set_ValueNoCheck("ItemSource", "I");
						prod.set_ValueNoCheck("IDL_Assortment_ID", assortID);
						prod.set_ValueNoCheck("M_Product_Category_ID", 1000004);
						prod.set_ValueNoCheck("M_Product_SubCategory_ID", article.get_Value("M_Product_SubCategory_ID"));
						prod.set_ValueNoCheck("C_TaxCategory_ID", 1000000);
						prod.set_ValueNoCheck("C_UOM_ID", 1000014);
						prod.set_ValueNoCheck("ProductType", "I");
						prod.set_ValueNoCheck("M_AttributeSet_ID", 1000000);
						prod.setIsBOM(true);
						prod.setAD_Org_ID(0);
						prod.saveEx(get_TrxName());					
			}
			}	
//			}
		}
		
		
	}
	
}