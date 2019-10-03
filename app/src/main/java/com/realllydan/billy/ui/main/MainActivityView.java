package com.realllydan.billy.ui.main;

import com.realllydan.billy.data.models.PersonWithFood;

import java.util.List;

public interface MainActivityView {
    void updateDataAdapters(List<String> mPersonNamesList);
    void navigateToSplitActivity(List<PersonWithFood> mPersonWithFoodList);
}
