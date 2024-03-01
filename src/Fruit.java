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
public class Fruit extends FoodItem{
	/**
	 * This variable specify the name of farm that provides the fruits in String type.
	 */
	private String orcharName;
	
	/**
	 * Default constructor
	 */
	Fruit(){}
	
	/**
	 * Constructor with necessary passed in parameter (superclass parameters are also included).
	 * @param itemCode - An unique number for an item.
	 * @param itemName = A name for an item.
	 * @param itemPrice - The price of the item.
	 * @param itemQuantityStock - The quantity for the item.
	 * @param itemCost - The cost for the item.
	 * @param orcharName = The supplier name
	 */
	Fruit(int itemCode, String itemName,  int itemQuantityStock,float itemPrice,float itemCost, String orcharName){
		super(itemCode, itemName, itemPrice, itemQuantityStock, itemCost);
		this.orcharName = orcharName;
	}
	/**
	 * Dealing with the input of supplier name and this method will inherited the method in superclass; after implementing the code in superclass, the code in this method will be implemented.
	 * @param scanner - The standard input object.
	 * @param fromFile - This boolean parameter specifies whether adding new item from a file or not.
	 * @return - If successfully entering the supplier name, return true. If not, then return false.
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {	
		if (super.addItem(scanner, fromFile)) {
	        do {
	        	scanner.nextLine();
	            System.out.println("Enter the name of the orchard supplier: ");
	            try {	            	
	            	this.orcharName = scanner.nextLine();	                
	            } catch (InputMismatchException e) { 
	                System.out.println("Invalid entry");
	            }	            
	        /* Check whether name is null or empty.*/
	        } while (orcharName == null || orcharName.trim().isEmpty()); 
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
		return super.toString() + String.format(" orchard supplier: %s", orcharName);
	}
	
	/**
	 * This method is used for output the data in the current Fruit object.
	 * @param writer - A Formatter object used for formatting the output
	 */
	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%s\n", orcharName);

	}
}