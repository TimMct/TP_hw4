package hw4.hw4.reflection;

import java.lang.reflect.Field;
import java.util.List;
import javax.swing.JTable;

import hw4.hw4.businessLayer.Order;


public class Reflection {
	
	
	private static int nrOfCols(Object o) {
		if(o instanceof Order)
			return o.getClass().getDeclaredFields().length - 1;
		return o.getClass().getSuperclass().getDeclaredFields().length;
	}
	
	
	private static String[] retrieveColumns(Object o){
		int nrOfCols = Reflection.nrOfCols(o);
		String[] colNames = new String[nrOfCols];
		int i = 0;
		if(o instanceof Order) {
			for (Field field : o.getClass().getDeclaredFields()) {
				if(!field.getName().equals("inc")) {
					field.setAccessible(true); 
					colNames[i] = field.getName();
					i++;
				}
			}
		} else {
			for (Field field : o.getClass().getSuperclass().getDeclaredFields()) {
				field.setAccessible(true); 
				colNames[i] = field.getName();
				i++;
			}
		}
		
		return colNames;
	}
	
	
	
	private static Object[] retrieveValues(Object o) {

		int nrOfCols = Reflection.nrOfCols(o);
		Object[] values = new Object[nrOfCols];
		int i = 0;
		
		if(o instanceof Order) {
			for (Field field : o.getClass().getDeclaredFields()) {
				if(!field.getName().equals("inc")) {
					field.setAccessible(true); 
					Object value;
					try {
						value = field.get(o);
						values[i] = value;
					} catch (IllegalArgumentException e) {
						System.out.println("Exception "+e.getMessage());
					} catch (IllegalAccessException e) {
						System.out.println("Exception "+e.getMessage());
					}
					i++;
				}
			}
		} else {
			for (Field field : o.getClass().getSuperclass().getDeclaredFields()) {
				field.setAccessible(true); 
				Object value;
				try {
					value = field.get(o);
					values[i] = value;
				} catch (IllegalArgumentException e) {
					System.out.println("Exception "+e.getMessage());
				} catch (IllegalAccessException e) {
					System.out.println("Exception "+e.getMessage());
				}
				i++;
			}
		}
		
		
		return values;
	}
	
	
	@SuppressWarnings("serial")
	public static <T> JTable createTable(List<T> objects) {
		JTable table = null;
		if(objects.size() == 0)
			return table;
		String[] colNames = Reflection.retrieveColumns(objects.get(0));
		int nrOfRows = objects.size();
		int nrOfCols = Reflection.nrOfCols(objects.get(0));
		Object[][] values = new Object[nrOfRows][nrOfCols];
		int i = 0;
		for(Object o : objects) {
			values[i] =  Reflection.retrieveValues(o);
			i++;
		}
		table = new JTable(values, colNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setRowHeight(50);
		
		return table;
	}
}
