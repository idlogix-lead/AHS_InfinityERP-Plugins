package com.idlogix.models;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MClient;
import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProductionLine;
import org.compiere.model.MProjectLine;
import org.compiere.model.MStorageOnHand;
import org.compiere.model.MTable;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MProduction extends org.compiere.model.MProduction {

    public static final String COLUMNNAME_ER_DailyProduction_ID = "ER_DailyProduction_ID";
    public static final String COLUMNNAME_ER_MasterProductionLine_ID = "ER_MasterProductionLine_ID";
    public static final String COLUMNNAME_M_AttributeValue_ID = "M_AttributeValue_ID";

    public static final String COLUMNNAME_CuttingPlanQty = "CuttingPlanQty";
    public static final String COLUMNNAME_StitchingPlanQty = "StitchingPlanQty";
    public static final String COLUMNNAME_StrobelPlanQty = "StrobelPlanQty";
    public static final String COLUMNNAME_LastingPlanQty = "LastingPlanQty";
    public static final String COLUMNNAME_PackingPlanQty = "PackingPlanQty";

    public void setPackingPlanQty (BigDecimal PackingPlanQty)
   	{
   		set_Value (COLUMNNAME_PackingPlanQty, PackingPlanQty);
   	}

   	/** Get Production Quantity.
   		@return Quantity of products to produce
   	  */
   	public BigDecimal getPackingPlanQty () 
   	{
   		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PackingPlanQty);
   		if (bd == null)
   			 return Env.ZERO;
   		return bd;
   	}
   	
   	

    public void setLastingPlanQty (BigDecimal LastingPlanQty)
   	{
   		set_Value (COLUMNNAME_LastingPlanQty, LastingPlanQty);
   	}

   	/** Get Production Quantity.
   		@return Quantity of products to produce
   	  */
   	public BigDecimal getLastingPlanQty () 
   	{
   		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LastingPlanQty);
   		if (bd == null)
   			 return Env.ZERO;
   		return bd;
   	}
   	
   	
    public void setStrobelPlanQty (BigDecimal StrobelPlanQty)
   	{
   		set_Value (COLUMNNAME_StrobelPlanQty, StrobelPlanQty);
   	}

   	/** Get Production Quantity.
   		@return Quantity of products to produce
   	  */
   	public BigDecimal getStrobelPlanQty () 
   	{
   		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_StrobelPlanQty);
   		if (bd == null)
   			 return Env.ZERO;
   		return bd;
   	}
    
    public void setStitchingPlanQty (BigDecimal StitchingPlanQty)
	{
		set_Value (COLUMNNAME_StitchingPlanQty, StitchingPlanQty);
	}

	/** Get Production Quantity.
		@return Quantity of products to produce
	  */
	public BigDecimal getStitchingPlanQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_StitchingPlanQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
    
    
    public void setCuttingPlanQty (BigDecimal CuttingPlanQty)
	{
		set_Value (COLUMNNAME_CuttingPlanQty, CuttingPlanQty);
	}

	/** Get Production Quantity.
		@return Quantity of products to produce
	  */
	public BigDecimal getCuttingPlanQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CuttingPlanQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
    
	public void setER_DailyProduction_ID (int ER_DailyProduction_ID)
	{
		if (ER_DailyProduction_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_DailyProduction_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_DailyProduction_ID, Integer.valueOf(ER_DailyProduction_ID));
	}

	/** Get Daily Production.
		@return Daily Production	  */
	public int getER_DailyProduction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_DailyProduction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	
	public void setER_MasterProductionLine_ID (int ER_MasterProductionLine_ID)
	{
		if (ER_MasterProductionLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ER_MasterProductionLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ER_MasterProductionLine_ID, Integer.valueOf(ER_MasterProductionLine_ID));
	}

	/** Get Master Production Line.
		@return Master Production Line	  */
	public int getER_MasterProductionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ER_MasterProductionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
    public org.compiere.model.I_M_AttributeValue getM_AttributeValue() throws RuntimeException
    {
		return (org.compiere.model.I_M_AttributeValue)MTable.get(getCtx(), org.compiere.model.I_M_AttributeValue.Table_Name)
			.getPO(getM_AttributeValue_ID(), get_TrxName());	}

	/** Set Attribute Value.
		@param M_AttributeValue_ID 
		Product Attribute Value
	  */
	public void setM_AttributeValue_ID (int M_AttributeValue_ID)
	{
		if (M_AttributeValue_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeValue_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeValue_ID, Integer.valueOf(M_AttributeValue_ID));
	}

	/** Get Attribute Value.
		@return Product Attribute Value
	  */
	public int getM_AttributeValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
	
	
	public MProduction(MOrderLine line) {
		super(line);
		// TODO Auto-generated constructor stub
	}

	public MProduction(MProjectLine line) {
		super(line);
		// TODO Auto-generated constructor stub
	}

	public MProduction(Properties ctx, int M_Production_ID, String trxName) {
		super(ctx, M_Production_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MProduction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	
	public int createLines(boolean mustBeStocked) {
		
		lineno = 100;

		count = 0;

		// product to be produced
		MProduct finishedProduct = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		

		MProductionLine line = new MProductionLine( this );
		line.setLine( lineno );
		line.setM_Product_ID( finishedProduct.get_ID() );
		line.setM_Locator_ID( getM_Locator_ID() );
		line.setMovementQty( getProductionQty());
		line.setPlannedQty(getProductionQty());
		
		line.saveEx();
		count++;
		
		createLines(mustBeStocked, finishedProduct, getProductionQty());
		
		return count;
	}

	protected int createLines(boolean mustBeStocked, MProduct finishedProduct, BigDecimal requiredQty) {
		
		int defaultLocator = 0;
		
		MLocator finishedLocator = MLocator.get(getCtx(), getM_Locator_ID());
		
		int M_Warehouse_ID = finishedLocator.getM_Warehouse_ID();
		
		int asi = 0;

		// products used in production
		String sql = "SELECT M_ProductBom_ID, BOMQty,C_Activity_ID" + " FROM M_Product_BOM"
				+ " WHERE M_Product_ID=" + finishedProduct.getM_Product_ID() + " ORDER BY Line";
		
		try (PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());) {			

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				lineno = lineno + 10;
				int BOMProduct_ID = rs.getInt(1);
				BigDecimal BOMQty = rs.getBigDecimal(2);
				BigDecimal BOMMovementQty = BOMQty.multiply(requiredQty);
				int C_Activity_ID = rs.getInt(3);
				MProduct bomproduct = new MProduct(Env.getCtx(), BOMProduct_ID, get_TrxName());
				

				if ( bomproduct.isBOM() && bomproduct.isPhantom() )
				{
					createLines(mustBeStocked, bomproduct, BOMMovementQty);
				}
				else
				{

					defaultLocator = bomproduct.getM_Locator_ID();
					if ( defaultLocator == 0 ) 
						defaultLocator = getM_Locator_ID();

					if (!bomproduct.isStocked())
					{					
						MProductionLine BOMLine = null;
						BOMLine = new MProductionLine( this );
						BOMLine.setLine( lineno );
						BOMLine.setM_Product_ID( BOMProduct_ID );
						BOMLine.setM_Locator_ID( defaultLocator );  
						BOMLine.setQtyUsed(BOMMovementQty );
						BOMLine.setPlannedQty( BOMMovementQty );
						BOMLine.setM_Locator_ID( getM_Locator_ID() );  
						BOMLine.set_ValueOfColumn("C_Activity_ID",C_Activity_ID);
						BOMLine.saveEx(get_TrxName());

						lineno = lineno + 10;
						count++;					
					}
					else if (BOMMovementQty.signum() == 0) 
					{
						MProductionLine BOMLine = null;
						BOMLine = new MProductionLine( this );
						BOMLine.setLine( lineno );
						BOMLine.setM_Product_ID( BOMProduct_ID );
						BOMLine.setM_Locator_ID( defaultLocator );  
						BOMLine.setQtyUsed( BOMMovementQty );
						BOMLine.setPlannedQty( BOMMovementQty );
						BOMLine.setM_Locator_ID( getM_Locator_ID() );  
						BOMLine.set_ValueOfColumn("C_Activity_ID",C_Activity_ID);
						BOMLine.saveEx(get_TrxName());

						lineno = lineno + 10;
						count++;
					}
					else
					{

						// BOM stock info
						MStorageOnHand[] storages = null;
						MProduct usedProduct = MProduct.get(getCtx(), BOMProduct_ID);
						defaultLocator = usedProduct.getM_Locator_ID();
						if ( defaultLocator == 0 )
							defaultLocator = getM_Locator_ID();
						if (usedProduct == null || usedProduct.get_ID() == 0)
							return 0;

						MClient client = MClient.get(getCtx());
						MProductCategory pc = MProductCategory.get(getCtx(),
								usedProduct.getM_Product_Category_ID());
						String MMPolicy = pc.getMMPolicy();
						if (MMPolicy == null || MMPolicy.length() == 0) 
						{ 
							MMPolicy = client.getMMPolicy();
						}

						storages = MStorageOnHand.getWarehouse(getCtx(), M_Warehouse_ID, BOMProduct_ID, 0, null,
								MProductCategory.MMPOLICY_FiFo.equals(MMPolicy), true, 0, get_TrxName());

						MProductionLine BOMLine = null;
						int prevLoc = -1;
						int previousAttribSet = -1;
						// Create lines from storage until qty is reached
						for (int sl = 0; sl < storages.length; sl++) {

							BigDecimal lineQty = storages[sl].getQtyOnHand();
							if (lineQty.signum() != 0) {
								if (lineQty.compareTo(BOMMovementQty) > 0)
									lineQty = BOMMovementQty;


								int loc = storages[sl].getM_Locator_ID();
								int slASI = storages[sl].getM_AttributeSetInstance_ID();
								int locAttribSet = new MAttributeSetInstance(getCtx(), asi,
										get_TrxName()).getM_AttributeSet_ID();

								// roll up costing attributes if in the same locator
								if (locAttribSet == 0 && previousAttribSet == 0
										&& prevLoc == loc) {
									BOMLine.setQtyUsed(BOMLine.getQtyUsed()
											.add(lineQty));
									BOMLine.setPlannedQty(BOMLine.getQtyUsed());
									BOMLine.setM_Locator_ID( getM_Locator_ID() );  
									BOMLine.set_ValueOfColumn("C_Activity_ID",C_Activity_ID);
									BOMLine.saveEx(get_TrxName());

								}
								// otherwise create new line
								else {
									BOMLine = new MProductionLine( this );
									BOMLine.setLine( lineno );
									BOMLine.setM_Product_ID( BOMProduct_ID );
									BOMLine.setM_Locator_ID( loc );
									BOMLine.setQtyUsed( lineQty);
									BOMLine.setPlannedQty( lineQty);
									if ( slASI != 0 && locAttribSet != 0 )  // ie non costing attribute
										BOMLine.setM_AttributeSetInstance_ID(slASI);

									BOMLine.setM_Locator_ID( getM_Locator_ID() );  
									BOMLine.set_ValueOfColumn("C_Activity_ID",C_Activity_ID);
									BOMLine.saveEx(get_TrxName());

									lineno = lineno + 10;
									count++;
								}
								prevLoc = loc;
								previousAttribSet = locAttribSet;
								// enough ?
								BOMMovementQty = BOMMovementQty.subtract(lineQty);
								if (BOMMovementQty.signum() == 0)
									break;
							}
						} // for available storages

						// fallback
						if (BOMMovementQty.signum() != 0 ) {
							if (!mustBeStocked)
							{

								// roll up costing attributes if in the same locator
								if ( previousAttribSet == 0
										&& prevLoc == defaultLocator) {
									BOMLine.setQtyUsed(BOMLine.getQtyUsed()
											.add(BOMMovementQty));
									BOMLine.setPlannedQty(BOMLine.getQtyUsed());
									BOMLine.setM_Locator_ID( getM_Locator_ID() );  
									BOMLine.set_ValueOfColumn("C_Activity_ID",C_Activity_ID);
									BOMLine.saveEx(get_TrxName());

								}
								// otherwise create new line
								else {

									BOMLine = new MProductionLine( this );
									BOMLine.setLine( lineno );
									BOMLine.setM_Product_ID( BOMProduct_ID );
									BOMLine.setM_Locator_ID( defaultLocator );  
									BOMLine.setQtyUsed( BOMMovementQty);
									BOMLine.setPlannedQty( BOMMovementQty);
									BOMLine.setM_Locator_ID( getM_Locator_ID() );  
									BOMLine.set_ValueOfColumn("C_Activity_ID",C_Activity_ID);
									BOMLine.saveEx(get_TrxName());
									lineno = lineno + 10;
									count++;
								}

							}
							else
							{
								throw new AdempiereUserError("Not enough stock of " + BOMProduct_ID);
							}
						}
					}
				}
			} // for all bom products
		} catch (Exception e) {
			throw new AdempiereException("Failed to create production lines", e);
		}

		return count;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
