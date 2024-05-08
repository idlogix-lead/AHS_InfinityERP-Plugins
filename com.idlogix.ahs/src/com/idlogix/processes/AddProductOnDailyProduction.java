package com.idlogix.processes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eri.model.MDailyProduction;
import org.eri.model.MProduction;
import org.eri.model.X_ER_Cutting_Plan;
import org.eri.model.X_ER_Lasting_Plan;
import org.eri.model.X_ER_Packing_Plan;
import org.eri.model.X_ER_Stitching_Plan;
import org.eri.model.X_ER_Strobel_Plan;

public class AddProductOnDailyProduction extends SvrProcess {

	int id = 0;

	int C_Order_ParentProduct_ID=0;
	
	int M_Locator_ID = 0;
	BigDecimal totalQty = Env.ZERO;
	BigDecimal currentTicketQty = Env.ZERO;
	
	int StitchingLine_ID = 0;
	int LastingLine_ID = 0;
	int CuttingLine_ID = 0;
	int StrobelLine_ID = 0;
	int PackingLine_ID = 0;
	Timestamp DateTrx = null;
	
	@Override
	protected void prepare() {
		id = getRecord_ID();
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Locator_ID"))
				M_Locator_ID = para[i].getParameterAsInt();
			
			else if (name.equals("MovementQty"))
				{
				totalQty = para[i].getParameterAsBigDecimal();
				currentTicketQty = para[i].getParameterAsBigDecimal();
				}

			else if (name.equals("StitchingLine_ID"))
				StitchingLine_ID = para[i].getParameterAsInt();

			else if (name.equals("LastingLine_ID"))
				LastingLine_ID = para[i].getParameterAsInt();

			else if (name.equals("CuttingLine_ID"))
				CuttingLine_ID = para[i].getParameterAsInt();

			else if (name.equals("StrobelLine_ID"))
				StrobelLine_ID = para[i].getParameterAsInt();

			else if (name.equals("PackingLine_ID"))
				PackingLine_ID = para[i].getParameterAsInt();

			else if (name.equals("DateTrx"))
				DateTrx = para[i].getParameterAsTimestamp();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {

		if (currentTicketQty.compareTo(Env.ZERO) <= 0)
		{
			return "";	
		}
		
		
		BigDecimal parentproductqty = Env.ZERO;
		BigDecimal sizeProductionQtyTillNow = Env.ZERO;

		List<MProduction> plines = new ArrayList<MProduction>();
		BigDecimal qtycalledinproduction = Env.ZERO;
				
		BigDecimal qtyProduct = Env.ZERO;
		
		MDailyProduction  mp = new MDailyProduction(Env.getCtx(), id, get_TrxName());
//		CID=mp.geter getC_Order_ID();
		C_Order_ParentProduct_ID = mp.getC_Order_ParentProduct_ID();
		
		String strSQL = "select coalesce(sum(oline.qty), 0) from ER_MasterProductionLine oline \n" + 
				"				 inner join c_orderline orderline on orderline.C_orderline_id = oline.c_orderline_id \n" + 
				"                inner join m_product p on p.m_product_id = oline.m_product_id\n" + 
				"                where  oline.ER_MasterProduction_ID=" + mp.getER_MasterProduction_ID() + "\n" + 
				"				 and orderline.C_Order_ParentProduct_ID = " + C_Order_ParentProduct_ID;
//				"                and  p.M_Parent_Product_ID="+ mp.getM_Parent_Product_ID();
//				"	and oline.c_orderline_id \n" + 
//				"		not in (select c_orderline_id from adempiere.er_masterproductionline pline where pline.er_masterproduction_id=" + mp.get_ID() + ")";

		parentproductqty = DB.getSQLValueBD(get_TrxName(), strSQL);
//		parentproductqty = totalQty;
		strSQL = "select coalesce(sum(productionqty), 0) from m_production where ER_MasterProductionLine_ID in (\n" + 
				"        select ER_MasterProductionLine_ID\n" + 
				"        from ER_MasterProductionLine oline \n" + 
				"                inner join c_orderline orderline on orderline.C_orderline_id = oline.c_orderline_id \n" + 
				"                inner join m_product p on p.m_product_id = oline.m_product_id\n" + 
				"                where oline.ER_MasterProduction_ID=" + mp.getER_MasterProduction_ID() + " \n" + 
				"				 and orderline.C_Order_ParentProduct_ID = " + C_Order_ParentProduct_ID  + "\n" + 
				")";
		
		BigDecimal productionQtyTillNow = DB.getSQLValueBD(get_TrxName(), strSQL);
		
		if(totalQty.compareTo(parentproductqty.subtract(productionQtyTillNow))==1)
			totalQty = parentproductqty.subtract(productionQtyTillNow);
		
		strSQL = "select oline.* from ER_MasterProductionLine oline \n" + 
				"                inner join m_product p on p.m_product_id = oline.m_product_id\n" + 
				"				 inner join c_orderline orderline on orderline.C_orderline_id = oline.c_orderline_id \n" + 
				"                where  oline.ER_MasterProduction_ID=" + mp.getER_MasterProduction_ID() + "\n" + 
				"				 and orderline.C_Order_ParentProduct_ID = " + C_Order_ParentProduct_ID +
				" order by p.name asc";
//				"                and  p.M_Parent_Product_ID="+ mp.getM_Parent_Product_ID() ; 
//				"	and oline.c_orderline_id \n" + 
//				"		not in (select c_orderline_id from er_masterproductionline pline where pline.er_masterproduction_id=" + mp.get_ID() + ")";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean addRemaining=false;
		try
		{
			pstmt = DB.prepareStatement (strSQL.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY, get_TrxName());
			 
//			    ResultSet.CONCUR_READ_ONLY
			    
			rs = pstmt.executeQuery ();
			BigDecimal remainingQty = totalQty;
			int rowCount =0;
			while (rs.next ())		//	Order
			{
		
				strSQL = "select coalesce(sum(productionqty), 0) from m_production where ER_MasterProductionLine_ID in (\n" + 
						"        select ER_MasterProductionLine_ID\n" + 
						"        from ER_MasterProductionLine oline \n" + 
						"                inner join c_orderline orderline on orderline.C_orderline_id = oline.c_orderline_id \n" + 
						"                inner join m_product p on p.m_product_id = oline.m_product_id\n" + 
						"                where  oline.ER_MasterProduction_ID=" + mp.getER_MasterProduction_ID() + " \n" + 
						"				 and orderline.m_product_id = " + rs.getInt("M_Product_ID") + "\n" + 
						"				 and orderline.C_Order_ParentProduct_ID = " + C_Order_ParentProduct_ID  + "\n" + 
						")";
				
				sizeProductionQtyTillNow = DB.getSQLValueBD(get_TrxName(), strSQL);
				BigDecimal ratio = rs.getBigDecimal("qty").divide(parentproductqty, MathContext.DECIMAL32);
				
				if(ratio.multiply(currentTicketQty).setScale(0, RoundingMode.CEILING).compareTo(rs.getBigDecimal("qty").subtract(sizeProductionQtyTillNow))==1)
					qtyProduct = rs.getBigDecimal("qty").subtract(sizeProductionQtyTillNow);
				else
					qtyProduct = ratio.multiply(currentTicketQty).setScale(0, RoundingMode.CEILING);
				
//				if (currentTicketQty.compareTo(parentproductqty.subtract(sizeProductionQtyTillNow)) == 1)
//				{
////					throw new IllegalArgumentException("Qty Overflow");
//					totalQty = parentproductqty.subtract(qtycalledinproduction);
//				}
//
//				qtyProduct = rs.getBigDecimal("qty");
//				qtyProduct =  totalQty.divide(parentproductqty, MathContext.DECIMAL32).multiply(qtyProduct);
//				qtyProduct = qtyProduct.setScale(0, RoundingMode.FLOOR);
				
				if(qtyProduct.compareTo(Env.ZERO)!=0) {
					remainingQty = remainingQty.subtract(qtyProduct) ;
					
					MProduction pline = new MProduction(Env.getCtx(), 0, get_TrxName());
					plines.add(pline);
					
					pline.setAD_Org_ID(mp.getAD_Org_ID());
					pline.setM_Product_ID(rs.getInt("M_Product_ID"));
					pline.setC_OrderLine_ID(rs.getInt("C_OrderLine_ID"));
					pline.setProductionQty(qtyProduct);
					
	//				pline.setER_MasterProduction_ID(mp.getER_MasterProduction_ID());
					pline.setMovementDate(mp.getDateDoc());
					pline.setER_DailyProduction_ID(mp.get_ID());
					pline.setM_Locator_ID(M_Locator_ID);
					
					pline.setER_MasterProductionLine_ID(rs.getInt("ER_MasterProductionLine_ID"));
					
//					pline.setCuttingPlanQty(pline.getProductionQty());
//					pline.setLastingPlanQty(pline.getProductionQty());
//					pline.setPackingPlanQty(pline.getProductionQty());
//					pline.setStitchingPlanQty(pline.getProductionQty());
//					pline.setStrobelPlanQty(pline.getProductionQty());					
					// New Code Added
					strSQL =  "select sum(qty) \n" + 
							"        from ER_MasterProductionLine oline \n" + 
							"                inner join c_orderline orderline on orderline.C_orderline_id = oline.c_orderline_id \n" + 
							"                inner join m_product p on p.m_product_id = oline.m_product_id\n" + 
							"                where  oline.ER_MasterProduction_ID=" + mp.getER_MasterProduction_ID() + " \n" + 
							"				 and orderline.m_product_id = " + pline.getM_Product_ID() + "\n" + 
							"				 and orderline.C_Order_ParentProduct_ID = " + C_Order_ParentProduct_ID  + "\n" + 
							"";
					
					BigDecimal prodQty = DB.getSQLValueBD(get_TrxName(), strSQL);
	
					strSQL = "select coalesce(sum(productionqty), 0) from m_production where ER_MasterProductionLine_ID in (\n" + 
							"        select ER_MasterProductionLine_ID\n" + 
							"        from ER_MasterProductionLine oline \n" + 
							"                inner join c_orderline orderline on orderline.C_orderline_id = oline.c_orderline_id \n" + 
							"                inner join m_product p on p.m_product_id = oline.m_product_id\n" + 
							"                where  oline.ER_MasterProduction_ID=" + mp.getER_MasterProduction_ID() + " \n" + 
							"				 and orderline.m_product_id = " + pline.getM_Product_ID() + "\n" + 
							"				 and orderline.C_Order_ParentProduct_ID = " + C_Order_ParentProduct_ID  + "\n" + 
							")";
					
					sizeProductionQtyTillNow = DB.getSQLValueBD(get_TrxName(), strSQL);
					
					
					
					if(sizeProductionQtyTillNow.add(qtyProduct).compareTo(prodQty)>0 ) {
						pline.setProductionQty(pline.getProductionQty().subtract(Env.ONE));
	//					pline.save();
						remainingQty = remainingQty.add(Env.ONE);
						}
					
					if(prodQty.subtract(sizeProductionQtyTillNow.add(qtyProduct)).compareTo(new BigDecimal(5)) < 0 ) {
						BigDecimal addAllRemaining = prodQty.subtract(sizeProductionQtyTillNow.add(qtyProduct));
						pline.setProductionQty(pline.getProductionQty().add(addAllRemaining));
	//					pline.save();
						remainingQty = remainingQty.subtract(addAllRemaining);
						}
					
					if(remainingQty.compareTo(Env.ZERO)<0) {
						pline.setProductionQty(pline.getProductionQty().add(remainingQty));
						remainingQty = Env.ZERO;
					}
					// New Code End
					boolean ret = false;
					if(pline.getProductionQty().compareTo(Env.ZERO)!=0)
							ret = pline.save();
	
					if (ret)
					{
						
						commit();
						ProcessInfoParameter pi1 = new ProcessInfoParameter("Record_ID", 1000024,"","","");
						ProcessInfo pi = new ProcessInfo("", 53226,0,0);
						pi.setParameter(new ProcessInfoParameter[] {pi1});
						MProcess pr = new Query(Env.getCtx(), MProcess.Table_Name, "value=?", null)
						                        .setParameters(new Object[]{"M_Production_Create"})
						                        .first();
						if (pr==null) {
						      return "Error";
						}
				
						ProductionCreate process = new ProductionCreate();
				
						MPInstance mpi = new MPInstance(Env.getCtx(), 0, null);
						mpi.setAD_Process_ID(pr.get_ID()); 
						mpi.setRecord_ID(mp.get_ID());
						mpi.save();
				
						pi.setAD_PInstance_ID(mpi.get_ID());
						pi.setRecord_ID(pline.get_ID());
						boolean result = process.startProcess(Env.getCtx(), pi, null);
						
						if (result == false)
						{
							return "ERROR";
						}
					}
				}
				
				if(rs.isLast() && remainingQty.compareTo(Env.ZERO)!=0) {
//					rs.beforeFirst();
					addRemaining = true;
				}
			}
			

//				qtyProduct = qtyProduct.add(remainingQty);
				
//					for (MProduction mProduction : plines) {
//						if(remainingQty.compareTo(Env.ZERO)>0)
//							 {
//							
//							strSQL =  "select sum(qty) \n" + 
//									"        from ER_MasterProductionLine oline \n" + 
//									"                inner join c_orderline orderline on orderline.C_orderline_id = oline.c_orderline_id \n" + 
//									"                inner join m_product p on p.m_product_id = oline.m_product_id\n" + 
//									"                where  oline.ER_MasterProduction_ID=" + mp.getER_MasterProduction_ID() + " \n" + 
//									"				 and orderline.m_product_id = " + mProduction.getM_Product_ID() + "\n" + 
//									"				 and orderline.C_Order_ParentProduct_ID = " + C_Order_ParentProduct_ID  + "\n" + 
//									"";
//							
//							BigDecimal prodQty = DB.getSQLValueBD(get_TrxName(), strSQL);
//
//							strSQL = "select coalesce(sum(productionqty), 0) from m_production where ER_MasterProductionLine_ID in (\n" + 
//									"        select ER_MasterProductionLine_ID\n" + 
//									"        from ER_MasterProductionLine oline \n" + 
//									"                inner join c_orderline orderline on orderline.C_orderline_id = oline.c_orderline_id \n" + 
//									"                inner join m_product p on p.m_product_id = oline.m_product_id\n" + 
//									"                where  oline.ER_MasterProduction_ID=" + mp.getER_MasterProduction_ID() + " \n" + 
//									"				 and orderline.m_product_id = " + mProduction.getM_Product_ID() + "\n" + 
//									"				 and orderline.C_Order_ParentProduct_ID = " + C_Order_ParentProduct_ID  + "\n" + 
//									")";
//							
//							sizeProductionQtyTillNow = DB.getSQLValueBD(get_TrxName(), strSQL);
//							
//							if(sizeProductionQtyTillNow.add(Env.ONE).compareTo(prodQty)<=0) {
//								mProduction.setProductionQty(mProduction.getProductionQty().add(Env.ONE));
//								mProduction.save();
//								remainingQty = remainingQty.subtract(Env.ONE);
//								}
//							}		
//					}
					
					
//					for (MProduction m_production : plines) {
//					ProcessInfoParameter pi1 = new ProcessInfoParameter("Record_ID", 1000024,"","","");
//					ProcessInfo pi = new ProcessInfo("", 53226,0,0);
//					pi.setParameter(new ProcessInfoParameter[] {pi1});
//					MProcess pr = new Query(Env.getCtx(), MProcess.Table_Name, "value=?", null)
//					                        .setParameters(new Object[]{"M_Production_Create"})
//					                        .first();
//					if (pr==null) {
//						  return "Error";
//						}
//					ProductionCreate process = new ProductionCreate();
//					MPInstance mpi = new MPInstance(Env.getCtx(), 0, null);
//					mpi.setAD_Process_ID(pr.get_ID()); 
//					mpi.setRecord_ID(mp.get_ID());
//					mpi.save();
//					pi.setAD_PInstance_ID(mpi.get_ID());
//					pi.setRecord_ID(m_production.get_ID());
//					boolean result = process.startProcess(Env.getCtx(), pi, null);
//					if (result == false){
//							return "ERROR";
//						}
//					}
			


		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		
		
//		if (CuttingLine_ID > 0)
//		{
//			X_ER_Cutting_Plan cuttingplan = new X_ER_Cutting_Plan(Env.getCtx(), 0, get_TrxName());
//			cuttingplan.setPP_Production_Line_ID(CuttingLine_ID);
//			cuttingplan.setER_DailyProduction_ID(mp.get_ID());
//			cuttingplan.setQty(totalQty);
//			cuttingplan.setPlanDate(DateTrx);
//			cuttingplan.save();
//		}
//		if (StitchingLine_ID > 0)
//		{
//			X_ER_Stitching_Plan StitchingPlan = new X_ER_Stitching_Plan(Env.getCtx(), 0, get_TrxName());
//			StitchingPlan.setPP_Production_Line_ID(StitchingLine_ID);
//			StitchingPlan.setER_DailyProduction_ID(mp.get_ID());
//			StitchingPlan.setQty(totalQty);
//			StitchingPlan.setPlanDate(DateTrx);
//			StitchingPlan.save();
//		}
//		if (StrobelLine_ID > 0)
//		{
//			X_ER_Strobel_Plan StroblePlan = new X_ER_Strobel_Plan(Env.getCtx(), 0, get_TrxName());
//			StroblePlan.setPP_Production_Line_ID(StrobelLine_ID);
//			StroblePlan.setER_DailyProduction_ID(mp.get_ID());
//			StroblePlan.setQty(totalQty);
//			StroblePlan.setPlanDate(DateTrx);
//			StroblePlan.save();
//		}
//		if (LastingLine_ID > 0)
//		{
//			X_ER_Lasting_Plan lastingplan = new X_ER_Lasting_Plan(Env.getCtx(), 0, get_TrxName());
//			lastingplan.setPP_Production_Line_ID(LastingLine_ID);
//			lastingplan.setER_DailyProduction_ID(mp.get_ID());
//			lastingplan.setQty(totalQty);
//			lastingplan.setPlanDate(DateTrx);
//			lastingplan.save();
//		}
//		if (PackingLine_ID > 0)
//		{
//			X_ER_Packing_Plan packplan = new X_ER_Packing_Plan(Env.getCtx(), 0, get_TrxName());
//			packplan.setPP_Production_Line_ID(PackingLine_ID);
//			packplan.setER_DailyProduction_ID(mp.get_ID());
//			packplan.setQty(totalQty);
//			packplan.setPlanDate(DateTrx);
//			packplan.save();
//		}
		
		return "";
	}

}
