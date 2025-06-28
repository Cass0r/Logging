import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Class responsible for managing a collection of to-do items and displaying the interactive menu
public class to_do_item {

// Map to store to-do items using their ID as the key
public Map<String,Items>item;

// Constructor initializes the map for storing items
public to_do_item(){
    item = new HashMap<>();
}

//Integrating logging
private static final Logger logger = LogManager.getLogger(to_do_item.class);

//----------------------------------------------------------------------------------------------------------------------
// Method to add a to-do item to the list
public boolean Add_Item(Items additem){
    // Check if the item already exists by ID
    if (item.containsKey(additem.getID())) {
        logger.warn("Attempted to add duplicate item with ID: " + additem.getID());
        return false;
    }
    // Add the item to the map
    item.put(additem.getID(), additem);
    logger.info("Item added successfully with ID: " + additem.getID());
    return true;
}
//----------------------------------------------------------------------------------------------------------------------
//* Delete a to-do item
public boolean Delete_item(String ID){
    // Attempt to remove the item; if null, item was not found
    if (item.remove(ID) == null) {
        logger.error("Failed to delete item. ID not found: " + ID);
        return false;
    }
    logger.info("Item deleted successfully. ID: " + ID);
    return true;
}
//----------------------------------------------------------------------------------------------------------------------
//* View the to-do items
public void display_items_list(){
    if (item.isEmpty()) {
        logger.warn("User attempted to view list, but it is empty.");
    } else {
        logger.info("Displaying all items in the to-do list.");
    }

}
//----------------------------------------------------------------------------------------------------------------------
// Method to display and manage the to-do list menu
public void To_Do_List_Menu(){
    Scanner sc = new Scanner(System.in);
    int option;

do {
    // Display menu options
    System.out.println("\nTo Do List Menu");
    System.out.println("1. Add an item to list");
    System.out.println("2. Delete an item from list");
    System.out.println("3. View the to-do list");
    System.out.println("4. Exit Menu");
    System.out.print("Enter your option by the number associated: ");

    while (true) {
        // Check if the input is an integer
        if (!sc.hasNextInt()) {
            System.out.println("\nInvalid input. Please enter a number between 1 and 4.");
            sc.next(); // Clear the invalid input
        } else {
            option = sc.nextInt(); // Read the input

            // Check if the input is within the valid range (1 or 2)
            if (option >= 1 && option <= 4) {
                break;  // Exit the loop if valid input is entered
            } else {
                System.out.println("Invalid option. Please enter a number between 1 and 4.");
            }
        }
    }

// Handle menu options
    switch (option) {

        //Add
        case 1:
            //Add ID
            System.out.print("\nEnter the Item ID(up to 5 numbers): ");
            sc.nextLine();
            String id = sc.nextLine();

            // Validate ID format
            while (!id.matches("\\d{1,5}")) {
                System.out.println("Error: ID must be a number with 1 to 5 digits only.");
                System.out.print("Enter the Item ID (1 to 5 digits): ");
                id = sc.nextLine();
            }

            // Prompt and validate name
            System.out.print("Enter the name of the item: ");
            //sc.nextLine();
            String name = sc.nextLine();

            while (name.length() < 1 || name.length() > 15) {
                System.out.println("Error: name entered had no characters entered, please re-enter proper amount(up to 15 characters.)");
                System.out.print("Enter Item name: ");
                name = sc.nextLine();
            }

            // Prompt and validate description
            System.out.print("Enter a short description of the item: ");
            //sc.nextLine();
            String description = sc.nextLine();

            while (description.length() < 1 || description.length() > 50) {
                System.out.println("Error: short description entered had no characters entered, Please enter below 50 characters.");
                System.out.print("Enter short description: ");
                description = sc.nextLine();
            }

            // Prompt and validate task status (boolean)
            boolean task_status;
            while (true) {
                System.out.print("Enter the completion status of the task(true,false): ");
                String input = sc.next().trim().toLowerCase();
                //sc.nextLine();

                if (input.equals("true")) {
                    task_status = true;
                    break;  // Exit the loop once a valid boolean is entered
                } else if (input.equals("false")) {
                    task_status = false;
                    break;  // Exit the loop once a valid boolean is entered
                } else {
                    System.out.println("Error: Please enter 'true' or 'false'.");
                }

            }
            // Add the validated item
            System.out.println("\nItem has been added.");
            Add_Item(new Items(id, name, description, task_status));
            break;

        //Delete
        case 2:
            System.out.print("Enter ID to delete task: ");
            sc.nextLine(); // Clear input
            String remove_ID = sc.nextLine();
            Delete_item(remove_ID);
            break;

        //view
        case 3:
            display_items_list();
            break;

        //close menu
        case 4:
            System.out.println("Exiting Menu..."); // Continue menu until option 4 is selected (exit)
            break;

            }//switch
        } while (option != 4);
    }//menu
}//class
