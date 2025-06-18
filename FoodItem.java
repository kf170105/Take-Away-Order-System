/**
 * This is FoodItem abstract superclass.
 *
 * <p>abstract class FoodItem
 * It is abstract because we never intend to instantiate objects from it
 * and at the same time we want it to contain abstract methods {@link #getFoodType()} and {@link #getMealType()}
 * which will be implemented differently for each subclass such as {@link Pizza} and {@link Pasta}.
 * At the same time, we want the BASE_PRICE  to be inherited to child class to make sure each
 * child class has the same base price of the food(pizza/pasta)</p>
 *
 * <p>It represents the common attributes and methods of all the foods such as pizza and pasta</p>
 *
 * @author Hew Kai Feng
 * @version ver 1.3.3
 */

public abstract class FoodItem implements FoodDetail
{
    //Fields

    protected final double BASE_PRICE = 11.50;  //Ensure immutability of BASE_PRICE and ensure it is accessible to
                                                // all classes within same package
    private double price;

    //Default constructor

    /**
     *Default constructor which initialises the price to a default value
     * which is the {@code BASE_PRICE} value.
     *
     */
    public FoodItem()
    {
        price = BASE_PRICE;
    }

    //Non default constructor

    /**
     *Non default constructor with one parameter to assign to the {@code price} attribute
     * create object(food item) of abstract class FoodItem.
     *
     * @param newPrice   Accepts the price of food in form of $ as {@code double}.
     */
    public FoodItem(double newPrice)
    {
        price = newPrice;
    }

    // abstract method {@code getFoodType()} must be overridden by any concrete
    // subclass such as {@link Pizza} or {@link Pasta}.

    public abstract FoodType getFoodType();

    // abstract method {@code getMealType()} must be overridden by any concrete
    // subclass such as {@link Pizza} or {@link Pasta}.

    public abstract MealType getMealType();

    //Accessor methods

    /**
     * Accessor method to get the price of the food (pizza or pasta).
     *
     * @return       The price of food as {@code double} which in form of $.
     */

    public double getPrice()
    {
        return price;
    }

    /**
     * Returns a description of price of food.
     *
     * @return       A complete formatted description of food price.
     */
    @Override
    public String toString()
    {
        return "FoodItem{price=" + price + "}";
    }


    //Mutator methods

    /**
     * Mutator method to set updated price of food with or without toppings.
     *
     * @param   latestPrice         The updated price of pizza or pasta,
     *                              it is a decimal value for attribute price.
     */
    public void setPrice(double latestPrice)
    {
        price = latestPrice;
    }
}