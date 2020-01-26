package com.abhinav.openweather;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.abhinav.ViewModel.DetailsViewModel;
import com.abhinav.model.DetailsModel;
import com.google.gson.JsonObject;

public class DetailsActivity extends AppCompatActivity {
    DetailsViewModel detailsViewModel;
    TextView name, temperature, pressure, windspeed, info, humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");


        init();
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailsViewModel.init(cityName, getString(R.string.key));
        detailsViewModel.getDetailsRepository().observe(this, new Observer<DetailsModel>() {
            @Override
            public void onChanged(DetailsModel details) {
                if (details != null) {
                    JsonObject wind, main;
                    wind = details.getWind();
                    main = details.getMain();
                    temperature.setText(main.get("temp").toString() + "K");
                    pressure.setText("Pressure: " + main.get("pressure").toString());
                    windspeed.setText("Speed : " + wind.get("speed").toString());
                    humidity.setText("Humidity : " + main.get("humidity").toString());
                    name.setText("City : " + details.getName().replace('\"', ' '));
                }

            }
        });


    }

    private void init() {
        temperature = findViewById(R.id.temperature);
        pressure = findViewById(R.id.pressure);
        windspeed = findViewById(R.id.windspeed);
        info = findViewById(R.id.info);
        humidity = findViewById(R.id.humidity);
        name = findViewById(R.id.name);
    }
}
