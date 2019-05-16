package hw4.hw4.dataLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import hw4.hw4.businessLayer.Restaurant;
import hw4.hw4.presentationLayer.ChefGUI;


//am folosit cod de aici
//https://www.tutorialspoint.com/java/java_serialization.htm

public class RestaurantSerializator {
	
	public static boolean serialize(Restaurant r) {
		try {
			FileOutputStream fileOut = new FileOutputStream("restaurant.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(r);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in restaurant.ser");
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public static Restaurant deserialize(ChefGUI cGUI) {
		
		Restaurant r = new Restaurant(cGUI);
		try {
			FileInputStream fileIn = new FileInputStream("restaurant.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			r = (Restaurant) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		r.addObserver(cGUI);
		
		return r;
	}
}
