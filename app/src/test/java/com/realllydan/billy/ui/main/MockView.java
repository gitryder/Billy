package com.realllydan.billy.ui.main;

import com.realllydan.billy.data.models.PersonWithFood;

import java.util.List;

class MockView implements MainActivityView {
    boolean hasUpdatedDataAdapters;

    @Override
    public void updateDataAdapters(List<String> mPersonNamesList) {
        hasUpdatedDataAdapters = true;
    }

    @Override
    public void navigateToSplitActivity(List<PersonWithFood> mPersonWithFoodList) {

    }

    @Override
    public void displayToastMessage(String message) {

    }
}
