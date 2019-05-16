package hw4.hw4.businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class Restaurant extends Observable implements RestaurantProcessing, Serializable{
	
	private static final long serialVersionUID = 1L;
	private  HashMap<Order, ArrayList<MenuItem>> orders;
	private  ArrayList<Order> orderNames;
	private  ArrayList<MenuItem> menu;
	
	public Restaurant() {
		orders = new HashMap<Order, ArrayList<MenuItem>>();
		orderNames = new ArrayList<Order>();
		menu = new ArrayList<MenuItem>();
	}
	
	
	
	public boolean isWellFormed() {
		if(this instanceof Restaurant)
			return true;
		else 
			return false;
	}
	
	
	public void createMenuItem(MenuItem m) {
		
		assert !menu.contains(m);
		assert isWellFormed();
		
		if(!menu.contains(m)) {
			menu.add(m);
		}
		
		assert isWellFormed();
		assert menu.contains(m);
	}



	public boolean deleteMenuItem(String name) {
		
		for(MenuItem m : menu) {
			if(!name.equals(m.getName()))
				assert false;//!contains()
		}
		
		assert isWellFormed();
		
		int i = 0;
		for(MenuItem m : menu) {
			if(name.equals(m.getName())) {
				menu.remove(i);
				return true;
			}
			i++;
		}
		
		assert isWellFormed();
		
		for(MenuItem m : menu) {
			if(name.equals(m.getName()))
				assert false;//contains()
		}
		
		
		return false;
	}



	public void editMenuItem(MenuItem m) {
		
		assert !menu.contains(m);
		
		assert isWellFormed();
		
		if(deleteMenuItem(m.getName()))
			createMenuItem(m);
		
		assert menu.contains(m);
		
		assert isWellFormed();
		
	}



	public ArrayList<MenuItem> getMenu() {
		
		assert menu instanceof ArrayList<?>;
		
		assert isWellFormed();
		
		
		return menu;
	}

	public MenuItem[] getMenuArray() {
		
		assert menu instanceof ArrayList<?>;
		
		assert isWellFormed();
		
		
		MenuItem[] array = new MenuItem[menu.size()];
		for(int i = 0; i < menu.size(); i++) {
			array[i] = menu.get(i);
		}
		
		assert isWellFormed();
		
		assert array instanceof MenuItem[]; 
		
		
		return array;
	}
	
	
	public BaseProduct[] getBaseMenu() {
		
		assert menu instanceof ArrayList<?>;
		
		assert isWellFormed();
		
		
		ArrayList<BaseProduct> baseMenu = new ArrayList<BaseProduct>();
		
		for(MenuItem m : menu) {
			if(m instanceof BaseProduct) {
				baseMenu.add((BaseProduct)m);
			}
		}
		BaseProduct[] array = new BaseProduct[baseMenu.size()];
		
		for(int i = 0; i < baseMenu.size(); i++) {
			array[i] = baseMenu.get(i);
		}
		
		
		assert isWellFormed();
		
		assert array instanceof BaseProduct[];
		
		
		return array;
	}



	public void addOrder(Order o, ArrayList<MenuItem> chosenProducts) {
		
		assert o instanceof Order && chosenProducts instanceof ArrayList<?>;
		assert !orders.containsKey(o);
		
		assert isWellFormed();
		
		
		orders.put(o,  chosenProducts);	
		orderNames.add(o);
		
		setChanged(); 
		notifyObservers(new Order(3));
		
		
		assert isWellFormed();
		
		assert orders.containsKey(o);
		
	}

	public ArrayList<Order> getOrdersNames() {
		
		assert orderNames instanceof ArrayList<?>;
		
		assert isWellFormed();
		
		
		return orderNames;
	}

	public HashMap<Order, ArrayList<MenuItem>> getOrders() {
		
		assert orders instanceof HashMap<?, ?>;
		
		assert isWellFormed();
		
		
		return orders;
	}
	
	public ArrayList<MenuItem> getChosenProducts(Order o) {
		
		assert orders.get(o) instanceof ArrayList<?> && !o.equals(null);
		
		assert isWellFormed();
		
		
		
		return orders.get(o);
	}

	public double computePriceForOrder(Order o) {
		
		double totalPrice = 0;
		
		assert o instanceof Order && !o.equals(null);
		
		assert isWellFormed();
		
		
		ArrayList<MenuItem> chosenProducts = new ArrayList<MenuItem>();
		if(orders.containsKey(o)) {
			chosenProducts = orders.get(o);
			for(MenuItem m : chosenProducts) {
				totalPrice += m.computePrice();
			}
		}

		assert isWellFormed();
		
		assert totalPrice >= 0;
		
		return totalPrice;
	}


}
