package com.abhinav.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.abhinav.model.CitiesModel;
import com.abhinav.openweather.DetailsActivity;
import com.abhinav.openweather.R;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    Activity activity;
    private ArrayList<CitiesModel> mDataset;

    public CityAdapter(ArrayList<CitiesModel> myDataset, Activity activity) {
        this.mDataset = myDataset;
        this.activity = activity;
    }

    @Override

    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_city_adapter, parent, false);
        return new CityAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CityAdapter.ViewHolder holder, int position) {
//        Toast.makeText(activity, String.valueOf(mDataset.size()), Toast.LENGTH_SHORT).show();
        holder.city.setText(mDataset.get(position).getName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailsActivity.class);
                intent.putExtra("cityName", mDataset.get(holder.getAdapterPosition()).getName());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView city;
        ConstraintLayout constraintLayout;

        public ViewHolder(View view) {
            super(view);
            city = view.findViewById(R.id.city);
            constraintLayout = view.findViewById(R.id.cityLayout);
        }
    }
}
