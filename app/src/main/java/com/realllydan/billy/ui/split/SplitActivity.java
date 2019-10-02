package com.realllydan.billy.ui.split;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.realllydan.billy.R;
import com.realllydan.billy.data.adapters.SplitListAdapter;
import com.realllydan.billy.data.models.PersonWithFood;

import java.util.ArrayList;
import java.util.List;

public class SplitActivity extends AppCompatActivity {

    private static final String TAG = "SplitActivity";
    public static final String EXTRAS_PERSON_LIST = "person_list";

    public static Intent getStartIntent(Context context,  List<PersonWithFood> personWithFoodList) {
        Intent intent = new Intent(context, SplitActivity.class);
        intent.putParcelableArrayListExtra(EXTRAS_PERSON_LIST, (ArrayList<? extends Parcelable>) personWithFoodList);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        Log.d(TAG, "onCreate: called");

        initToolbar();

        try {
            initRecyclerView(getIntent().getExtras().<PersonWithFood>getParcelableArrayList(EXTRAS_PERSON_LIST));
        } catch (NullPointerException e) {
            Log.e(TAG, "Error: Start Activity using SplitActivity.getStartIntent()");
        }
    }

    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_split_details));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView(List<PersonWithFood> mPersonWithFoodList) {
        Log.d(TAG, "initRecyclerView: called");
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        SplitListAdapter splitListAdapter = new SplitListAdapter(mPersonWithFoodList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(splitListAdapter);
    }
}
