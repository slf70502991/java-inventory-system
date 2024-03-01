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

public class Assign2{
	/**
	 * The method for displaying menu for users to choose the options: 1 for adding new item, 2 for printing out items, 3 for buying items, 4 for selling items and 5 for exiting the program.
	 */
	public static void displayMenu() {
		Inventory inventory = new Inventory();
		Scanner keyboard = new Scanner(System.in);
		
		String option = null;
		do {
			/* Print out the menu */
			System.out.println("Please select one of the following:");
			System.out.println("1: Add Item to Inventory");
			System.out.println("2: Display Current Inventory");
			System.out.println("3: Buy Item(s)");
			System.out.println("4: Sell Item(s)");
			System.out.println("5: Search for Item");
			System.out.println("6: Save Inventory to File");
			System.out.println("7: Read Inventory from File");
			System.out.println("8: To Exit");
			System.out.println("> ");
			
				option = keyboard.next();
				switch(option) {
				case "1":
					inventory.addItem(keyboard, false);
					break;
				case "2": 
					inventory.toString();
					break;
				case "3":
					
					inventory.updateQuantity(keyboard, true);
					break;
				case "4":
					
					inventory.updateQuantity(keyboard, false);
					break;
				case "5":
					inventory.searchForItem(keyboard);
					break;
				case "6":
					inventory.saveToFile(keyboard);
					break;
				case "7":
					inventory.addItem(keyboard, true);
					break;
				case "8":
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("...Invalid input, please try again...");
					break;
				}
			
		}while(!option.equalsIgnoreCase("8"));
		keyboard.close();
	}
	/**
	 * An entry point of the program
	 * @param args
	 */
	public static void main(String[] args) {
		displayMenu();
	}
}
