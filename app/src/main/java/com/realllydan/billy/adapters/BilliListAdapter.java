package com.realllydan.billy.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.realllydan.billy.R;

import java.util.ArrayList;

public class BilliListAdapter extends RecyclerView.Adapter<BilliListAdapter.ViewHolder> {

    private static final String TAG = "BilliListAdapter";

    //vars
    private ArrayList<String> mBilliList;

    public BilliListAdapter(ArrayList<String> mBilliList) {
        this.mBilliList = mBilliList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_billi_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.billiName.setText(mBilliList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBilliList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView billiName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            billiName = itemView.findViewById(R.id.billi_name);
        }
    }
}
