/**
 * This class manages  the main operations of take away order system.
 * It contains multiple operations which can be chosen by customer to perform
 * such as adding order which requires customer details and food that customer
 * wants to order, delivering order which delivers the food items to customer's
 * address, displaying order details for customer, removing and searching order
 * of customer by inputting their order number or customer details and updating
 * customer details for that specific order.
 *
 * In this class, it used queue and linkedlist implementation which fulfills FIFO first in first out theory,
 * datetime to determine the date and time of the delivery and try&except to handle
 * validation of invalid order number that customer chose to remove.
 *
 * @author Hew Kai Feng
 * @version ver 1.3.3
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderOperation
{
    private static final ArrayList<Order> orders = new ArrayList<Order>();

    /**
     * This method will keep reprompting customer details such as their names, contact number
     * and the delivery address, these information can't be left empty and must be filled in
     * or else take away order system will keep reprompting customer inputs. After filling
     * in customer details, customers will be asked to choose to purchase pizza or pasta.
     * After choosing what food customers want to add, system will store the food items into an arraylist
     * and track the amount of food items customer added.
     */
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        // Check if name contains only letters, spaces, and common name characters
        return name.matches("^[a-zA-Z\\s'-]+$");
    }

    /**
     * Helper method to validate contact number (digits only, reasonable length)
     * @param contactNumber the contact number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidContactNumber(String contactNumber) {
        if (contactNumber == null || contactNumber.trim().isEmpty()) {
            return false;
        }
        // Remove spaces and check if it contains only digits with reasonable length
        String cleanNumber = contactNumber.replaceAll("\\s+", "");
        return cleanNumber.matches("^\\d{8,15}$"); // 8-15 digits
    }

    public static void addOrder()
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<FoodItem> foodItems = new ArrayList<>();

        String customerName = "";
        boolean validName = false;
        while(!validName)
        {
            System.out.print("Enter a valid customer name (letters only): ");
            customerName = scanner.nextLine().trim();
            
            if (customerName.isEmpty())
            {
                System.out.println("Customer name cannot be empty. Please try again.");
            }
            else if (!isValidName(customerName))
            {
                System.out.println("Invalid name! Name should contain only letters, spaces, hyphens, and apostrophes. No digits allowed.");
            }
            else
            {
                validName = true;
            }
        }

        String customerContactNumber = "";
        boolean validContactNumber = false;
        while (!validContactNumber)
        {
            System.out.print("Enter a valid customer contact number (8-15 digits): ");
            customerContactNumber = scanner.nextLine().trim();
            
            if (customerContactNumber.isEmpty())
            {
                System.out.println("Customer contact number cannot be empty. Please try again.");
            }
            else if (!isValidContactNumber(customerContactNumber))
            {
                System.out.println("Invalid contact number! Please enter 8-15 digits only (spaces allowed).");
            }
            else
            {
                validContactNumber = true;
            }
        }

        String customerHomeAddress = "";
        boolean validHomeAddress = false;
        while (!validHomeAddress)
        {
            System.out.print("Enter a valid customer's home address: ");
            customerHomeAddress = scanner.nextLine().trim();
            
            if (customerHomeAddress.isEmpty())
            {
                System.out.println("Customer's home address cannot be empty. Please try again.");
            }
            else if (customerHomeAddress.length() < 5)
            {
                System.out.println("Address too short! Please enter a complete address (minimum 5 characters).");
            }
            else
            {
                validHomeAddress = true;
            }
        }

        int pizzaCount = 0;
        int pastaCount = 0;

        boolean done=false;
        while (!done)
        {
            System.out.println("\n---------------------------Food you want to order------------------------------");
            System.out.println("Please enter the food you want to order (pizza/pasta, enter 'done' when finished):");
            String customerFoodOrder = scanner.nextLine().toLowerCase();

            if (customerFoodOrder.equals("done"))
            {
                done=true;
                break;
            }
            else if (customerFoodOrder.equals("pizza"))
            {
                Pizza pizza = new Pizza();   // create pizza object
                pizza.createToppingPizza();  // call out createToppingPizza method
                foodItems.add(pizza);        // store the pizza into foodItems arraylist
                pizzaCount++;
            }
            else if (customerFoodOrder.equals("pasta"))
            {
                Pasta pasta = new Pasta();      // create pasta object
                pasta.createToppingPasta();     // call out createToppingPasta method
                foodItems.add(pasta);           // store the pasta into foodItems arraylist
                pastaCount++;
            }
            else
            {
                System.out.println("Invalid option! Note that we only sell pizza and pasta here.\n");
            }
        }

        System.out.println("\n(Mr/Miss)" + customerName + " " +
                "ordered " + pizzaCount + " pizza(s) and " + pastaCount +
                " pasta(s).");

        if (foodItems.isEmpty())
        {
            System.out.println("Order must have at least one food item.");
            return;
        }

        //Create an object for Order using the inputted customer name, contact number , delivery address and arraylist.
        Order order = new Order(customerName, customerContactNumber, customerHomeAddress, foodItems);
        orders.add(order);
        System.out.println("Order(s) added!\n");
    }


    /**
     * This method will deliver the food order following "first in first out" theory using queue
     * from linkedlist. This means the first order will be the first to deliver.
     * After that, use local datetime to record the date and the time of the delivery.
     * During removal of current order, it will show the details of the next order
     * which will be delivered if available.
     */
    public static void deliverOrder()
    {
        if (orders.isEmpty())
        {
            System.out.println("So far no orders to deliver.");
        }
        else
        {
            Queue<Order> orderQueue = new LinkedList<>(orders);
            Order orderToBeRemoved = orderQueue.poll();     //Remove the first order in the queue
            System.out.println("Delivering..........\n" + orderToBeRemoved);

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj  = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            System.out.println("Delivered on: " + myDateObj.format(myFormatObj));

            orders.remove(0);

            if (orderQueue.size() != 0)
            {
                System.out.println("----------------------------------------------------");
                System.out.println("Next order to be delivered:\n" + orders.get(0));
            }
        }
    }


    /**
     * This method will display the order details of customers such as
     * customer name, contact number, delivery address, total costs and the meal type.
     * Basically , it returns the toString() method details in class order.
     */
    public static void displayOrders()
    {
        if (orders.isEmpty())
        {
            System.out.println("No pending orders.");
        }
        else
        {
            System.out.println("\nPending orders:");
            int counter=1;
            for (int i = 0; i < orders.size(); i++)
            {
                System.out.println("\n------------------------Order(" + counter + ")-----------------" +
                        "-------------\n" + "----" + "Your order number (don't forget it):" + " " + counter + " " + "----\n" + orders.get(i));
                counter++;
            }
        }
    }


    /**
     * This method will remove the order that customer chose to remove. Customer is required to
     * choose an order number to remove, the try&catch exception will handle the validation operation
     * to keep reprompting and ensure user enters  a valid order number.
     * After user enters a valid order number, system will perform double verification to ensure
     * customers really want to remove order or not, if no, it will break and quit this method,
     * if yes, the specific order will be removed from the arraylist orders.
     */
    public static void removeOrder()
    {
        if(orders.isEmpty())
        {
            System.out.println("No available orders to be removed");
        }
        else
        {
            boolean validOrderNumber = false;
            Scanner scanner = new Scanner(System.in);
            while(!validOrderNumber)
            {
                System.out.println("Enter your order number to remove the food order:");

                int numberOrderToBeRemoved = 0;

                try
                {
                    numberOrderToBeRemoved = scanner.nextInt();  //Read an integer (order number) inputted by customer
                    scanner.nextLine();
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();    //It will discard the invalid order number input
                    continue;
                }


                if(numberOrderToBeRemoved < 1 || numberOrderToBeRemoved > orders.size())
                {
                    System.out.println("No such order number exists");
                    validOrderNumber = false;
                }
                else
                {
                    System.out.println("---------------The order you want to remove---------------\n"
                            + orders.get(numberOrderToBeRemoved-1));
                    System.out.println("----------------------------------------------------------");



                    boolean response = false;
                    while(!response){
                        System.out.println("Are you sure you want to remove this order?(yes/no):");
                        String responseToRemove = scanner.nextLine().toLowerCase();

                        if(responseToRemove.equals("no"))
                        {
                            System.out.println("Order not removed");
                            response = true;
                            break;
                        }
                        else if (responseToRemove.equals("yes"))
                        {
                            //Remove order from the orders arraylist
                            Order orderToBeRemoved = orders.remove(numberOrderToBeRemoved-1);
                            System.out.println("............Removing order............");
                            System.out.println(orderToBeRemoved); //Display out the order we want to remove
                            response = true;
                        }
                        else
                        {
                            System.out.println("please enter yes or no only");
                            response = false;
                        }

                    }
                    validOrderNumber = true;
                }
            }
        }
    }


    /**
     * This method will prompt user to input their name , contact number and delivery address.
     * Then use a for loop to iterate through orders arraylist to check whether the inputted name,
     * contact number and delivery address are same as within the arraylist.
     * If it is, then display the specific order, if it is not matched,
     * then display a message of no such order exists.
     */
    public static void searchOrder()
    {
        if(orders.isEmpty())
        {
            System.out.println("So far no orders");
        }
        else
        {
            Scanner scanner = new Scanner(System.in);

            String name = "";
            boolean validName = false;
            while(!validName)
            {
                System.out.println("Please enter your name (letters only):");
                name = scanner.nextLine().trim();
                
                if(name.isEmpty())
                {
                    System.out.println("Name cannot be blank!");
                }
                else if (!isValidName(name))
                {
                    System.out.println("Invalid name! Name should contain only letters, spaces, hyphens, and apostrophes. No digits allowed.");
                }
                else
                {
                    validName = true;
                }
            }

            String contactNumber = "";
            boolean validNumber = false;
            while(!validNumber)
            {
                System.out.println("Please enter your contact number (digits only):");
                contactNumber = scanner.nextLine().trim();
                
                if(contactNumber.isEmpty())
                {
                    System.out.println("Contact number cannot be blank!");
                }
                else if (!isValidContactNumber(contactNumber))
                {
                    System.out.println("Invalid contact number! Please enter 8-15 digits only.");
                }
                else
                {
                    validNumber = true;
                }
            }

            String address = "";
            boolean validAddress = false;
            while(!validAddress)
            {
                System.out.println("Please enter your delivery address:");
                address = scanner.nextLine().trim();
                
                if(address.isEmpty())
                {
                    System.out.println("Delivery address cannot be blank!");
                }
                else if (address.length() < 5)
                {
                    System.out.println("Address too short! Please enter a complete address.");
                }
                else
                {
                    validAddress = true;
                }
            }

            boolean orderFound = false;
            for(Order i : orders)
            {
                if (i.getCustomerName().equalsIgnoreCase(name) && 
                    i.getContactNumber().equals(contactNumber) && 
                    i.getAddress().equalsIgnoreCase(address))
                {
                    System.out.println("---------------This is your order---------------\n" + i);
                    orderFound = true;
                    break;
                }
            }
            
            if (!orderFound)
            {
                System.out.println("No such order exists or user info is wrong");
            }
        }
    }


    /**
     * This method will prompt user to input an order number, after that ,
     * if the order number existed, simply let user decides which of the customer
     * detail they want to update. Then, prompt their inputted customer detail
     * and use the setter method from class Order to update the specific
     * customer detail.
     */
    public static void updateOrder(){
        if(orders.isEmpty())
        {
            System.out.println("So far no orders to update");
        }
        else{
            Scanner scanner = new Scanner(System.in);
            
            boolean validOrderNumber = false;
            int updateNumber = 0;
            
            while (!validOrderNumber) {
                try {
                    System.out.println("Please enter your order number to update your details (1-" + orders.size() + "):");
                    updateNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    
                    if(updateNumber < 1 || updateNumber > orders.size()) {
                        System.out.println("No such order number exists. Please enter a number between 1 and " + orders.size());
                    } else {
                        validOrderNumber = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // clear invalid input
                }
            }

            Order order = orders.get(updateNumber-1);
            System.out.println("...Below is your order...");
            System.out.println("---------------------------------------");
            System.out.println(order);
            System.out.println("---------------------------------------");

            boolean validChoice = false;
            while(!validChoice) {
                try {
                    System.out.println("Please select the part you want to update:");
                    System.out.println("1. Customer name");
                    System.out.println("2. Contact number");
                    System.out.println("3. Delivery address");
                    System.out.print("Your choice: ");
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch(updateChoice) {
                        case 1:
                            boolean validNewName = false;
                            while (!validNewName) {
                                System.out.println("Enter updated customer name (letters only):");
                                String latestName = scanner.nextLine().trim();
                                
                                if (latestName.isEmpty()) {
                                    System.out.println("Name cannot be empty!");
                                } else if (!isValidName(latestName)) {
                                    System.out.println("Invalid name! Name should contain only letters, spaces, hyphens, and apostrophes.");
                                } else {
                                    order.setCustomerName(latestName);
                                    System.out.println("System already updated your name!\n");
                                    validNewName = true;
                                }
                            }
                            validChoice = true;
                            break;

                        case 2:
                            boolean validNewContact = false;
                            while (!validNewContact) {
                                System.out.println("Enter updated contact number (8-15 digits):");
                                String latestContactNumber = scanner.nextLine().trim();
                                
                                if (latestContactNumber.isEmpty()) {
                                    System.out.println("Contact number cannot be empty!");
                                } else if (!isValidContactNumber(latestContactNumber)) {
                                    System.out.println("Invalid contact number! Please enter 8-15 digits only.");
                                } else {
                                    order.setContactNumber(latestContactNumber);
                                    System.out.println("System already updated your contact number!\n");
                                    validNewContact = true;
                                }
                            }
                            validChoice = true;
                            break;

                        case 3:
                            boolean validNewAddress = false;
                            while (!validNewAddress) {
                                System.out.println("Enter updated delivery address:");
                                String latestDeliveryAddress = scanner.nextLine().trim();
                                
                                if (latestDeliveryAddress.isEmpty()) {
                                    System.out.println("Address cannot be empty!");
                                } else if (latestDeliveryAddress.length() < 5) {
                                    System.out.println("Address too short! Please enter a complete address.");
                                } else {
                                    order.setAddress(latestDeliveryAddress);
                                    System.out.println("System already updated your address!\n");
                                    validNewAddress = true;
                                }
                            }
                            validChoice = true;
                            break;

                        default:
                            System.out.println("Invalid choice! Please enter 1, 2, or 3.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a valid number (1-3).");
                    scanner.nextLine(); // clear invalid input
                }
            }
            
            System.out.println("...Below is the full info with updated details...");
            System.out.println(order);
        }
    }
}
