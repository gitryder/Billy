package com.realllydan.billy;

import android.os.Bundle;

public interface StatefulPresenter {

    /**
     * Saves the state of the presenter
     *
     * @param outState to which Serializable data is added that is passed to onSaveInstanceState()
     * */
    void savePresenterState(Bundle outState);

    /**
     * Restores the state of the presenter
     *
     * @param savedInstanceState by which the state of the presenter is restored
     * */
    void restorePresenterState(Bundle savedInstanceState);
}
