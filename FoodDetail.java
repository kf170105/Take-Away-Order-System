/**
 * FoodDetail interface declaration.
 * It represents a contract for some classes
 * that have determination of the type of foods
 * or type of meals.
 *
 * @author Hew Kai Feng
 * @version ver 1.3.3
 */

public interface FoodDetail
{
    FoodType getFoodType();
    MealType getMealType();
}
