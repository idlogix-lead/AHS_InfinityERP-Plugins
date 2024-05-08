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
import org.eri.model.MMasterProduction;
import org.eri.model.MProduction;
import org.eri.model.X_ER_Cutting_Plan;
import org.eri.model.X_ER_Lasting_Plan;
import org.eri.model.X_ER_Packing_Plan;
import org.eri.model.X_ER_Stitching_Plan;
import org.eri.model.X_ER_Strobel_Plan;

public class AddProductOnDP_EvenSizes extends SvrProcess {

	int id = 0;

	int C_Order_ParentProduct_ID=0;
	
	int M_Locator_ID = 0;
	BigDecimal totalQty = Env.ZERO;
	BigDecimal currentTicketQty = Env.ZERO;
	BigDecimal pack_size = Env.ZERO;
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
			else if (name.equals("PackingSize"))
			{
				pack_size = para[i].getParameterAsBigDecimal();
			}
			else if (name.equals("DateTrx"))
				DateTrx = para[i].getParameterAsTimestamp();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		pack_size = new BigDecimal(1);
		if (currentTicketQty.compareTo(Env.ZERO) <= 0 || pack_size.compareTo(Env.ZERO) <= 0)
		{
			return "";	
		}
		List<Integer> prod_ids      = new ArrayList<Integer>();
		List<Integer> order_lines      = new ArrayList<Integer>();
		List<Double> pending_qty    = new ArrayList<Double>();
		List<Double> packing_qty    = new ArrayList<Double>();
		List<Double> rounded_qty    = new ArrayList<Double>();
		List<Integer> masterprodline_ids      = new ArrayList<Integer>();
		double total_pp_qty=0.0;
		double productionQtyTillNow=0.0;
		double ticket_qty=currentTicketQty.doubleValue();		
		double packing_size = pack_size.doubleValue();
		
