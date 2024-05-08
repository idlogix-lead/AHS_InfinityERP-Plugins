package com.idlogix.processes;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class helper {
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_RESET = "\u001B[0m";
public static void print(Object o) {
	System.out.print(o);
}
public static void lbreak() {
	System.out.println();
}

public static boolean isEmpty(double[] list) {
	for(double qty:list) {
		if(qty> 0.0)
			return false;
	}
	return true;
}
public static void printList(String msg,List<Double> list,String s) {
	print(msg+" ");
	for(double d:list) {
		print(d+" ");
	}
	print (" = "+s);
	lbreak();
}
public static double sum(double[] list) {
	double sum=0.0;
	for(double d:list)
	sum+=d;
	return sum;
}
public static double sum(List<Double> list) {
	double sum=0.0;
	for(double d:list)
	sum+=d;
	return sum;
}
public static void printArray(String msg,double[] list,String s) {
	print(msg+" ");
	for(double d:list) {
		print(d+" ");
	}
	print (" = "+s);
	lbreak();
}


	
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
	
		
//		print(new BigDecimal(6.818181).setScale(2, RoundingMode.HALF_UP).doubleValue());
		lbreak();
		
		double total_order=38.0;
		double ticket_qty=25.0;		
		double packing_size = 12.0;
		double t_proportion = (ticket_qty/total_order ) ;
	 String[] size_names = {"36","37" ,"38" ,"39","40"};
 double[] size_wise_qty =   {19,10,9};
 double[] pending_qty =     {19,10,9};
 double actual_sum = 0.0;
 int counter = 1;
 
 packing_size = packing_size > ticket_qty ? ticket_qty:packing_size; 
 ticket_qty = ticket_qty%packing_size==0.0?ticket_qty:ticket_qty- (ticket_qty%packing_size);
 ticket_qty = ticket_qty>total_order?total_order:ticket_qty; 
 
 
 while(!isEmpty(pending_qty)) {
	 	 
	 ticket_qty = total_order - actual_sum > ticket_qty ? ticket_qty- (ticket_qty%packing_size) : total_order - actual_sum;
	 t_proportion = (ticket_qty/(total_order-actual_sum) ) ;
	 print(counter+"Ticket Quantity = " + ticket_qty + "  Pending Order = "+(total_order - actual_sum)+"   Packing size = "+packing_size);
	 lbreak();
	 
/////////////////////////////////////////////////////////////////////////////////////////////// Calculate Rounded Quantities
	 
	 List<Double> rounded_qty = new ArrayList<Double>();	 
	 for(int i=0;i<pending_qty.length;i++) {
		 	double multiplier = (pending_qty[i]*t_proportion) / packing_size;
		 	if(multiplier>1.0)
		 	multiplier = Math.floor(multiplier);
		 	if(multiplier*packing_size > pending_qty[i])
		 		rounded_qty.add(Math.floor(pending_qty[i]));
		 	else
		 		rounded_qty.add(Math.floor(multiplier*packing_size));	
		 }
	 printList("Initial Rounded Values = ",rounded_qty,Double.toString(sum(rounded_qty)));

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 double pair_wise_diff_sum=0.0;
	 for(int i=0;i<pending_qty.length;i++) {
		 if(pending_qty[i] > rounded_qty.get(i)) 
			 {
				 pair_wise_diff_sum+=(pending_qty[i]*t_proportion)-rounded_qty.get(i);
			 }
		 }
	 pair_wise_diff_sum = Math.round(pair_wise_diff_sum);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
	 
	 
	 List<Double> actual_qty = new ArrayList<Double>();
	 double limit = 0.0;
	 for(int i=0;i<pending_qty.length;i++) { 
		 double adjustment = 0.0; 
		 double qty = rounded_qty.get(i);
		 int added=0;
		 while( Math.ceil(rounded_qty.get(i)+adjustment) < pending_qty[i]   &&  adjustment < packing_size  && limit < pair_wise_diff_sum) {
			 qty++; 
			 adjustment+=1;	
			 limit+=1;
		 }

		 actual_qty.add(qty);
		 actual_sum+=qty;
		 pending_qty[i]-=qty;
		 }
	 
	 
	 
	 
	 
	 printList("Actual Values          = ",actual_qty,Double.toString(sum(actual_qty)));
	 counter++;
	 printArray("Pending Values = ",pending_qty,Double.toString(sum(pending_qty)));
	 print("Actual Sum = "+actual_sum);
//	 lbreak();
	 
	 lbreak();
	 lbreak();
 }

	}
	
	
	
}

