package hw4.hw4;



import hw4.hw4.businessLayer.Restaurant;
import hw4.hw4.dataLayer.RestaurantSerializator;
import hw4.hw4.presentationLayer.AdministratorGUI;

public class App{
    public static void main( String[] args ){

    	
    	
    	Restaurant r = RestaurantSerializator.deserialize();
    	
		new AdministratorGUI(r );
    }
}
