package hw4.hw4.presentationLayer;



import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



@SuppressWarnings("deprecation")
public class ChefGUI implements Observer{

	private JFrame chefWindow;
	private JLabel newOrder;
	
	public ChefGUI() {
		
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
		newOrder.setText("The chef has to cook "+arg);
	}
	
}
