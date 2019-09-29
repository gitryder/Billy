package com.realllydan.billy.ui.main;

import android.widget.EditText;

import com.realllydan.billy.data.models.Food;

public class MainActivityPresenter {

    private MainActivityView view;

    MainActivityPresenter(MainActivityView view) {
        this.view = view;
    }

    void addNewPerson(String personName) {
        view.addNewPerson(personName);
    }

    void addFoodToPerson(Food food, String personName) {
        view.addFoodToPerson(food, personName);
    }

    void clearInputFields(EditText... editTexts) {
        view.clearInputFields(editTexts);
    }

    void navigateToSplitActivity() {
        view.navigateToSplitActivity();
    }
}
