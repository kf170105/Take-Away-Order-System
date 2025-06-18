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
import java.io.*;
import java.util.*;
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

    public static ArrayList<Order> getOrders()
    {
        return orders;
    }

    private static final String FILE_NAME = "orders_backup.txt";
    /**
     * This method will keep reprompting customer details such as their names, contact number
     * and the delivery address, these information can't be left empty and must be filled in
     * or else take away order system will keep reprompting customer inputs. After filling
     * in customer details, customers will be asked to choose to purchase pizza or pasta.
     * After choosing what food customers want to add, system will store the food items into an arraylist
     * and track the amount of food items customer added.
     */
    public static void addOrder()
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<FoodItem> foodItems = new ArrayList<>();

        String customerName = "";
        boolean validName = false;
        while(!validName)
        {
            System.out.print("Enter a valid customer name: ");
            customerName = scanner.nextLine();
            if (customerName.isEmpty())
            {
                System.out.println("Customer name cannot be empty. Please try again.");
                validName = false;
            }
            else
            {
                break;
            }
        }

        String customerContactNumber = "";
        boolean validContactNumber = false;
        while (!validContactNumber)
        {
            System.out.print("Enter a valid customer contact number: ");
            customerContactNumber = scanner.nextLine();
            if (customerContactNumber.isEmpty())
            {
                System.out.println("Customer contact number cannot be empty. Please try again.");
                validContactNumber = false;
            }
            else
            {
                break;
            }
        }

        String customerHomeAddress = "";
        boolean validHomeAddress = false;
        while (!validHomeAddress)
        {
            System.out.print("Enter a valid customer's home address: ");
            customerHomeAddress = scanner.nextLine();
            if (customerHomeAddress.isEmpty())
            {
                System.out.println("Customer's home address cannot be empty. Please try again.");
                validHomeAddress = false;
            }
            else
            {
                break;
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
                System.out.println("Please enter your name:");
                name=scanner.nextLine();
                if(name.isEmpty())
                {
                    System.out.println("Can't leave blank for name !");
                    validName = false;
                }
                else
                {
                    validName = true;
                    break;
                }
            }

            String contactNumber = "";
            boolean validNumber = false;
            while(!validNumber)
            {
                System.out.println("Please enter your contact number:");
                contactNumber=scanner.nextLine();
                if(contactNumber.isEmpty())
                {
                    System.out.println("Can't leave blank for contact number !");
                    validNumber = false;
                }
                else
                {
                    validNumber = true;
                    break;
                }
            }

            String address = "";
            boolean validAddress = false;
            while(!validAddress)
            {
                System.out.println("Please enter your delivery address:");
                address = scanner.nextLine();
                if(address.isEmpty())
                {
                    System.out.println("Can't leave blank for delivery address !");
                    validAddress = false;
                }
                else
                {
                    validAddress = true;
                    break;
                }
            }

            for(Order i : orders)
            {
                if (i.getCustomerName().equals(name) && i.getContactNumber().equals(contactNumber) && i.getAddress().equals(address))
                {
                    System.out.println("---------------This is your order---------------\n" + i);
                }
                else
                {
                    System.out.println("No such order exists or user info is wrong");
                }
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
            System.out.println("Please enter your order number to update your details :");
            int updateNumber = scanner.nextInt();

            if(updateNumber < 1 || updateNumber > orders.size())
            {
                System.out.println("No such order number exists");
            }else
            {
                Order order = orders.get(updateNumber-1);
                System.out.println("...Below is your order...");
                System.out.println("---------------------------------------");
                System.out.println(order);
                System.out.println("---------------------------------------");


                System.out.println("Please select the part you want to update");
                System.out.println("Enter 1 if customer name, enter 2 if contact number, enter 3 if delivery address");
                System.out.println("Your choice:");
                int updateChoice = scanner.nextInt();
                scanner.nextLine();

                boolean validChoice = false;
                while(!validChoice)
                {
                    if (updateChoice == 1)
                    {

                        System.out.println("Enter updated customer name:");
                        String latestName = scanner.nextLine();

                        order.setCustomerName(latestName);
                        System.out.println("System already updated your name!\n");

                        validChoice = true;

                        System.out.println("...Below is the full info with updated details...");
                        System.out.println(order);
                    }
                    else if(updateChoice == 2)
                    {
                        System.out.println("Enter updated contact number:");
                        String latestContactNumber = scanner.nextLine();

                        order.setContactNumber(latestContactNumber);
                        System.out.println("System already updated your contact number!\n");

                        validChoice = true;

                        System.out.println("...Below is the full info with updated details...");
                        System.out.println(order);
                    }
                    else if(updateChoice == 3)
                    {
                        System.out.println("Enter updated contact number:");
                        String latestDeliveryAddress = scanner.nextLine();

                        order.setAddress(latestDeliveryAddress);
                        System.out.println("System already updated your address!\n");

                        validChoice = true;

                        System.out.println("...Below is the full info with updated details...");
                        System.out.println(order);
                    }
                    else
                    {
                        System.out.println("Sorry, unknown choice");
                        validChoice = false;
                        break;
                    }
                }
            }
        }
    }

    public static void saveOrder()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(OrderOperation.getOrders());
            System.out.println("Orders saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }
}
