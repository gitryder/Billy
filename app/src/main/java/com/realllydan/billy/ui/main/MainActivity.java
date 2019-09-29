package com.realllydan.billy.ui.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.realllydan.billy.R;
import com.realllydan.billy.data.adapters.PersonListAdapter;
import com.realllydan.billy.data.models.Food;
import com.realllydan.billy.data.models.PersonWithFood;
import com.realllydan.billy.ui.split.SplitActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements MainActivityView {

    private static final String TAG = "MainActivity";

    private EditText etPersonName, etFoodName, etFoodPrice, etFoodTax, etFoodEatenBy;
    private Button bAddPerson, bAddFood, bGetSplit;
    private Spinner mChooseEatenBy;

    private static final int DEFAULT_FOOD_TAX = 12;
    private MainActivityPresenter mainActivityPresenter;
    private PersonListAdapter personListAdapter;
    private ArrayAdapter<String> personSpinnerAdapter;
    private ArrayList<String> mPersonNamesList;
    private ArrayList<PersonWithFood> mPersonWithFoodList;
    private HashMap<String, PersonWithFood> mFoodEaten = new HashMap<>();

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

        initToolbar();
        initRecyclerView();
        initSpinner();

        bAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bAddPerson");

                if (getStringFromEditText(etPersonName).equals("")) {
                    makeToast("Add a PersonWithFood name!");
                } else {
                    mainActivityPresenter.addNewPerson(etPersonName.getText().toString().trim());
                }
            }
        });

        bAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bAddFood");

                if (mChooseEatenBy.getSelectedItem() != null) {
                    mainActivityPresenter.addFoodToPerson(getDataFromFoodFields(), mChooseEatenBy.getSelectedItem().toString());
                } else {
                    makeToast("No PersonWithFood added!");
                }
            }
        });

        bGetSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bGetSplit");
                mainActivityPresenter.navigateToSplitActivity();
            }
        });
    }

    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        mPersonNamesList = new ArrayList<>();
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        personListAdapter = new PersonListAdapter(mPersonNamesList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(personListAdapter);
    }

    private void initSpinner() {
        Log.d(TAG, "initSpinner: called");
        personSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.single_spinner_item, mPersonNamesList);
        mChooseEatenBy.setAdapter(personSpinnerAdapter);
    }

    private String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Food getDataFromFoodFields() {
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

    @Override
    public void addNewPerson(String personName) {
        Log.d(TAG, "addNewPerson: called");
        mainActivityPresenter.clearInputFields(etPersonName);

        mFoodEaten.put(personName, new PersonWithFood());
        mPersonNamesList.add(personName);

        personListAdapter.notifyDataSetChanged();
        personSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFoodToPerson(Food food, String personName) {
        Log.d(TAG, "addFoodToPerson: called");

        mFoodEaten.get(personName).setName(personName);
        mFoodEaten.get(personName).addFoodEaten(food);

        mainActivityPresenter.clearInputFields(etFoodName, etFoodPrice, etFoodTax);
    }

    @Override
    public void clearInputFields(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setText("");
        }
    }

    @Override
    public void navigateToSplitActivity() {
        mPersonWithFoodList = new ArrayList<>();
        for (String personName : mPersonNamesList) {
            if (!mFoodEaten.get(personName).isEmpty()) {
                mPersonWithFoodList.add(mFoodEaten.get(personName));
            }
        }
        startActivity(SplitActivity.getStartIntent(this, mPersonWithFoodList));
    }
}
