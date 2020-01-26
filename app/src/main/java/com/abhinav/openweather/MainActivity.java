package com.abhinav.openweather;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhinav.Adapter.CityAdapter;
import com.abhinav.ViewModel.CitiesViewmodel;
import com.abhinav.model.CitiesModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CityAdapter cityAdapter;
    RecyclerView rvCities;
    CitiesViewmodel citiesViewmodel;
    ArrayList<CitiesModel> citiesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvCities = findViewById(R.id.rvCities);

        citiesViewmodel = ViewModelProviders.of(this).get(CitiesViewmodel.class);
        citiesViewmodel.init();
        citiesViewmodel.getCitiesRepository().observe(this, new Observer<List<CitiesModel>>() {
            @Override
            public void onChanged(List<CitiesModel> cityResponse) {
                cityAdapter.notifyDataSetChanged();
            }
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (cityAdapter == null) {
            cityAdapter = new CityAdapter((ArrayList<CitiesModel>) citiesViewmodel.getCitiesRepository().getValue(), MainActivity.this);
            rvCities.setLayoutManager(new LinearLayoutManager(this));
            rvCities.setAdapter(cityAdapter);
            rvCities.setItemAnimator(new DefaultItemAnimator());
            rvCities.setNestedScrollingEnabled(true);
        } else {
            cityAdapter.notifyDataSetChanged();
        }
    }

}
