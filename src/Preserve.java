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
public class Preserve extends FoodItem{
	/**
	 * This variable specify the size of jar in String type.
	 */
	private String jarSize;
	
	/**
	 * Default constructor
	 */
	Preserve(){}
	
	/**
	 * 
	 * Constructor with necessary passed in parameter (superclass parameters are also included).
	 * @param itemCode - An unique number for an item.
	 * @param itemName = A name for an item.
	 * @param itemPrice - The price of the item.
	 * @param itemQuantityStock - The quantity for the item.
	 * @param itemCost - The cost for the item.
	 * @param jarSize - The size of preserve jar measured in milliliters.
	 */
	Preserve (int itemCode, String itemName, int itemQuantityStock,float itemPrice,float itemCost, String jarSize){
		super(itemCode, itemName, itemPrice, itemQuantityStock, itemCost);
		this.jarSize = jarSize;
	}
	
	/**
	 * Dealing with the input of jar size and this method will inherited the method in superclass; after implementing the code in superclass, the code in this method will be implemented.
	 * @return - If successfully entering the supplier name, return true. If not, then return false.
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if (super.addItem(scanner, fromFile)) {
	        do {
	            System.out.println("Enter the size of the jar in millilitres: ");
	            try {
	                this.jarSize = scanner.next();
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid entry");
	                scanner.next(); 
	            }
	         /* Check whether jarSize is null or empty. */
	        } while (jarSize == null || jarSize.trim().isEmpty()); 
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
		return super.toString() + String.format(" size: %smL", jarSize);
	}
	/**
	 * This method is used for output the data in the current Preserve object.
	 * @param writer - A Formatter object used for formatting the output
	 */
	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%s\n", jarSize);
	}
}