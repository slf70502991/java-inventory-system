import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 *The FoodItem class
 * Student Name: YENLING LIN
 * Student Number:  041107273
 * Course: CST8130 - Data Structures
 * @author - Professor: James Mwangi PhD. / Student: YENLING LIN
 * 
 */
public class Vegetable extends FoodItem{
	/**
	 * This variable specify the name of farm that provides the vegetable in String type.
	 */
	private String farmName;
	
	/**
	 * Default constructor
	 */
	Vegetable(){}
	
	/**
	 * Constructor with necessary passed in parameter (superclass parameters are also included).
	 * @param itemCode - An unique number for an item.
	 * @param itemName = A name for an item.
	 * @param itemPrice - The price of the item.
	 * @param itemQuantityStock - The quantity for the item.
	 * @param itemCost - The cost for the item.
	 * @param farmName - The farm name
	 */
	Vegetable(int itemCode, String itemName, int itemQuantityStock, float itemPrice,float itemCost,  String farmName){
		super(itemCode, itemName, itemPrice, itemQuantityStock, itemCost);
		this.farmName = farmName;
	}
	
	/**
	 * Dealing with the input of supplier name and this method will inherited the method in superclass; after implementing the code in superclass, the code in this method will be implemented.
	 * @return - If successfully entering the supplier name, return true. If not, then return false.
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if (super.addItem(scanner, fromFile)) {
	        do {
	        	scanner.nextLine();
	            System.out.println("Enter the name of the farm supplier: ");
	            try {
	                this.farmName = scanner.nextLine();
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid entry");	                
		        }
	         /* Check whether farmName is null or empty. */
	        } while (farmName == null || farmName.trim().isEmpty());
	        return true;
	    }
		return false;
	}
	
	/**
	 * Call super method and print out supplier name.
	 * @return - A formatted string as required.
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(" farm supplier: %s", farmName);
		
	}
	/**
	 * This method is used for output the data in the current Vegetable object.
	 * @param writer - A Formatter object used for formatting the output
	 */
	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%s\n", farmName);
	}
}