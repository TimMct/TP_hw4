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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import hw4.hw4.businessLayer.BaseProduct;
import hw4.hw4.businessLayer.CompositeProduct;
import hw4.hw4.businessLayer.MenuItem;
import hw4.hw4.businessLayer.Restaurant;
import hw4.hw4.businessLayer.RestaurantProcessing;
import hw4.hw4.dataLayer.RestaurantSerializator;
import hw4.hw4.reflection.Reflection;

public class AdministratorGUI {
	
	private RestaurantProcessing rp;
	
	
	private JFrame adminWindow;
	private WaiterGUI waiterWindow;
	
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private JPanel p6;
	
	private JLabel intro;
	private JComboBox<String> operations;
	private JLabel baseProd;
	private JLabel compProd;
	private JComboBox<BaseProduct> products; 
	private JLabel nameLab;
	private JLabel priceLab;
	private JLabel quantLab;
	private JLabel error;
	
	private JTextField name;
	private JTextField price;
	private JTextField quantity;
	private JButton execute;
	private JButton combine;
	
	private JRadioButton bP;//base product check 
	private JRadioButton cP;//composite product check
	
	
	private ArrayList<MenuItem> selectedProducts;
	
	
	public AdministratorGUI(Restaurant r, ChefGUI cW) {
		
		rp = r;//operatiile de restaurant
		
		waiterWindow = new WaiterGUI(r);
		//chefWindow = cW;
		
		
		adminWindow = new JFrame("Admin");
		adminWindow.setLocation(150, 100);
		adminWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminWindow.setSize(600, 300);
		adminWindow.setLayout(new GridLayout(6, 1));
		
		p1 = new JPanel();
		intro = new JLabel("Operations for menu items");
		p1.add(intro);
		
		
		p2 = new JPanel();
		String[] variants = {"Add", "Edit", "Delete", "View all"};
		operations = new JComboBox<String>(variants);
		p2.add(operations);
		
		p3 = new JPanel();
		bP = new JRadioButton();
		cP = new JRadioButton();
		bP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bP.isSelected())
					cP.setSelected(false);
			}
		});
		
		cP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cP.isSelected())
					bP.setSelected(false);
			}
		});
		
		
		p3.add(bP);
		baseProd = new JLabel("Introduce a base product");
		p3.add(baseProd);
		
		p4 = new JPanel();
		p4.setLayout(new GridLayout(2, 3));
		
		
		nameLab = new JLabel("name");
		nameLab.setHorizontalAlignment(SwingConstants.CENTER);
		p4.add(nameLab);
		priceLab = new JLabel("price");
		priceLab.setHorizontalAlignment(SwingConstants.CENTER);
		p4.add(priceLab);
		quantLab = new JLabel("quantity");
		quantLab.setHorizontalAlignment(SwingConstants.CENTER);
		p4.add(quantLab);
		
		name = new JTextField(10);
		p4.add(name);
		price = new JTextField(10);
		p4.add(price);
		quantity = new JTextField(10);
		p4.add(quantity);
		
		
		p5 = new JPanel();
		
		p5.add(cP);
		compProd = new JLabel("Combine products");
		p5.add(compProd);
		
		
		
		//BaseProduct[] examples = {new BaseProduct("pizza", 12, 100), new BaseProduct("pasta", 15, 200)};
		//products = new JComboBox<BaseProduct>(examples);
		products = new JComboBox<BaseProduct>(r.getBaseMenu());
		
		p5.add(products);
		
		combine = new JButton("Add to composite product"); 
		
		selectedProducts = new ArrayList<MenuItem>();
		
		combine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!selectedProducts.contains((BaseProduct)products.getSelectedItem()))
					selectedProducts.add((BaseProduct)products.getSelectedItem());
			}
		});
		
		
		p5.add(combine);
		
		
		p6 = new JPanel();
		error = new JLabel();//pt atentionare in caz de erori
		execute = new JButton("Execute");
		execute.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				error.setText("");
				
				String nameValue;
				double priceValue;
				int quantValue;
				
				
				if(operations.getSelectedItem().equals("View all")){
					ArrayList<MenuItem> allProd = rp.getMenu();
					
					if(allProd.size() == 0) {
						error.setText("There is no product yet");
						return;
					}
					
					JFrame tableWindow = new JFrame("Table of menu items");
					tableWindow.setSize(800, 400);
					tableWindow.setLocationRelativeTo(null);
					tableWindow.setLayout(new BorderLayout());
					
					JTable table = Reflection.createTable(allProd);
					
					tableWindow.add(new JScrollPane(table), BorderLayout.CENTER);
					tableWindow.setVisible(true);
					
					return;
				}
				
				
				if(!bP.isSelected() && !cP.isSelected()) {
					error.setText("Choose between base and composite products");
					return;
				}
				
				
				if(bP.isSelected()) {//base product
					if(operations.getSelectedItem().equals("Add")){
						
						nameValue = name.getText();
						if(nameValue.equals("")) {
							error.setText("Invalid name");
							return;
						}
						try {
							priceValue = Double.parseDouble(price.getText());
							quantValue = Integer.parseInt(quantity.getText());
						}catch(NumberFormatException e) {
							error.setText("Invalid values");
							return;
						}
						
						BaseProduct p = new BaseProduct(nameValue, priceValue, quantValue);
						rp.createMenuItem(p);
						products.addItem(p);
						
					}
					if(operations.getSelectedItem().equals("Edit")){
						
						nameValue = name.getText();
						if(nameValue.equals("")) {
							error.setText("Invalid name");
							return;
						}
						try {
							priceValue = Double.parseDouble(price.getText());
							quantValue = Integer.parseInt(quantity.getText());
						}catch(NumberFormatException e) {
							error.setText("Invalid values");
							return;
						}
						
						BaseProduct p = new BaseProduct(nameValue, priceValue, quantValue);
						
						//daca editez un base product trebuie sa modific si composite product-ul care il contine
						ArrayList<CompositeProduct> toEdit = new ArrayList<CompositeProduct>();
						for(MenuItem m : rp.getMenu()) {
							if(m instanceof CompositeProduct) {
								ArrayList<MenuItem> comps = ((CompositeProduct) m).getComponents();
								for(MenuItem n : comps) {
									if(n.getName().equals(nameValue)) {
										toEdit.add((CompositeProduct)m);
									}
								}
							}
						}
						
						/*for(CompositeProduct cP : toEdit) {
							cP.getComponents().remove(p);
						}*/
						
						
						rp.editMenuItem(p);
						
						products = new JComboBox<BaseProduct>(rp.getBaseMenu());

					}
					if(operations.getSelectedItem().equals("Delete")){
						
						nameValue = name.getText();
						if(nameValue.equals("")) {
							error.setText("Invalid name");
							return;
						}

						//daca se sterge un base product trebuie sa se stearga si composite product-ul care il contine
						ArrayList<String> toDelete = new ArrayList<String>();
						for(MenuItem m : rp.getMenu()) {
							if(m instanceof CompositeProduct) {
								ArrayList<MenuItem> comps = ((CompositeProduct) m).getComponents();
								for(MenuItem n : comps) {
									if(n.getName().equals(nameValue)) {
										toDelete.add(m.getName());
									}
								}
							}
						}
						
						for(String s : toDelete) {
							rp.deleteMenuItem(s);
						}
						
						rp.deleteMenuItem(nameValue);
						
						products = new JComboBox<BaseProduct>(rp.getBaseMenu());
						
					}
				}else if(cP.isSelected()){//composite product
					if(operations.getSelectedItem().equals("Add")){
						
						nameValue = new String();
						for(MenuItem m : selectedProducts) {
							if(m.equals(selectedProducts.get(0)))
								nameValue += m.getName();
							else
								nameValue += " & " + m.getName();
						}
						
						if(selectedProducts.size() <= 1) {
							error.setText("Add at least 2 base products");
							return;
						}
						
						CompositeProduct p = new CompositeProduct(nameValue, selectedProducts);
						p.computePrice();
						p.computeQuantity();

						rp.createMenuItem(p);
						
						selectedProducts = new ArrayList<MenuItem>();
					}
					if(operations.getSelectedItem().equals("Edit")){
						
						nameValue = new String();
						for(MenuItem m : selectedProducts) {
							if(m.equals(selectedProducts.get(0)))
								nameValue += m.getName();
							else
								nameValue += " & " + m.getName();
						}
						
						if(selectedProducts.size() <= 1) {
							error.setText("Add at least 2 base products");
							return;
						}
						
						
						CompositeProduct p = new CompositeProduct(nameValue, selectedProducts);
						p.computePrice();
						p.computeQuantity();

						rp.editMenuItem(p);
						
						selectedProducts = new ArrayList<MenuItem>();
						
					}
					if(operations.getSelectedItem().equals("Delete")){
						
						nameValue = name.getText();
						
						if(nameValue.equals("")) {
							error.setText("invalid name");
							return;
						}

						rp.deleteMenuItem(nameValue);
						
					}
				}
				
				
				
				name.setText("");
				price.setText("");
				quantity.setText("");
				
				waiterWindow.end();
				waiterWindow = new WaiterGUI((Restaurant)rp);
				
				
				saveWork((Restaurant)rp);
				
			}
		});
		p6.add(execute);
		p6.add(error);
		
		
		
		
		adminWindow.add(p1);
		adminWindow.add(p2);
		adminWindow.add(p3);
		adminWindow.add(p4);
		adminWindow.add(p5);
		adminWindow.add(p6);
		
		
		adminWindow.setVisible(true);
	}
	
	public void saveWork(Restaurant r) {
		RestaurantSerializator.serialize(r);
	}
	
}
