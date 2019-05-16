package hw4.hw4;



import hw4.hw4.businessLayer.Restaurant;
import hw4.hw4.dataLayer.RestaurantSerializator;
import hw4.hw4.presentationLayer.AdministratorGUI;
import hw4.hw4.presentationLayer.ChefGUI;

public class App{
    public static void main( String[] args ){

    	
    	ChefGUI cGUI = new ChefGUI();
    	
    	Restaurant r = RestaurantSerializator.deserialize(cGUI);
    	
		new AdministratorGUI(r, cGUI);
    }
}
