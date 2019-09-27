package com.realllydan.billy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.realllydan.billy.Constants;
import com.realllydan.billy.R;
import com.realllydan.billy.adapters.BilliListAdapter;
import com.realllydan.billy.adapters.SplitListAdapter;
import com.realllydan.billy.models.Billi;

import java.util.ArrayList;
import java.util.HashMap;

public class SplitActivity extends AppCompatActivity {

    private static final String TAG = "SplitActivity";

    //vars
    private ArrayList<Billi> mBilliList = new ArrayList<>();

    public static Intent getStartIntent(Context context,  ArrayList<Billi> billiList) {
        Intent intent = new Intent(context, SplitActivity.class);
        intent.putParcelableArrayListExtra(Constants.EXTRAS_BILLI_LIST, billiList);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        Log.d(TAG, "onCreate: called");

        if (getIntent().hasExtra(Constants.EXTRAS_BILLI_LIST)) {
            mBilliList = getIntent().getExtras().getParcelableArrayList(Constants.EXTRAS_BILLI_LIST);
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
        SplitListAdapter splitListAdapter = new SplitListAdapter(mBilliList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(splitListAdapter);
    }
}
