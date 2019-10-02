package com.realllydan.billy.data.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.realllydan.billy.R;

import java.util.ArrayList;
import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ViewHolder> {

    private static final String TAG = "PersonListAdapter";
    private List<String> mPersonWithOrderedFoodList = new ArrayList<>();

    public PersonListAdapter() {
    }

    public void updateListWithAddedData(List<String> mPersonWithOrderedFoodList) {
        this.mPersonWithOrderedFoodList.clear();
        this.mPersonWithOrderedFoodList.addAll(mPersonWithOrderedFoodList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_person_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.personName.setText(mPersonWithOrderedFoodList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPersonWithOrderedFoodList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView personName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.person_name);
        }
    }
}
