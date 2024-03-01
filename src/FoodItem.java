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
public class FoodItem implements Comparable<FoodItem> {
	
		/**
		 * An unique number for an item in primitive integer type.
		 */
		protected int itemCode;
		/**
		 * A name for an item in String type.
		 */
		protected String itemName;
		/**
		 * The price of the item in primitive float type.
		 */
		protected float itemPrice;
		/**
		 * The quantity for the item in primitive integer type.
		 */
		protected int itemQuantityStock;
		/**
		 * The cost for the item in primitive float type.
		 */
		protected float itemCost;
	
	/**
	 * Default Constructor
	 */
	FoodItem(){}
	
/**
 * Constructor with necessary passed in. Child class will inherit this as well.
 * @param itemCode - An unique number for an item.
 * @param itemName = A name for an item.
 * @param itemPrice - The price of the item.
 * @param itemQuantityStock - The quantity for the item.
 * @param itemCost - The cost for the item.
 */
	FoodItem(int itemCode, String itemName, float itemPrice, int itemQuantityStock, float itemCost){
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantityStock = itemQuantityStock;
		this.itemCost = itemCost;
	}
	
	/**
	 * For updating the quantity in stock with passed in parameter and the parameter is verify as a valid inout already.
	 * @param amount - The specific amount to sell or buy. If selling item, amount will be negative. If buying item, amount will be positive.
	 * @return - If update is successful, return true. If not, return false.
	 */
	public boolean updateItem(int amount) {
		/* Add the current quantity with amount.. */
		int newQuantity = this.itemQuantityStock + amount;
		/* Checking whether newQuantity is larger than zero */
		if(newQuantity<0) {
			System.out.println("Error...could not sell item");
			return false;
		}else {
			this.itemQuantityStock = newQuantity;
		}
		return true;
	}
	
	/**
	 * Dealing with the details of entering an item code including checking whether the input is valid. If the input is not an integer, the program will ask user to enter again. Negative integer is acceptable because the program will change it into positive number.
	 * @param scanner - The standard input object for user to enter something.
	 * @param fromFile - True means data is from reading a file, while false means data is from user input.
	 * @return - If successfully entering the item code, return true. If not, return false.
	 */
	public boolean inputCode(Scanner scanner, boolean fromFile) {
		int code = 0;
		if(fromFile) {
			System.out.println("Enter the code for the item:");
			try {
				if((code = scanner.nextInt())!= 0) {
					this.itemCode = code;
					return true;
				}				
			}catch(InputMismatchException e) {
				System.out.println("Invalid code");				
			}
			scanner.nextLine();
			return false;
		}else {
			do {
				scanner.nextLine();
				System.out.println("Enter the code for the item:");				
				try {
					/*If input is not an integer, print out the warning message.*/
					code = scanner.nextInt();
					/* If input is a positive integer, then assign it to itemCode.*/
					if (code > 0) {
			            this.itemCode = code;
			            return true;
			         /* If input is a negative integer, then assign it to itemCode after converting it to positive.*/
			        }else if(code<0) {
			        	/*Convert the negative to positive number.*/
			        	code = -code; 
			        	this.itemCode = code;
			            return true;
			        }
					else {
			        	System.out.println("Invalid entry");
			        }					
	        	}catch(InputMismatchException e) {
	        		System.out.println("Invalid code");
	        	}
			}while(this.itemCode == 0 );
		}
		return false;
	}
	
	/**
	 * Dealing with all details of add a new item, including verifying the input types and conditions. This method will be executed after getting true result from inputCode()
	 * @param scanner - The standard input object for user to enter something.
	 * @param fromFile - True means data is from reading a file, while false means data is from user input.
	 * @return flag - If all details are accepted, return false. If there is any one input is not acceptable, return false.
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		scanner.nextLine(); //Flush the input remaining in the stream.
		String name = null;
		do {
			System.out.println("Enter the name for the item:");
			/* If name is not a String, print our the waning message.*/
			try {
				name = scanner.nextLine();				
			}catch(InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Invalid entry");
			}
			
		}while(name==null);		
		itemName =name; // If name matches all conditions above, assign name to itemName

		do{
			System.out.println("Enter the quantity for the item: ");
			/* If quantity is not an integer, print our the waning message.*/
			try {
				if( (itemQuantityStock = scanner.nextInt()) <0) {
					System.out.println("Invalid entry");
				}
			}catch(InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Invalid entry");
			}
			
		}while(itemQuantityStock <=0); //Quantity stock should be positive. Zero is acceptable.
		
		do {
			System.out.println("Enter the cost of the item: ");
			/* If cost is not a float number, print our the waning message. */
			try {
				if((itemCost = scanner.nextFloat())<0) {
					System.out.println("Invalid entry");
				}
			}catch(InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Invalid entry");
			}
			
		}while(itemCost <= 0); //Cost should always be positive.
		
		
		do {
			System.out.println("Enter the sales price of the item: ");
			/* If price is not a float number, print our the waning message.*/
			try {
				if((itemPrice = scanner.nextFloat()) < 0) {
					System.out.println("Invalid entry");
				}
			}catch(InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Invalid entry");
			}
		}while(itemPrice <= 0); //Price should always be positive. 
		
		
		boolean flag; // To check whether all details are successfully stored in the memory.
		if(itemCode >= 0 && 
		   itemName != null &&
		   itemPrice > 0 &&
		   itemQuantityStock >=0 &&
		   itemCost > 0
		   ) {
			flag = true;
		}else {
			flag = false;
		}
		return flag;	//Return the result.
	}
	
	/**
	 * Printing out the content of current object as format and each object in the inventory array will call this method.
	 * @return - A formatted string as required.
	 */
	@Override
	public String toString() {
		return String.format("Item: %d %s %d price: $%.2f cost: $%.2f", 
                itemCode, itemName, itemQuantityStock, 
                itemPrice, itemCost);
	}
	/**
	 * This method is used for output the data in the current object.
	 * @param writer - A Formatter object used for formatting the output
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d\n%s\n%d\n%.2f\n%.2f\n", 
                itemCode, itemName, itemQuantityStock, 
                itemPrice, itemCost).toString();

	}
	/**
	 * This method is to check whether a product is already existed in the inventory by comparing the itemCode number.
	 * @param f - The item that the user want to find
	 * @return - Return an integer -1 (when f.itemCode is larger than the current object itemCode) , 0 (two itemCodes are equal)or 1 (when f.itemCode is smaller than the current object itemCode).
	 */
	@Override
	public int compareTo(FoodItem f) {
		return Integer.compare(this.itemCode, f.itemCode);
		
	}
	/**
	 * This getter method allows other class object to get item code for the current object.
	 * @return - The item code of current object
	 */
	public int getItemCode() {
		return this.itemCode;
	}
	
}