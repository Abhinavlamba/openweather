package com.abhinav.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abhinav.Repository.DetailsRepository;
import com.abhinav.model.DetailsModel;

public class DetailsViewModel extends ViewModel {

    String cityName, api_key;
    private MutableLiveData<DetailsModel> details;
    private DetailsRepository detailsRepository;

    public void init(String cityName, String api_key) {
        this.cityName = cityName;
        this.api_key = api_key;
        if (details != null) {
            return;
        }
        detailsRepository = DetailsRepository.getInstance(cityName, api_key);
//        cities = cityRepository.getCities("google-news", "API_KEY");
        details = detailsRepository.getDetails();
    }

    public LiveData<DetailsModel> getDetailsRepository() {
        return details;
    }
}