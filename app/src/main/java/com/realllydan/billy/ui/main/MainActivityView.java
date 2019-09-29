package com.realllydan.billy.ui.main;

import android.widget.EditText;

import com.realllydan.billy.data.models.Food;

public interface MainActivityView {

    void addNewPerson(String personName);
    void addFoodToPerson(Food food, String personName);
    void clearInputFields(EditText... editTexts);
    void navigateToSplitActivity();

}
