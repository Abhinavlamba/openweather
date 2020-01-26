package com.abhinav.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.abhinav.Interface.DetailsApi;
import com.abhinav.Retrofit.CityDetailsRetrofitService;
import com.abhinav.model.DetailsModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsRepository {

    public static String cityName, api_key;
    private static DetailsRepository detailsRepository;
    private DetailsModel dataSet;
    private DetailsApi detailsApi;

    public DetailsRepository() {
        detailsApi = CityDetailsRetrofitService.cteateService(DetailsApi.class);
    }

    public static DetailsRepository getInstance(String cityName, String api_key) {
        DetailsRepository.cityName = cityName;
        DetailsRepository.api_key = api_key;
        if (detailsRepository == null) {
            detailsRepository = new DetailsRepository();
        }
        return detailsRepository;
    }

    public MutableLiveData<DetailsModel> getDetails() {
        MutableLiveData<DetailsModel> data = new MutableLiveData<>();
        detailsApi.getDetails(cityName, api_key).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call,
                                   Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonObject = response.body();
                    Log.d("ABCD", response + jsonObject.get("name").toString());
                    dataSet = new DetailsModel(jsonObject.get("name").toString(), jsonObject.getAsJsonObject("wind"), jsonObject.getAsJsonObject("main"));
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
}
