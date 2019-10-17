package com.realllydan.billy.ui.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainActivityPresenterTest {

    private static final String MOCK_PERSON_NAME = "John";

    private MainActivityView view;
    private MainActivityPresenter mainActivityPresenter;

    @Before
    public void beforeTests() {
        view = new MockView();
        mainActivityPresenter = new MainActivityPresenter(view);
    }

    @Test
    public void verifyAddsNewPerson() {
        mainActivityPresenter.onAddPersonClicked(MOCK_PERSON_NAME);
        Assert.assertTrue(((MockView) view).hasUpdatedDataAdapters);
    }
}
