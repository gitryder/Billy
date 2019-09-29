package com.realllydan.billy.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {

    private String foodName;
    private int foodPrice;
    private int foodTax;

    public Food() {
    }

    public Food(String foodName, int foodPrice, int foodTax) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodTax = foodTax;
    }

    protected Food(Parcel in) {
        foodName = in.readString();
        foodPrice = in.readInt();
        foodTax = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodTax() {
        return foodTax;
    }

    public void setFoodTax(int foodTax) {
        this.foodTax = foodTax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foodName);
        parcel.writeInt(foodPrice);
        parcel.writeInt(foodTax);
    }
}
