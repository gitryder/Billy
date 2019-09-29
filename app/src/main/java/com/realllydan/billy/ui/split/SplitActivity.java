package com.realllydan.billy.ui.split;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.realllydan.billy.R;
import com.realllydan.billy.data.adapters.SplitListAdapter;
import com.realllydan.billy.data.models.PersonWithFood;

import java.util.ArrayList;

public class SplitActivity extends AppCompatActivity {

    private static final String TAG = "SplitActivity";

    public static final String EXTRAS_PERSON_LIST = "person_list";
    private ArrayList<PersonWithFood> mPersonWithFoodList = new ArrayList<>();

    public static Intent getStartIntent(Context context,  ArrayList<PersonWithFood> personWithFoodList) {
        Intent intent = new Intent(context, SplitActivity.class);
        intent.putParcelableArrayListExtra(EXTRAS_PERSON_LIST, personWithFoodList);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        Log.d(TAG, "onCreate: called");

        try {
            mPersonWithFoodList = getIntent().getExtras().getParcelableArrayList(EXTRAS_PERSON_LIST);
        } catch (NullPointerException e) {
            Log.e(TAG, "Error: Start Activity using SplitActivity.getStartIntent()");
        }

        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_split_details));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        SplitListAdapter splitListAdapter = new SplitListAdapter(mPersonWithFoodList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(splitListAdapter);
    }
}
