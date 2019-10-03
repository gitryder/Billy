package com.realllydan.billy.ui.main;

import android.os.Bundle;
import android.util.Log;

import com.realllydan.billy.StatefulPresenter;
import com.realllydan.billy.data.models.Food;
import com.realllydan.billy.data.models.PersonWithFood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivityPresenterState implements StatefulPresenter {

    private static final String TAG = "MainActivityPresenterState";
    private static final String PERSON_NAME_LIST_STATE = "person_name_list";
    private static final String PERSON_WITH_FOOD_LIST_STATE = "person_with_food_list";
    private static final String PERSON_WITH_FOOD_MAP_STATE = "person_with_food_map";

    private List<String> mPersonNamesList;
    private List<PersonWithFood> mPersonWithFoodList;
    private HashMap<String, PersonWithFood> mFoodEaten;

    private MainActivityView view;

    public MainActivityPresenterState(MainActivityView view) {
        this.view = view;
        initDatasets();
    }

    private void initDatasets() {
        mPersonNamesList = new ArrayList<>();
        mPersonWithFoodList = new ArrayList<>();
        mFoodEaten = new HashMap<>();
    }

    public void onAddPersonClicked(String personName) {
        Log.d(TAG, "addNewPerson: called");
        mFoodEaten.put(personName, new PersonWithFood());
        mPersonNamesList.add(personName);
        view.updateDataAdapters(mPersonNamesList);
    }

    public void onAddFoodClicked(Food food, String personName) {
        Log.d(TAG, "addFoodToPerson: called");
        mFoodEaten.get(personName).setName(personName);
        mFoodEaten.get(personName).addFoodEaten(food);
    }

    public void onGetSplitClicked() {
        mPersonWithFoodList = new ArrayList<>();
        for (String personName : mPersonNamesList) {
            if (!mFoodEaten.get(personName).isEmpty()) {
                mPersonWithFoodList.add(mFoodEaten.get(personName));
            }
        }
        view.navigateToSplitActivity(mPersonWithFoodList);
    }

    @Override
    public void savePresenterState(Bundle outState) {
        outState.putSerializable(PERSON_NAME_LIST_STATE, (ArrayList) mPersonNamesList);
        outState.putSerializable(PERSON_WITH_FOOD_LIST_STATE, (ArrayList) mPersonWithFoodList);
        outState.putSerializable(PERSON_WITH_FOOD_MAP_STATE, mFoodEaten);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void restorePresenterState(Bundle savedInstanceState) {
        mPersonNamesList = (List<String>) savedInstanceState.getSerializable(PERSON_NAME_LIST_STATE);
        mPersonWithFoodList = (List<PersonWithFood>) savedInstanceState.getSerializable(PERSON_WITH_FOOD_LIST_STATE);
        mFoodEaten = (HashMap<String, PersonWithFood>) savedInstanceState.getSerializable(PERSON_WITH_FOOD_MAP_STATE);
        view.updateDataAdapters(mPersonNamesList);
    }
}