		MDailyProduction  mp = new MDailyProduction(Env.getCtx(), id, get_TrxName());
		MMasterProduction master = new MMasterProduction(getCtx(), mp.getER_MasterProduction_ID(), null);
		String view_name = "";
		if(master.getC_Order_ID()==0 || master.getC_Order_ID() == -1)
			view_name = "adempiere.m_plan_vs_production_v_mp_only";
		else
			view_name = "adempiere.m_plan_vs_production_v";
		C_Order_ParentProduct_ID = mp.getC_Order_ParentProduct_ID();
		if(  C_Order_ParentProduct_ID == 0)
			 C_Order_ParentProduct_ID =mp.getM_Parent_Product_ID();
		String strSQL = "select ol.c_orderline_id,ppv.m_product_id,ppv.er_masterproductionline_id,SUM(planqty-ticketqty) pending_qty,al.qty packing_qty \n"
		+ "FROM  "+ view_name+" as ppv "
		+ "LEFT JOIN adempiere.m_product p ON ppv.m_product_id =  p.m_product_id\n"
		+ "left JOIN adempiere.idl_assortment_line al ON p.idl_assortment_id = al.idl_assortment_id and p.er_size_id = al.er_size_id\n"
		+ "left join adempiere.er_masterproductionline mpl ON ppv.er_masterproductionline_id = mpl.er_masterproductionline_id\n"
		+ "left join adempiere.c_orderline ol ON mpl.c_orderline_id = ol.c_orderline_id\n"
		+ "where ppv.er_masterproduction_id = "+ mp.getER_MasterProduction_ID()+"\n"
		+ " and ppv.c_order_parentproduct_id = "+ C_Order_ParentProduct_ID +"\n"
		+ "group by ol.c_orderline_id,ppv.er_masterproductionline_id,ppv.m_product_id,al.qty\n"
		+ "having SUM(planqty-ticketqty) > 0 \n"
		+ "order by ppv.m_product_id";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			
			pstmt = DB.prepareStatement (strSQL.toString(), null);
			rs = pstmt.executeQuery ();
			while (rs.next ())		
			{
				masterprodline_ids.add(rs.getInt("er_masterproductionline_id"));				
				prod_ids.add(rs.getInt("m_product_id"));
				order_lines.add(rs.getInt("c_orderline_id"));
				pending_qty.add(rs.getDouble("pending_qty"));
				packing_qty.add(rs.getDouble("packing_qty"));
				total_pp_qty+=rs.getDouble("pending_qty");
			}
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		boolean makeAll = total_pp_qty<=ticket_qty?true:false;
		 packing_size = packing_size > ticket_qty ? ticket_qty:packing_size; 
		 ticket_qty = ticket_qty%packing_size==0.0?ticket_qty:ticket_qty- (ticket_qty%packing_size);
		 ticket_qty = ticket_qty>total_pp_qty?total_pp_qty:ticket_qty;
		 double ticket_cartons = ticket_qty/packing_size;	 
		 List<Double> actual_qty = new ArrayList<Double>();
		 List<Double> carton_wise_qty = new ArrayList<Double>();
		 double pending_cartons=0.0;
		 double produceable_carton=0.0;
		 for(int i=0;i<pending_qty.size();i++) 
		 {
			 packing_size = packing_qty.get(i);
			 actual_qty.add(0.0);
			 carton_wise_qty.add(pending_qty.get(i)/packing_qty.get(i));
			 pending_cartons+=pending_qty.get(i)/packing_qty.get(i);
			 produceable_carton+=(pending_qty.get(i))-(pending_qty.get(i)%packing_qty.get(i));
			 
		 }
	 	if(!makeAll) {
	 		 while(sum(actual_qty)<ticket_qty && sum(actual_qty)<produceable_carton) 
	 		 {
	 			 for(int i=0;i<pending_qty.size();i++)
	 			 {
	 				 if(sum(actual_qty)+packing_qty.get(i)>ticket_qty)
	 					 break;
	 				 if(actual_qty.get(i)+packing_qty.get(i) <= pending_qty.get(i))
	 					 actual_qty.set(i, actual_qty.get(i)+packing_qty.get(i));
	 			 }
	 		 }
	 	}
	 	else {
	 		for(int i=0;i<pending_qty.size();i++) 
	 		{
				 actual_qty.set(i, pending_qty.get(i));
			 }
	 	}
		for(int i=0;i<pending_qty.size();i++) 
		{
			if(actual_qty.get(i)> 0.0) {
				MProduction pline = new MProduction(Env.getCtx(), 0, get_TrxName());
				pline.setAD_Org_ID(mp.getAD_Org_ID());
				pline.setM_Product_ID(prod_ids.get(i));
				pline.setC_OrderLine_ID(order_lines.get(i));
				pline.setProductionQty(new BigDecimal(actual_qty.get(i)));
				pline.setMovementDate(mp.getDateDoc());
				pline.setER_DailyProduction_ID(mp.get_ID());
				pline.setM_Locator_ID(M_Locator_ID);			
//				pline.setER_MasterProductionLine_ID(mp.getER_MasterProduction_ID());
				pline.setER_MasterProductionLine_ID(masterprodline_ids.get(i));
				boolean ret = false;
				ret = pline.save();
				//pline.saveEx();
				if (ret)
				{
					commit();
					ProcessInfoParameter pi1 = new ProcessInfoParameter("Record_ID", 1000024,"","","");
					ProcessInfo pi = new ProcessInfo("", 53226,0,0);
					pi.setParameter( new ProcessInfoParameter[] {pi1} );
					MProcess pr = new Query(Env.getCtx(), MProcess.Table_Name, "value=?", null)
					                        .setParameters(new Object[]{"M_Production_Create"})
					                        .first();
					if (pr==null) 
					{
				      return "Error 1";
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
						return "ERROR 2";
					}
				}
			}
		}		
		return "";
	}
	public static double sum(List<Double> list) {
		double sum=0.0;
		for(double d:list)
		sum+=d;
		return sum;
	}
}
