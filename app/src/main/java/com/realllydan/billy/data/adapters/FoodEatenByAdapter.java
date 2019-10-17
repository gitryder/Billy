package com.realllydan.billy.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.realllydan.billy.R;

import java.util.ArrayList;
import java.util.List;

public class FoodEatenByAdapter extends ArrayAdapter<String> {

    private List<String> mPersonNamesList;
    private Context context;

    public FoodEatenByAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        mPersonNamesList = new ArrayList<>();
    }

    public void updateListWithAddedData(List<String> mPersonNamesList) {
        if (mPersonNamesList == null) mPersonNamesList = new ArrayList<>();
        this.mPersonNamesList.clear();
        this.mPersonNamesList.addAll(mPersonNamesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_spinner_item, parent, false);
        TextView mSpinnerPersonName = view.findViewById(R.id.spinner_person_name);
        mSpinnerPersonName.setText(mPersonNamesList.get(position));
        return view;
    }

    @Override
    public int getCount() {
        return mPersonNamesList.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return mPersonNamesList.get(position);
    }
}
