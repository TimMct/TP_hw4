package hw4.hw4.businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem{

	private ArrayList<MenuItem> components;
	
	
	public CompositeProduct(String n) {
		super(n, 0, 0);
		components = new ArrayList<MenuItem>();
	}
	
	
	public CompositeProduct(String n, ArrayList<MenuItem> c) {
		super(n, 0, 0);
		components = c;
	}

	
	public ArrayList<MenuItem> getComponents() {
		return components;
	}
	
	
	public void addProduct(MenuItem p) {
		components.add(p);
	}
	
	public void remProduct(MenuItem p) {
		components.remove(p);
	}
	
	
	@Override
	public double computePrice() {
		double totalPrice = 0;
		
		for(MenuItem m : components) {
			totalPrice += m.computePrice();
		}
		
		setPrice(totalPrice);//update price
		
		return totalPrice;
	}
	
	public int computeQuantity() {
		int leastQuantity = components.get(0).getQuantity();
		
		for(MenuItem m : components) {
			if(leastQuantity > m.getQuantity())
				leastQuantity = m.getQuantity();
		}
		setQuantity(leastQuantity);
		
		return leastQuantity;
	}
	
	
}
