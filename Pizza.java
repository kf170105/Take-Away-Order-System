/**
 * Pizza concrete class extends abstract class FoodItem.
 * It represents food pizza's price before additional of
 * toppings and price after adding toppings. There are six fixed toppings
 * can be added for pizza. MealType can only be determined after
 * adding or without adding toppings.
 *
 * @author Hew Kai Feng
 * @version ver 1.3.3
 */

import java.util.*;
import java.util.Scanner;

public class Pizza extends FoodItem
{

    private ArrayList<PizzaTopping> toppings;

    //Default constructor

    /**
     *Default constructor which call out super method to inherit
     *properties from parent class which is BASE_PRICE
     *and assign and initialise toppings with an arraylist.
     *
     */
    public Pizza()
    {
        super();
        toppings = new ArrayList<>();
    }

    //Non default constructor

    /**
     *Non default constructor with one parameter to assign for attribute
     *create object of abstract class FoodItem.
     *
     * one-argument constructor
     * Create pizza with specific BASE_PRICE by calling out super method
     * , meal type and toppings.
     *
     * @param newToppings    An arraylist to store the toppings which selected to be added into pizza
     */
    public Pizza(ArrayList<PizzaTopping> newToppings)
    {
        super();
        toppings = newToppings;
    }


    //Mutator method

    /**
     * Mutator method to prompt user input add on topping or not
     * which are ham,cheese,pineapple,mushrooms,tomato or seafoods.
     * If a valid topping is chosen, it increments the total number
     * of topping count added to pizza, updates the price of pizza
     * after adding topping and set the mealtype based on
     * overall toppings. If there is no topping, it automatically
     * considered as vegan meal, if there is a ham or seafood,
     * it automatically considered as meat meal, or there is a cheese
     * but without meat or seafood, it will be considered as vegetarian meal,
     * and last if there are tomato with pineapple and mushrooms only, it is considered as vegan.
     *
     */

    public void createToppingPizza()
    {
        double price = BASE_PRICE;

        System.out.println("\nAvailable pizza toppings = [ ham, cheese, pineapple, mushrooms, tomato, seafood ]");

        boolean add = false;
        int toppingCount = 0;
        Scanner scanner = new Scanner(System.in);

        while(!add)
        {

            System.out.println("\n-------------------------------Topping (Additional)-------------------------------");
            System.out.println("Add the topping you want or type 'done' or enter to finish (Note that if you don't add on any toppings it will be assumed as vegan meal):");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("ham"))
            {
                toppingCount++;
                toppings.add(PizzaTopping.HAM);
                price += 2.00;
                System.out.println("topping(" + toppingCount + "):" + PizzaTopping.HAM + " " + "+$2.00");
            }
            else if (input.equals("cheese"))
            {
                toppingCount++;
                toppings.add(PizzaTopping.CHEESE);
                price += 2.00;
                System.out.println("topping(" + toppingCount + "):" + PizzaTopping.CHEESE + " " + "+$2.00");
            }
            else if (input.equals("pineapple"))
            {

                toppingCount++;

                toppings.add(PizzaTopping.PINEAPPLE);
                price += 2.50;
                System.out.println("topping(" + toppingCount + "):" + PizzaTopping.PINEAPPLE + " " + "+$2.50");
            }
            else if (input.equals("mushrooms"))
            {

                toppingCount++;

                toppings.add(PizzaTopping.MUSHROOMS);
                price += 2.00;
                System.out.println("topping(" + toppingCount + "):" + PizzaTopping.MUSHROOMS + " " + "+$2.00");
            }
            else if (input.equals("tomato"))
            {

                toppingCount++;

                toppings.add(PizzaTopping.TOMATO);
                price += 2.00;
                System.out.println("topping(" + toppingCount + "):" + PizzaTopping.TOMATO + " " + "+$2.00");
            }
            else if (input.equals("seafood"))
            {

                toppingCount++;

                toppings.add(PizzaTopping.SEAFOOD);
                price += 3.50;
                System.out.println("topping(" + toppingCount + "):" + PizzaTopping.SEAFOOD + " " + "+$3.50");
            }
            else if (input.equals("done") || input.isEmpty())
            {
                add = true;
                break;
            }
            else
            {
                System.out.println("Invalid topping.");
                continue;
            }
        }

        setPrice(price);
        System.out.println("Total" + " " + toppingCount + " " + "toppings" + " " + "added.");
        if(toppingCount == 0)
        {
            System.out.println("No toppings added.");
        }

    }



    //Accessor methods

    /**
     *return the specific foodtype for this class which is pizza
     * keeps promise to make concrete the abstract method, getFoodType(), in FoodItem
     *
     * @return the {@code getFoodType()}  for this class which is pizza
     */
    @Override
    public FoodType getFoodType()
    {
        return FoodType.PIZZA;
    }

    /**
     *return the specific mealtype for this class which is meat, vegan or vegetarian
     * keeps promise to make concrete the abstract method, getMealType(), in FoodItem
     *
     * In this method, we search through the topping list which consists of
     * topping added to pizza by iterating and filtering.
     * If there is no topping, it automatically
     * considered as vegan meal, if there is a ham or seafood,
     * it automatically considered as meat meal, or there is a cheese
     * but without meat or seafood, it will be considered as vegetarian meal,
     * and last if there are tomato with pineapple and mushrooms only, it is considered as vegan.
     *
     * @return the {@code getMealType()}  for this class which is meat, vegan or vegetarian.
     */
    @Override
    public MealType getMealType()
    {

        MealType realMealType = MealType.VEGAN;

        if(toppings.size() == 0)
        {
            realMealType = MealType.VEGAN;
        }
        else
        {
            for (int i = 0; i < toppings.size(); i++)
            {
                if (toppings.get(i) == PizzaTopping.HAM || toppings.get(i) == PizzaTopping.SEAFOOD)
                {
                    realMealType = MealType.MEAT;
                    break;
                }
                else if (toppings.get(i) == PizzaTopping.CHEESE && toppings.get(i) != PizzaTopping.HAM
                         && toppings.get(i)!=PizzaTopping.SEAFOOD)
                {
                    realMealType = MealType.VEGETARIAN;
                }
            }
        }
        return realMealType;
    }

    /**
     *Accessor method to return the arraylist toppings that store the
     *toppings we added into pizza.
     *
     * @return        the arraylist toppings that store the toppings which added to the pizza.
     */
    public ArrayList<PizzaTopping> getToppings()
    {
        return toppings;
    }

    /**
     * Returns a description of price of topping or without topping.
     *
     * @return   A complete formatted description of pizza price after or without toppings .
     */
    @Override
    public String toString()
    {
        if (toppings.isEmpty())
        {
            return "no topping," + " " + "price=$" + getPrice() + "0";
        }
        else
        {
            return "toppings" + toppings + ","+" price=$" + getPrice() + "0";
        }
    }

    //Mutator method

    /**
     * Mutator method to set updated toppings arraylist.
     *
     * @param   latestPizzaToppings     The updated arraylist that store
     *                                  toppings of pizza.
     *
     */
    public void setToppings(ArrayList<PizzaTopping> latestPizzaToppings)
    {
        toppings = latestPizzaToppings;
    }
}