package com.realllydan.billy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Dish implements Parcelable {

    private String dishName;
    private int dishPrice;
    private int dishTax;

    public Dish() {
    }

    public Dish(String dishName, int dishPrice, int dishTax) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishTax = dishTax;
    }

    protected Dish(Parcel in) {
        dishName = in.readString();
        dishPrice = in.readInt();
        dishTax = in.readInt();
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(int dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getDishTax() {
        return dishTax;
    }

    public void setDishTax(int dishTax) {
        this.dishTax = dishTax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dishName);
        parcel.writeInt(dishPrice);
        parcel.writeInt(dishTax);
    }
}
