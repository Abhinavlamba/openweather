package com.abhinav.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abhinav.Repository.CityRepository;
import com.abhinav.model.CitiesModel;

import java.util.List;

public class CitiesViewmodel extends ViewModel {

    private MutableLiveData<List<CitiesModel>> cities;
    private CityRepository cityRepository;

    public void init() {
        if (cities != null) {
            return;
        }
        cityRepository = CityRepository.getInstance();
//        cities = cityRepository.getCities("google-news", "API_KEY");
        cities = cityRepository.getCities();
    }

    public LiveData<List<CitiesModel>> getCitiesRepository() {
        return cities;
    }

}