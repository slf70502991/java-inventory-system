import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CET - CS Academic Level 3
 *The FoodItem class
 * Student Name: YENLING LIN
 * Student Number:  041107273
 * Course: CST8130 - Data Structures
 * @author - Professor: James Mwangi PhD. / Student: YENLING LIN
 * 
 */
public class Inventory{
	
	/**
	 * FoodItem ArrayList to store product
	 */
	private List<FoodItem> inventory = new ArrayList<FoodItem>();
	
	
	/**
	 * Default constructor
	 */
	Inventory() {}
	
	/**
	 * The constructor with passed in parameters. 
	 * @param inventory - FoodItem ArrayList to store product
	 */
	Inventory(ArrayList<FoodItem> inventory){
		this.inventory = new ArrayList<FoodItem>();
	}
	
	/**
	 * For checking the item code of passed in object has already stored in the inventory: if the item code is already existed, this method will return the specific index.
	 * @param item - The item object that wants to check whether existed or not.
	 * @return - Return an integer. If not finding any existed item code the same as the one passed in, return -1. If found, return the index of that object in the inventory array.
	 */
	public int alreadyExists(FoodItem item) {
		 for (int i = 0; i < inventory.size(); i++) {
			 if(inventory.get(i).compareTo(item) == 0) {	        	
		        	return i;  //If found, return the index.
		        }
		    }
		 return -1; // Item not found
	}
	
	
	/**
	 * Dealing with the different type of item to add by asking user to choose a certain type of item and proceed on entering the item details: If all details are successfully accepted, a new item object will be stored in the inventory array.
	 * If the types and conditions are not matched, ask the user to input again.
	 * @param scanner - The standard input object
	 * @param fromFile - This boolean parameter specifies whether adding new item from a file or not.
	 * @return - If adding new item successfully, return true; if not, return false.
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if(fromFile) {
			readFromFile(scanner);
			quickSort(inventory, 0, inventory.size()-1);
			return true;
		}else {
			String option = null;
			while (true) {
			    System.out.println("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
			    try {
			        option = scanner.next().toLowerCase();
			    } catch (InputMismatchException e) {
			        System.out.println("Please enter valid option.");
			        continue;
			    }

			    FoodItem item = null;
			    switch (option) {
			        case "f":
			            item = new Fruit();
			            break;
			        case "v":
			            item = new Vegetable();
			            break;
			        case "p":
			            item = new Preserve();
			            break;
			        default:
			            System.out.println("Invalid entry");
			            continue;
			    }

			    if (item != null && item.inputCode(scanner, fromFile) && alreadyExists(item) == -1 && item.addItem(scanner, fromFile)) {
			        inventory.add(item);
			        quickSort(inventory, 0, inventory.size() - 1);
			        return true;
			    }else if(alreadyExists(item) != -1) {
			    	System.out.println("Product item is already existed.");
			    	return false;
			    }
			    else {
			    	return false;
			    }
			}
		}
	}
	
	/**
	 * If user wants to buy, passes true into method and if user wants to sell, passes false into the method.
	 * @param keyboard - The standard input object.
	 * @param buyOrSell - True means buying more. False means selling.
	 * @return - If successfully buying/selling, return true. If not, return false.
	 */
	public boolean updateQuantity(Scanner keyboard, boolean buyOrSell) {
		String cannotBuy = "Error...could not buy item";
		String cannotSell = "Error...could not sell item";
		if(inventory.get(0) == null && buyOrSell==true) {
			System.out.println(cannotBuy);
			return false;
		}else if(inventory.get(0) == null && buyOrSell==false){
			System.out.println(cannotSell);
			return false;
		}else {
			FoodItem newItem = new FoodItem();
			
			System.out.println("Enter valid item code: ");
			int code = 0;
			try {
				code = keyboard.nextInt();
			}catch(InputMismatchException e) {
				keyboard.nextLine();
				System.out.println("Invalid code.");
			}
			
			if(code > 0) {
				newItem.itemCode = code;
				if(alreadyExists(newItem) == -1) {
					
					if(buyOrSell) {
						System.out.println(cannotBuy);
					}else {
						System.out.println(cannotSell);
					}
				}else {
					int index = alreadyExists(newItem);
					if(buyOrSell) {
						System.out.println("Enter valid quantity to buy: ");
						int quantity = 0;
						try {
							quantity = keyboard.nextInt();
						}catch(InputMismatchException e) {
							keyboard.nextLine();
							System.out.println("Invalid data type.");
						}
						
						if(quantity<0) {
							System.out.println("Invalid quantity...");
							System.out.println(cannotBuy);
						}else {
							if(inventory.get(index).updateItem(quantity)) {
								return true;
							}
						}
					}else {
						System.out.println("Enter valid quantity to sell: ");
						int quantity = 0;
						try {
							quantity = keyboard.nextInt();
						}catch(InputMismatchException e) {
							keyboard.nextLine();
							System.out.println("Invalid data type.");
						}
						if(quantity<0) {
							System.out.println("Invalid quantity...");
							System.out.println(cannotSell);
						}else {
							int sellAmount = -quantity;
							if(inventory.get(index).updateItem(sellAmount)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * The method implements quick sort to sort inventory ArrayList  by itemCode.
	 * @param inventory - The product inventory ArrayList for sorting.
	 * @param start - THe left bound
	 * @param end - The right bound
	 */
	private void quickSort(List<FoodItem> inventory,int start, int end) { 
        if (start < end) {
            int pivot = inventory.get(end).getItemCode();
            int nextIndex = start;
            for (int i = start; i < end; i++) {
                if (inventory.get(i).getItemCode() < pivot) {
                    FoodItem temp = inventory.get(nextIndex);
                    inventory.set(nextIndex, inventory.get(i));
                    inventory.set(i, temp);
                    nextIndex++;
                }
            }
            
            FoodItem temp = inventory.get(nextIndex);
            inventory.set(nextIndex, inventory.get(end));
            inventory.set(end, temp);

            quickSort(inventory, start, nextIndex - 1);
            quickSort(inventory, nextIndex + 1, end);
        }
    }
	/**
	 * This method is for searching the item code and check whether the item is already existed with for loop to iterate all elements in inventory ArrayList.
	 * @param scanner - The standard input stream object.
	 */
	public void searchForItem(Scanner scanner) {
		FoodItem midItem = null;
		FoodItem food = new FoodItem();
		/* Binary Search to iterate inventory */
		if(food.inputCode(scanner, true)) {
			int low = 0;
			int high = inventory.size() - 1;
			while(low < high) {
				int mid = (low + high)/2;
				midItem = inventory.get(mid);
				
				if(midItem.compareTo(food) == 0) {
					break;
				}else if(midItem.compareTo(food) < 0) { 
					/*
					 *  If the new itemCode is greater than the itemCode of a existed 
					 *  product placed in the middle of the inventory
					 */
					low = mid + 1; // Search the items in the right side of the middle item
				}else {
					/*
					 *  If the new itemCode is smaller than the itemCode of a existed 
					 *  product placed in the middle of the inventory
					 */
					high = mid -1; // Search the items in the left side of the middle item
				}
			}
			
			while(high == 0 || low == high) {
				midItem = inventory.get(high);
				break;
			}
			if(midItem != null && midItem.compareTo(food) == 0) {
				System.out.println(midItem.toString());	
			}else {
				System.out.println("Code not found in inventory...");
			}
		}	
	}
	
	/**
	 * This method is for saving the data in the inventory to a text file line by line which means that each data is stored and separated by a new line.
	 * @param scanner - The standard input stream object that receives data input from user.
	 */
	public void saveToFile(Scanner scanner) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		String fileName = null;
		try {
			System.out.println("Enter the filename to save to: ");
			fileName = scanner.next();
			File file = new File(fileName);
			//Check whether file exists or not.
			if(!file.exists()) {
				if (file.createNewFile()) {
	                System.out.println( fileName + " does not exist. "+ fileName +" will be created.");
	            }
			}
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			Formatter writer = new Formatter(bw);
			for(FoodItem i:inventory) {
				if(i instanceof Fruit) {
					bw.write("f\n");
					i.outputItem(writer);
				}else if(i instanceof Vegetable) {
					bw.write("v\n");
					i.outputItem(writer);
				}else {
					bw.write("v\n");
					i.outputItem(writer);
				}
			}
			
            bw.close();
            fw.close();
            writer.close();
		}catch(InputMismatchException e) {
			System.out.println("Please enter a valid file name.");
		
		}catch(IOException e) {
			System.out.println("Error reading file: " + fileName);
		}
	}
	
	/**
	 * This method is for reading the data from a text file and add data into inventory by creating new object based on different type of product. ("f" represents Fruit, "v" represents Vegetable, and "p" represents Preserve.)
	 * @param scanner - The standard input stream object.
	 */
	public void readFromFile(Scanner scanner) {
//		inventory = new ArrayList<FoodItem>();
		FileReader fr = null;
		BufferedReader br = null;
		String fileName = null;
		try {
			System.out.println("Enter the filename to read from: ");
			fileName = scanner.next();
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			FoodItem foodItem = null;
			String line;
	        while ((line = br.readLine()) != null) {	  
	        	/* Input data from the file into inventory */
				if(line.equals("f")) {
					foodItem = new Fruit(Integer.parseInt(br.readLine()),br.readLine(),Integer.parseInt(br.readLine()),Float.parseFloat(br.readLine()),Float.parseFloat(br.readLine()),br.readLine());	
				}else if(line.equals("v")) {
					foodItem = new Vegetable(Integer.parseInt(br.readLine()),
							br.readLine(),
							Integer.parseInt(br.readLine()),
							Float.parseFloat(br.readLine()),
							Float.parseFloat(br.readLine()),
							br.readLine());
				}else if(line.equals("p")) {
					foodItem = new Preserve(Integer.parseInt(br.readLine()),
							br.readLine(),
							Integer.parseInt(br.readLine()),
							Float.parseFloat(br.readLine()),
							Float.parseFloat(br.readLine()),
							br.readLine());
				}
			if(alreadyExists(foodItem) == -1) {
				inventory.add(foodItem);
			}else {
				System.out.println("Item code already exists\r\nError Encountered while reading the file, aborting...\r\n");
				break;
			}
		}
		}catch(InputMismatchException e) {
			System.out.println("Please enter a valid file name.");
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found, ignoring...");
		}catch(IOException e) {
			System.out.println("Error reading file: " + fileName);
		}finally {
	        try {
	            if (fr != null) {
	                fr.close();
	            }
	            if (br != null) {
	                br.close();
	            }
	        } catch (IOException e) {
	            System.out.println("FileReader/BufferedReader is not closed successfully.");
	        }
	    }
		/* Sort the inventory after reading all data */
		quickSort(inventory, 0, inventory.size()-1);		
	}
	
	/**
	 * Sorting the aArrayList and printing out the content of all inventory elements. 
	 * @return - Return an empty string.
	 */
	@Override
	public String toString() {		
		System.out.println("Inventory: ");
		for(FoodItem i:inventory) {
			System.out.println(i.toString());
		}
		System.out.println();
		return "";
	}
	
}