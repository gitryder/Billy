package com.realllydan.billy.data.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.realllydan.billy.R;
import com.realllydan.billy.data.models.PersonWithFood;

import java.util.ArrayList;

public class SplitListAdapter extends RecyclerView.Adapter<SplitListAdapter.ViewHolder> {

    private static final String TAG = "SplitListAdapter";

    //vars
    private ArrayList<PersonWithFood> mPersonWithFoodList;

    public SplitListAdapter(ArrayList<PersonWithFood> mPersonWithFoodList) {
        this.mPersonWithFoodList = mPersonWithFoodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_split_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.billiName.setText(mPersonWithFoodList.get(position).getName());
        holder.billiAmount.setText("₹" + mPersonWithFoodList.get(position).getSplitCost());
        holder.billiFoodEaten.setText(mPersonWithFoodList.get(position).getFoodEatenAsString());
    }

    @Override
    public int getItemCount() {
        return mPersonWithFoodList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView billiName, billiAmount, billiFoodEaten;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            billiName = itemView.findViewById(R.id.person_name);
            billiAmount = itemView.findViewById(R.id.person_split_amount);
            billiFoodEaten = itemView.findViewById(R.id.person_food_eaten);
        }
    }
}
