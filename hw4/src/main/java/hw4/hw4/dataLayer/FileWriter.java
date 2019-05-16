package hw4.hw4.dataLayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


import hw4.hw4.businessLayer.CompositeProduct;
import hw4.hw4.businessLayer.MenuItem;
import hw4.hw4.businessLayer.Order;

public class FileWriter {

	private static int fileId = 0;
	private StringBuilder fileName;
	private PrintWriter writer;
	
	public FileWriter() {
		fileId++;
		fileName = new StringBuilder();
		fileName.append("order");
		fileName.append(fileId);
		fileName.append(".txt");
		writer = null;
	}
	
	public void computeBill(Order o, ArrayList<MenuItem> chosenProducts, double price) {
		
		try{
			writer = new PrintWriter(fileName.toString(), "UTF-8");
		}catch(FileNotFoundException e) {
		}catch(UnsupportedEncodingException e) {
		}
		
		if(!writer.equals(null)) {
			writer.println();
			writer.println(o);
			
			writer.println();
			writer.println("Products: ");
			for(MenuItem m : chosenProducts) {
				
				if(!m.getName().contains("&")) {
					writer.print(m+"   ");
					writer.print(m.getPrice());
					
				} else {
					for(MenuItem n : ((CompositeProduct) m).getComponents()) {
						writer.print("composite   ");
						writer.print(n+"   ");
					}
					writer.print(m.getPrice());
				}
				
				writer.println();
			}
			
			writer.println();
			writer.println("Total price is:  "+price);
			
			writer.close();
		}
		
	}

}
