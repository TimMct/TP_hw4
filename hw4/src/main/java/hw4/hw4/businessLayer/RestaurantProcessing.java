package hw4.hw4.businessLayer;

import java.util.ArrayList;
import java.util.HashMap;

public interface RestaurantProcessing {

	
	public boolean isWellFormed();
	
	//administrator
	/**
	 * @pre !contains()
	 * @invariant isWellFormed()
	 * @post contains()
	 */
	public void createMenuItem(MenuItem m); 
	
	/**
	 * @pre contains()
	 * @invariant isWellFormed()
	 * @post !contains()
	 */
	public boolean deleteMenuItem(String name);
	
	/**
	 * @pre !contains()
	 * @invariant isWellFormed()
	 * @post contains()
	 */
	public void editMenuItem(MenuItem m);
	
	/**
	 * @pre instanceof ArrayList<?>
	 * @invariant isWellFormed()
	 * @post 
	 */
	public ArrayList<MenuItem> getMenu();//view all in a JTable
	
	/**
	 * @pre instanceof ArrayList<?>
	 * @invariant isWellFormed()
	 * @post instanceof MenuItem[]
	 */
	public MenuItem[] getMenuArray();//for orders
	
	/**
	 * @pre menu instanceof ArrayList<?>
	 * @invariant isWellFormed()
	 * @post array instanceof BaseProduct[]
	 */
	public BaseProduct[] getBaseMenu();//for creating a composite product
	
	//waiter
	/**
	 * @pre o instanceof Order && chosenProducts instanceof ArrayList<?>
	 * !orders.containsKey(o)
	 * @invariant isWellFormed()
	 * @post orders.containsKey(o)
	 */
	public void addOrder(Order o, ArrayList<MenuItem> chosenProducts);
	
	/**
	 * @pre orderNames instanceof ArrayList<?>
	 * @invariant isWellFormed()
	 * @post 
	 */
	public ArrayList<Order> getOrdersNames();//view all orders
	
	/**
	 * @pre orders instanceof HashMap<?, ?>
	 * @invariant isWellFormed()
	 * @post 
	 */
	public HashMap<Order, ArrayList<MenuItem>> getOrders();//view all orders
	
	/**
	 * @pre o instanceof Order && !o.equals(null)
	 * @invariant isWellFormed()
	 * @post totalPrice >= 0
	 */
	public double computePriceForOrder(Order o);
	
	/**
	 * @pre o instanceof Order && !o.equals(null)
	 * @invariant isWellFormed()
	 * @post 
	 */
	public ArrayList<MenuItem> getChosenProducts(Order o);
}
