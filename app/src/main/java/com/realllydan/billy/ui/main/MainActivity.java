package com.realllydan.billy.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.realllydan.billy.R;
import com.realllydan.billy.data.adapters.FoodEatenByAdapter;
import com.realllydan.billy.data.adapters.PersonListAdapter;
import com.realllydan.billy.data.models.Food;
import com.realllydan.billy.data.models.PersonWithFood;
import com.realllydan.billy.ui.split.SplitActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MainActivityView, AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static final int DEFAULT_FOOD_TAX = 12;
    private static final String PERSON_LIST_STATE_KEY = "person_list_state";

    private EditText etPersonName, etFoodName, etFoodPrice, etFoodTax;
    private Button bAddPerson, bAddFood, bGetSplit;
    private RecyclerView mRecyclerView;
    private Spinner mChooseEatenBy;

    private MainActivityPresenter mainActivityPresenter;
    private PersonListAdapter personListAdapter;
    private FoodEatenByAdapter foodEatenByAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: called");

        mainActivityPresenter = new MainActivityPresenter(this);

        etPersonName = findViewById(R.id.et_person_name);
        etFoodName = findViewById(R.id.et_food_name);
        etFoodPrice = findViewById(R.id.et_food_price);
        etFoodTax = findViewById(R.id.et_food_tax);
        mChooseEatenBy = findViewById(R.id.choose_eaten_by);
        bAddPerson = findViewById(R.id.b_add_person);
        bAddFood = findViewById(R.id.b_add_food);
        bGetSplit = findViewById(R.id.b_get_split);

        initViews();

        bAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bAddPerson");

                if (getStringFromEditText(etPersonName).equals("")) {
                    makeToast("Add a person's name!");
                } else {
                    mainActivityPresenter.onAddPersonClicked(etPersonName.getText().toString().trim());
                }
                clearInputFields(etPersonName);
            }
        });

        bAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bAddFood");

                if (mChooseEatenBy.getSelectedItem() != null) {
                    mainActivityPresenter.onAddFoodClicked(getDataFromFieldsAsFood(), mChooseEatenBy.getSelectedItem().toString());
                } else {
                    makeToast("No Person added!");
                }
                clearInputFields(etFoodName, etFoodPrice, etFoodTax);
            }
        });

        bGetSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bGetSplit");
                mainActivityPresenter.onGetSplitClicked();
            }
        });
    }

    private void initViews() {
        initToolbar();
        initRecyclerView();
        initSpinner();
    }

    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        mRecyclerView = findViewById(R.id.recycler_view);
        personListAdapter = new PersonListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(personListAdapter);
    }

    private void initSpinner() {
        Log.d(TAG, "initSpinner: called");
        foodEatenByAdapter = new FoodEatenByAdapter(getApplicationContext(), R.layout.single_spinner_item);
        mChooseEatenBy.setAdapter(foodEatenByAdapter);
    }

    private void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }

    private Food getDataFromFieldsAsFood() {
        Log.d(TAG, "getDataFromFoodFields: called");
        Food food = new Food();

        String foodName = getStringFromEditText(etFoodName);
        String foodPrice = getStringFromEditText(etFoodPrice);
        String foodTax = getStringFromEditText(etFoodTax);

        if (!foodName.equals("") && !foodPrice.equals("")) {
            food.setFoodName(foodName);
            food.setFoodPrice(Integer.parseInt(foodPrice));
            food.setFoodTax(foodTax.equals("") ? DEFAULT_FOOD_TAX : Integer.parseInt(foodTax));
        } else {
            makeToast("Food details cannot be empty");
        }
        return food;
    }

    public void clearInputFields(EditText... editTexts) {
        for (EditText editText : editTexts) editText.setText("");
    }

    // TODO: Write code to save state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // TODO: Write code to restore state
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void updateDataAdapters(List<String> mPersonNamesList) {
        personListAdapter.updateListWithAddedData(mPersonNamesList);
        foodEatenByAdapter.updateListWithAddedData(mPersonNamesList);
    }

    @Override
    public void navigateToSplitActivity(List<PersonWithFood> mPersonWithFoodList) {
        startActivity(SplitActivity.getStartIntent(this, mPersonWithFoodList));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
