package hw4.hw4.businessLayer;

import java.io.Serializable;

public class MenuItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	private int quantity;
	
	public MenuItem(String n, double p, int q) {
		name = n;
		price = p;
		quantity = q;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	
	public void setPrice(double p) {
		price = p;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setQuantity(int q) {
		quantity = q;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public double computePrice() {
		return getPrice();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
