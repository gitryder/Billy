package com.realllydan.billy.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.realllydan.billy.util.Calculator;

import java.util.ArrayList;

public class Billi implements Parcelable {

    private String name;
    private int splitCost;
    private ArrayList<Dish> dishesEaten = new ArrayList<>();

    public Billi() {
    }

    public Billi(String name, int splitCost, ArrayList<Dish> dishesEaten) {
        this.name = name;
        this.splitCost = splitCost;
        this.dishesEaten = dishesEaten;
    }

    protected Billi(Parcel in) {
        name = in.readString();
        splitCost = in.readInt();
        dishesEaten = in.createTypedArrayList(Dish.CREATOR);
    }

    public static final Creator<Billi> CREATOR = new Creator<Billi>() {
        @Override
        public Billi createFromParcel(Parcel in) {
            return new Billi(in);
        }

        @Override
        public Billi[] newArray(int size) {
            return new Billi[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSplitCost() {
        return splitCost;
    }

    public void addToSplitCost(int splitCost) {
        this.splitCost += splitCost;
    }

    public ArrayList<Dish> getDishesEaten() {
        return dishesEaten;
    }

    public void addDishesEaten(Dish dish) {
        dishesEaten.add(dish);
    }

    public String getDishesEatenAsString() {
        String dishesAsString = "";
        for (Dish dish : dishesEaten) {
            dishesAsString += Calculator.getCalculatedDishString(dish) + "\n";
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
        parcel.writeTypedList(dishesEaten);
    }
}
