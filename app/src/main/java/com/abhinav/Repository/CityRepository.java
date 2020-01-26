package com.abhinav.Repository;

import androidx.lifecycle.MutableLiveData;

import com.abhinav.Interface.CitiesApi;
import com.abhinav.Retrofit.RetrofitService;
import com.abhinav.model.CitiesModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityRepository {

    private static CityRepository cityRepository;
    private ArrayList<CitiesModel> dataSet = new ArrayList<>();
    private CitiesApi citiesApi;

    public CityRepository() {
        citiesApi = RetrofitService.cteateService(CitiesApi.class);
    }

    public static CityRepository getInstance() {
        if (cityRepository == null) {
            cityRepository = new CityRepository();
        }
        return cityRepository;
    }

    public MutableLiveData<List<CitiesModel>> getCities() {
        MutableLiveData<List<CitiesModel>> data = new MutableLiveData<>();
        citiesApi.getCities().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call,
                                   Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonArray jsonArray = response.body().getAsJsonArray("cities");
                    setCities(jsonArray);
                    data.setValue(dataSet);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        data.setValue(dataSet);
        return data;
    }


    private void setCities(JsonArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            dataSet.add(new CitiesModel(jsonObject.get("name").toString().replace('\"', ' ')));
        }
    }
}