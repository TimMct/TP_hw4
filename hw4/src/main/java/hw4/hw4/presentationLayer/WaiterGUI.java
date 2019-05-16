package hw4.hw4.presentationLayer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import hw4.hw4.businessLayer.MenuItem;
import hw4.hw4.businessLayer.Order;
import hw4.hw4.businessLayer.Restaurant;
import hw4.hw4.businessLayer.RestaurantProcessing;
import hw4.hw4.dataLayer.FileWriter;
import hw4.hw4.reflection.Reflection;

public class WaiterGUI {
	
	private RestaurantProcessing rp;
	
	
	private JFrame waiterWindow;
	
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private JPanel p6;
	private JPanel p7;
	
	
	private JLabel intro;
	private JLabel table;
	private JTextField tableNr;
	private JLabel chooseProduct;
	private JComboBox<MenuItem> menu;
	private JLabel quantity;
	private JTextField quantValue;
	private JButton addProduct;
	private JButton createOrder;
	private JButton prepareBill;
	private JButton viewOrders;
	private JLabel error;
	
	
	private Order o;
	private ArrayList<MenuItem> chosenProducts;
	
	
	
	public WaiterGUI(Restaurant r) {
		
		rp = r;
		
		waiterWindow = new JFrame("Waiter");
		waiterWindow.setLocation(500, 500);
		waiterWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		waiterWindow.setSize(600, 300);
		waiterWindow.setLayout(new GridLayout(7, 1));
		
		p1 = new JPanel();
		intro = new JLabel("Operations for orders");
		p1.add(intro);
		
		
		p2 = new JPanel();
		table = new JLabel("Table number");
		tableNr = new JTextField(5);
		p2.add(table);
		p2.add(tableNr);
		
		
		p3 = new JPanel();
		chooseProduct = new JLabel("Choose menu item    ");
		p3.add(chooseProduct);
		
		
		menu = new JComboBox<MenuItem>(rp.getMenuArray());
		p3.add(menu);
		
		quantity = new JLabel("  quantity  ");
		p3.add(quantity);
		
		quantValue = new JTextField(5);
		p3.add(quantValue);
		
		addProduct = new JButton("Add to order");
		
		o = new Order(0);
		chosenProducts = new ArrayList<MenuItem>();
		
		addProduct.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				menu = new JComboBox<MenuItem>(rp.getMenuArray());
				
				error.setText("");
				int quant;
				
				try {
					quant = Integer.parseInt(quantValue.getText());
				}catch(NumberFormatException e) {
					error.setText("Invalid quantity");
					return;
				}
				
				if(quant == 0) {
					error.setText("Don't forget the quantity");
					return;
				}
				
				MenuItem m = (MenuItem)menu.getSelectedItem();
				
				if(m.getQuantity() < quant) {
					error.setText("Not enough quantity for this product");
					return;
				}
				
				while(quant > 0) {
					chosenProducts.add(m);
					quant--;
				}
				
				quantValue.setText("");
			}
		});
		
		p3.add(addProduct);
		
		p4 = new JPanel();
		error = new JLabel();
		p4.add(error);
		
		
		p5 = new JPanel();
		createOrder = new JButton("Create order");
		
		createOrder.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				error.setText("");
				
				int tNr;
				
				try {
					tNr = Integer.parseInt(tableNr.getText());
				}catch(NumberFormatException e) {
					error.setText("Table number invalid");
					return;
				}
				
				
				
				if(tNr <= 0 || tNr > 100) {
					error.setText("Tables available between 1 and 100");
					return;
				}
				
				
				if(chosenProducts.size() == 0) {
					error.setText("You forgot to choose some products");
					return;
				}
				
				o = new Order(tNr);
				
				
				rp.addOrder(o, chosenProducts);
				
				chosenProducts = new ArrayList<MenuItem>();
				
				tableNr.setText("");
			}
		});
		
		p5.add(createOrder);
		
		p6 = new JPanel();
		prepareBill = new JButton("Prepare the bill");
		
		prepareBill.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				if(o.getTable() == 0) {
					error.setText("Make the order before you pay");
					return;
				}
				
				double totalPrice = rp.computePriceForOrder(o);
				ArrayList<MenuItem> prods = rp.getChosenProducts(o);
				
				FileWriter fW = new FileWriter();
				fW.computeBill(o, prods, totalPrice);
				
			}
		});
		
		p6.add(prepareBill);
		
		p7 = new JPanel();
		viewOrders = new JButton("View all orders");
		
		viewOrders.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				ArrayList<Order> orders = rp.getOrdersNames();
				
				if(orders.size() == 0) {
					error.setText("There is no order yet");
					return;
				}
				
				JFrame tableWindow = new JFrame("Table of orders");
				tableWindow.setSize(800, 400);
				tableWindow.setLocationRelativeTo(null);
				tableWindow.setLayout(new BorderLayout());
			
				JTable table = Reflection.createTable(orders);
				
				tableWindow.add(new JScrollPane(table), BorderLayout.CENTER);
				tableWindow.setVisible(true);
				
			}
		});
		
		p7.add(viewOrders);
		
		
		waiterWindow.add(p1);
		waiterWindow.add(p2);
		waiterWindow.add(p3);
		waiterWindow.add(p4);
		waiterWindow.add(p5);
		waiterWindow.add(p6);
		waiterWindow.add(p7);
		
		waiterWindow.setVisible(true);
	}
	
	
	public void end() {
		waiterWindow.dispose();
	}
	
	
}
