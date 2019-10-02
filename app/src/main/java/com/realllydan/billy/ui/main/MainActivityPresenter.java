package com.realllydan.billy.ui.main;

import android.os.Bundle;
import android.util.Log;

import com.realllydan.billy.data.models.Food;
import com.realllydan.billy.data.models.PersonWithFood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivityPresenter {

    private static final String TAG = "MainActivityPresenter";

    private List<String> mPersonNamesList;
    private List<PersonWithFood> mPersonWithFoodList;
    private HashMap<String, PersonWithFood> mFoodEaten;

    private MainActivityView view;

    MainActivityPresenter(MainActivityView view) {
        this.view = view;
        mPersonNamesList = new ArrayList<>();
        mPersonWithFoodList = new ArrayList<>();
        mFoodEaten = new HashMap<>();
    }

    void onAddPersonClicked(String personName) {
        Log.d(TAG, "addNewPerson: called");
        mFoodEaten.put(personName, new PersonWithFood());
        mPersonNamesList.add(personName);
        view.updateDataAdapters(mPersonNamesList);
    }

    void onAddFoodClicked(Food food, String personName) {
        Log.d(TAG, "addFoodToPerson: called");
        mFoodEaten.get(personName).setName(personName);
        mFoodEaten.get(personName).addFoodEaten(food);
    }

    void onGetSplitClicked() {
        mPersonWithFoodList = new ArrayList<>();
        for (String personName : mPersonNamesList) {
            if (!mFoodEaten.get(personName).isEmpty()) {
                mPersonWithFoodList.add(mFoodEaten.get(personName));
            }
        }
        view.navigateToSplitActivity(mPersonWithFoodList);
    }
}
