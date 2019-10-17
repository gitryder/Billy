package com.realllydan.billy.util;

import com.realllydan.billy.data.models.Food;

public class Calculator {

    private static final int DEFAULT_FOOD_TAX = 12;

    /**
     * @param food the food of which String is requested
     *
     * @return the concatenated string of dishName and foodPrice after
     * calculating the tax on the food
     * */

    public static String getCalculatedDishString(Food food) {
        long taxInclusiveDishCost = food.getFoodPrice() * food.getFoodTax() / 100;
        return food.getFoodName() + " (₹" + food.getFoodPrice() + " + ₹" + taxInclusiveDishCost + ")";
    }

    /**
     * @param foodPrice the price of the food of which price is expected
     * @param foodTax the tax of the food of which price is expected
     *
     * @return the tax inclusive price of the food
     * */

    public static int getTaxInclusivePrice(int foodPrice, int foodTax) {
        return foodPrice += (foodPrice * foodTax / 100);
    }

}
