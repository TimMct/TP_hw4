package hw4.hw4.presentationLayer;



import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import hw4.hw4.businessLayer.Order;
import hw4.hw4.businessLayer.Restaurant;


@SuppressWarnings("deprecation")
public class ChefGUI implements Observer{

	//private static Restaurant r;
	//private Observable obs;
	
	private JFrame chefWindow;
	private JLabel newOrder;
	
	public ChefGUI(Restaurant r) {
		
		//rp = r;
		
		
		
		chefWindow = new JFrame("Chef");
		chefWindow.setLocation(900, 100);
		chefWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chefWindow.setSize(500, 300);
		
		
		newOrder = new JLabel();
		newOrder.setHorizontalAlignment(SwingConstants.CENTER);
		chefWindow.add(newOrder);
		
		chefWindow.setVisible(true);
	}

	public void update(Observable o, Object arg) {
		if(o instanceof Restaurant && arg instanceof Order) {
			newOrder.setText("The chef has to cook "+arg);
		}
		
	}
	
	public void end() {
		chefWindow.dispose();
	}
	
}
