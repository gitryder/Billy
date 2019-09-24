package com.realllydan.billy.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.realllydan.billy.R;
import com.realllydan.billy.models.Billi;

import java.util.ArrayList;

public class SplitListAdapter extends RecyclerView.Adapter<SplitListAdapter.ViewHolder> {

    private static final String TAG = "SplitListAdapter";

    //vars
    private ArrayList<Billi> mBilliList;

    public SplitListAdapter(ArrayList<Billi> mBilliList) {
        this.mBilliList = mBilliList;
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

        holder.billiName.setText(mBilliList.get(position).getName());
        holder.billiAmount.setText("₹" + mBilliList.get(position).getSplitCost());
        holder.billiFoodEaten.setText(mBilliList.get(position).getDishesEatenAsString());
    }

    @Override
    public int getItemCount() {
        return mBilliList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView billiName, billiAmount, billiFoodEaten;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            billiName = itemView.findViewById(R.id.billi_name);
            billiAmount = itemView.findViewById(R.id.billi_split_amount);
            billiFoodEaten = itemView.findViewById(R.id.billi_food_eaten);
        }
    }
}
