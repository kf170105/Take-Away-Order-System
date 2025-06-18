/**
 * Pasta concrete class extends abstract class FoodItem.
 * It represents food pasta's price before additional of
 * toppings and price after adding toppings. There are four fixed toppings
 * can be added for pasta. MealType can be seen and determined after
 * adding or without adding toppings.
 *
 * @author Hew Kai Feng
 * @version ver 1.3.3
 */

import java.util.*;
import java.util.Scanner;

public class Pasta extends FoodItem
{
    private MealType mealType;
    private ArrayList<PastaTopping> toppings;

    //Default constructor

    /**
     *Default constructor which initialises the meal type to vegan type
     * and assign and initialise toppings with an arraylist and inherit
     * the properties from parent class which is the BASE_PRICE.
     *
     */
    public Pasta()
    {
        super();
        mealType = MealType.VEGAN;
        toppings = new ArrayList<>();
    }

    //Non default constructor

    /**
     *Non default constructor with two parameters to assign for the two attributes.
     *create object of abstract class FoodItem.
     *
     * one-argument constructor
     * Create pasta with specific BASE_PRICE by calling out super method
     * , meal type and toppings.
     *
     * @param newToppings    An arraylist to store the toppings which selected to be added into pasta
     */
    public Pasta(ArrayList<PastaTopping> newToppings)
    {
        super();
        mealType = MealType.VEGAN;   //initialise and store the initial meal type as vegan first
        toppings = newToppings;
    }


    //Mutator method

    /**
     * Mutator method to prompt user input add on topping or not
     * which are bolognese, marinara, primavera or tomato.
     * If a valid topping is chosen, it increments the total number
     * of topping count added to pasta, updates the price of pasta
     * after adding topping and set the mealtype based on
     * overall toppings. If there is no topping, it automatically
     * considered as vegan meal, if there is a bolognese or marinara,
     * it automatically considered as meat meal, or there is a primavera,
     * it will be considered as vegetarian meal,
     * and last if there is tomato, it considered as vegan. Note that
     * user is only allowed to add one topping for pasta.
     *
     */
    public void createToppingPasta()
    {

        double price = BASE_PRICE;
        System.out.println("\nAvailable pasta toppings = [ bolognese, marinara, primavera, tomato ]");
        boolean add = false;
        int toppingCount = 0;
        Scanner scanner = new Scanner(System.in);
        while(!add)
        {
            System.out.println("\n-------------------------------Topping (Additional)-------------------------------");
            System.out.println("Add only one topping you want or type 'done' or enter to finish (Note that if you don't add on any toppings it will be assumed as vegan meal):");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("bolognese"))
            {
                toppingCount++;   // increment topping count by one after adding a topping(bolognese)
                toppings.add(PastaTopping.BOLOGNESE);
                price += 5.20;
                mealType = MealType.MEAT;
                System.out.println("topping(" + toppingCount + "):" + PastaTopping.BOLOGNESE + " " + "+ $5.20");
                break;
            }
            else if (input.equals("marinara"))
            {
                toppingCount++;
                toppings.add(PastaTopping.MARINARA);
                price += 6.80;
                mealType = MealType.MEAT;
                System.out.println("topping(" + toppingCount + "):" + PastaTopping.MARINARA + " " + " +$6.80");
                break;
            }
            else if (input.equals("primavera"))
            {
                toppingCount++;
                toppings.add(PastaTopping.PRIMAVERA);
                price += 5.20;
                mealType = MealType.VEGETARIAN;
                System.out.println("topping(" + toppingCount + "):" + PastaTopping.PRIMAVERA + " " + "+$5.20");
                break;
            }
            else if (input.equals("tomato"))
            {
                toppingCount++;
                toppings.add(PastaTopping.TOMATO);
                price += 4.00;
                mealType = MealType.VEGAN;
                System.out.println("topping(" + toppingCount + "):" + PastaTopping.TOMATO + " " + "+$4.00");
                break;
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
            mealType = MealType.VEGAN;
        }
    }


    //Accessor methods

    /**
     *return the specific foodtype for this class which is pasta
     * keeps promise to make concrete the abstract method, getFoodType(), in FoodItem
     *
     * @return the foodtype for this class which is pasta
     */
    @Override
    public FoodType getFoodType()
    {
        return FoodType.PASTA;
    }

    /**
     *return the specific mealtype for this class which is meat, vegan or vegetarian
     * keeps promise to make concrete the abstract method, getMealType(), in FoodItem
     *
     * @return the mealtype for this class which is meat, vegan or vegetarian
     */
    @Override
    public MealType getMealType()
    {
        return mealType;
    }

    /**
     *Accessor method to return the arraylist toppings that store the
     *toppings we added into pasta.
     *
     * @return        the arraylist toppings that store the toppings which added to the pasta.
     */
    public ArrayList<PastaTopping> getToppings()
    {
        return toppings;
    }

    /**
     * Returns a description of price of topping or without topping.
     *
     * @return   A complete formatted description of pasta price after or without toppings .
     */
    @Override
    public String toString()
    {
        if (toppings.isEmpty())
        {
            return "no topping," + " " + "price=$" + getPrice() +"0";
        }
        else
        {
            return "toppings" + toppings + "," + " price=$" + getPrice() + "0";
        }
    }

    //Mutator methods

    /**
     * Mutator method to set updated meal type after filtering the toppings.
     *
     * @param   latestMealType         The updated mealtype of pasta
     *
     */
    public void setMealType(MealType latestMealType)
    {
        mealType = latestMealType;
    }

    /**
     * Mutator method to set updated toppings arraylist.
     *
     * @param   latestPastaToppings        The updated arraylist that store
     *                                toppings of pasta.
     *
     */
    public void setToppings(ArrayList<PastaTopping> latestPastaToppings)
    {
        toppings = latestPastaToppings;
    }
}