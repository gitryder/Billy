package com.realllydan.billy.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.realllydan.billy.util.Calculator;

import java.util.ArrayList;

public class PersonWithFood implements Parcelable {

    private String name;
    private int splitCost;
    private ArrayList<Food> foodEaten = new ArrayList<>();

    public PersonWithFood() {
    }

    public PersonWithFood(String name) {
        this.name = name;
    }

    public PersonWithFood(String name, int splitCost, ArrayList<Food> foodEaten) {
        this.name = name;
        this.splitCost = splitCost;
        this.foodEaten = foodEaten;
    }

    protected PersonWithFood(Parcel in) {
        name = in.readString();
        splitCost = in.readInt();
        foodEaten = in.createTypedArrayList(Food.CREATOR);
    }

    public static final Creator<PersonWithFood> CREATOR = new Creator<PersonWithFood>() {
        @Override
        public PersonWithFood createFromParcel(Parcel in) {
            return new PersonWithFood(in);
        }

        @Override
        public PersonWithFood[] newArray(int size) {
            return new PersonWithFood[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSplitCost() {
        for (Food food : foodEaten) {
            splitCost += Calculator.getTaxInclusivePrice(food.getFoodPrice(), food.getFoodTax());
        }
        return splitCost;
    }

    public boolean isEmpty() {
        return foodEaten.isEmpty();
    }

    public void addFoodEaten(Food food) {
        foodEaten.add(food);
    }

    public String getFoodEatenAsString() {
        String dishesAsString = "";
        for (Food food : foodEaten) {
            dishesAsString += Calculator.getCalculatedDishString(food) + "\n";
        }
        return dishesAsString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(splitCost);
        parcel.writeTypedList(foodEaten);
    }
}
