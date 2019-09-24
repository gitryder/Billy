package com.realllydan.billy.util;

import com.realllydan.billy.models.Dish;

public class Calculator {

    /**
     * @return the concatenated string of dishName and dishPrice after
     * calculating the tax on the dish
     *
     * @param dish the dish of which String is requested
     * */

    public static String getCalculatedDishString(Dish dish) {
        long taxInclusiveDishCost = dish.getDishPrice() * dish.getDishTax() / 100;
        return dish.getDishName() + " (₹" + dish.getDishPrice() + " + ₹" + taxInclusiveDishCost + ")";
    }

    public static int getTaxInclusivePrice(int dishPrice, int dishTax) {
        return dishPrice += (dishPrice * dishTax / 100);
    }

}
