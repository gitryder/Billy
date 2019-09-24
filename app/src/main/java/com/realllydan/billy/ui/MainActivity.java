package com.realllydan.billy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.realllydan.billy.R;
import com.realllydan.billy.adapters.BilliListAdapter;
import com.realllydan.billy.models.Billi;
import com.realllydan.billy.models.Dish;
import com.realllydan.billy.util.Calculator;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    //ui components
    private EditText etBilliName, etDishName, etDishPrice, etDishTax, etDishEatenBy;
    private Button bAddBilli, bAddDish, bGetSplit;
    private Spinner mChooseEatenBy;
    private BilliListAdapter billiListAdapter;
    private ArrayAdapter<String> billiSpinnerAdapter;
    private ArrayList<String> mBilliNames;
    private ArrayList<Billi> mBilliList;
    private HashMap<String, Billi> mFoodEaten = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: called");

        etBilliName = findViewById(R.id.et_billi_name);
        etDishName = findViewById(R.id.et_dish_name);
        etDishPrice = findViewById(R.id.et_dish_price);
        etDishTax = findViewById(R.id.et_dish_tax);
        mChooseEatenBy = findViewById(R.id.choose_eaten_by);
        bAddBilli = findViewById(R.id.b_add_billi);
        bAddDish = findViewById(R.id.b_add_dish);
        bGetSplit = findViewById(R.id.b_get_split);

        initToolbar();
        initRecyclerView();
        initSpinner();

        bAddBilli.setOnClickListener(this);
        bAddDish.setOnClickListener(this);
        bGetSplit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_add_billi:
                Log.d(TAG, "Add New Billi");

                if (getStringFromEditText(etBilliName).equals("")) {
                    makeToast("Add a Billi name!");
                } else {
                    addNewBilli(etBilliName.getText().toString().trim());
                }
                break;

            case R.id.b_add_dish:
                Log.d(TAG, "Add Dish To Billi");

                if (mChooseEatenBy.getSelectedItem() != null) {
                    addDishToBilli(getDataFromDishFields(), mChooseEatenBy.getSelectedItem().toString());
                } else {
                    makeToast("No Billi added!");
                }
                break;

            case R.id.b_get_split:
                Log.d(TAG, "Get Split: called");
                navigateToSplitActivity();
                break;

            default:
                break;
        }
    }

    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_add_billis));
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        mBilliNames = new ArrayList<>();
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        billiListAdapter = new BilliListAdapter(mBilliNames);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(billiListAdapter);
    }

    private void initSpinner() {
        Log.d(TAG, "initSpinner: called");
        billiSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.single_spinner_item, mBilliNames);
        mChooseEatenBy.setAdapter(billiSpinnerAdapter);
    }

    private void addNewBilli(String billiName) {
        Log.d(TAG, "addNewBilli: called");
        clearInputField(etBilliName);

        mFoodEaten.put(billiName, new Billi());
        mBilliNames.add(billiName);

        billiListAdapter.notifyDataSetChanged();
        billiSpinnerAdapter.notifyDataSetChanged();
    }

    private void addDishToBilli(Dish dish, String billiName) {
        Log.d(TAG, "addDishToBilli: called");

        mFoodEaten.get(billiName).setName(billiName);
        mFoodEaten.get(billiName).addDishesEaten(dish);
        mFoodEaten.get(billiName).addToSplitCost(Calculator.getTaxInclusivePrice(dish.getDishPrice(), dish.getDishTax()));

        clearInputField(etDishName, etDishPrice, etDishTax);
    }

    private Dish getDataFromDishFields() {
        Log.d(TAG, "getDataFromDishFields: called");
        Dish dish = new Dish();

        String dishName = getStringFromEditText(etDishName);
        String dishPrice = getStringFromEditText(etDishPrice);
        String dishTax = getStringFromEditText(etDishTax);

        if (!dishName.equals("") && !dishPrice.equals("")) {
            dish.setDishName(dishName);
            dish.setDishPrice(Integer.parseInt(dishPrice));
            dish.setDishTax(dishTax.equals("") ? 12 : Integer.parseInt(dishTax));
        }
        Log.d(TAG, "getDataFromDishFields: " + dish.toString());
        return dish;
    }

    private void clearInputField(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setText("");
        }
    }

    private void navigateToSplitActivity() {
        mBilliList = new ArrayList<>();
        for (String billiName : mBilliNames) {
            mBilliList.add(mFoodEaten.get(billiName));
        }

        Intent splitActivityIntent = new Intent(MainActivity.this, SplitActivity.class);
        splitActivityIntent.putParcelableArrayListExtra("mBilliList", mBilliList);
        startActivity(splitActivityIntent);
    }

    private String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    }
}
