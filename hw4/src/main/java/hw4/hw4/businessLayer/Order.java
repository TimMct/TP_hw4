package hw4.hw4.businessLayer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


//am folosit cod de aici https://stackabuse.com/how-to-get-current-date-and-time-in-java/
//pentru a seta data unui nou Order ca data curenta

@SuppressWarnings("serial")
public class Order implements Serializable{
	/**
	 * 
	 */
	
	public int oId;
	public Date date;
	public int table;
	public static int inc = -1;
	
	
	public Order(int nrOfTable) {
		oId = ++inc;  //auto-increment
		date = new Date(System.currentTimeMillis());  
		table = nrOfTable;
	}
	
	public int getId() {
		return oId;
	}
	
	public int getTable() {
		return table;
	}
	
	@Override
	public int hashCode() {//formula proprie
		return ((oId + table + 7) % 5 + date.hashCode() + 11 ) % 13;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Order) {
			if(date.equals(((Order)obj).date) && table == ((Order)obj).table){
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm");
		return "Order for table #"+table+" on the date "+formatter.format(date);
	}	
}
