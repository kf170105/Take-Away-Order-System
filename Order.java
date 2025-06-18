/**
 * This class represents the order details of customers
 * which contains attributes such as customer name, customer contact number,
 * delivery address and the arraylist that store the foods(pizza/pasta) with or without toppings.
 * This class has constructors to initialise order details such as customer name , their contact
 * number and delivery address, accessor and mutator methods to get and set
 * values and toString method to display the complete order details.
 *
 * @author Hew Kai Feng
 * @version ver 1.3.3
 */

import java.util.ArrayList;

public class Order
{
    //Fields

    private String customerName;
    private String contactNumber;
    private String address;
    private ArrayList<FoodItem> foodItems;

    //Default constructor

    /**
     * Default constructor which initialises each field to a default value and
     * creates object of class Order.
     *
     */
    public Order()
    {
        customerName = "James Wong";
        contactNumber = "012-345-6789";
        address = "35A, Damai Impian";
        foodItems = new ArrayList<>();
    }

    //Non default constructor

    /**
     * Non-default constructor with four parameters to assign for all attributes
     * which creates object of class Order.
     *
     * @param newCustomerName            Accepts the customer name as a String.
     * @param newContactNumber           Accepts the contact number as a String.
     * @param newAddress                 Accepts the address as a String.
     * @param newFoodItems               Accepts the FoodItems arraylist as an Arraylist..
     */
    public Order(String newCustomerName, String newContactNumber, String newAddress, ArrayList<FoodItem> newFoodItems)
    {
        customerName = newCustomerName;
        contactNumber = newContactNumber;
        address = newAddress;
        foodItems = newFoodItems;
    }

    //Accessor methods

    /**
     * Accessor method to get the delivery address of customer.
     *
     * @return       The delivery address of customer as String.
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * Accessor method to get the contact number of customer.
     *
     * @return       The contact number of customer as String.
     */
    public String getContactNumber()
    {
        return contactNumber;
    }

    /**
     * Accessor method to get the customer name.
     *
     * @return       The customer name as String.
     */
    public String getCustomerName()
    {
        return customerName;
    }

    /**
     * Accessor method to get ArrayList that store food items.
     *
     * @return       The foodItems as Arraylist.
     */
    public ArrayList<FoodItem> getFoodItems()
    {
        return foodItems;
    }

    /**
     * Accessor method to get total price of food items.
     *
     * @return       The total cost as double.
     */
    public double getTotalPrice()
    {
        double totalCost = 0;
        for (FoodItem i : foodItems)   //iterate through the foodItems arraylist
        {
            totalCost += i.getPrice(); //each iteration will sum up the pizza/pasta with or without topping's
                                        // total cost
        }
        return totalCost;
    }

    /**
     * Returns a description of the order details.
     *
     * @return       A complete formatted description of order details as a String.
     */
    @Override
    public String toString()
    {
        String result = "";
               result += "Customer's name: " + customerName;
               result += "\nCustomer's contact number: " + contactNumber;
               result += "\nCustomer's home address: " + address;
               result += "\nTotal order cost: $" + getTotalPrice()+"0";
               result += "\nFood items:";
               for (FoodItem item : foodItems)
               {
                   result += "\n " + item.getFoodType() + ": " + item + " (Meal Type: " + item.getMealType() + ")" ;
               }
        return result;
    }

    //Mutator methods

    /**
     * Mutator method to set the delivery address of customer.
     *
     * @param   latestAddress      The delivery address of customer as String.
     */
    public void setAddress(String latestAddress)
    {
        address = latestAddress;
    }

    /**
     * Mutator method to set the contact number of customer.
     *
     * @param   latestContactNumber     The contact number of customer as String.
     */
    public void setContactNumber(String latestContactNumber)
    {
        contactNumber = latestContactNumber;
    }

    /**
     * Mutator method to set the name of customer.
     *
     * @param   latestCustomerName     The name of customer as String.
     */
    public void setCustomerName(String latestCustomerName)
    {
        customerName = latestCustomerName;
    }

    /**
     * Mutator method to set the arraylist that store food items.
     *
     * @param   latestFoodItems         foodItems as ArrayList.
     */
    public void setFoodItems(ArrayList<FoodItem> latestFoodItems)
    {
        foodItems = latestFoodItems;
    }
}