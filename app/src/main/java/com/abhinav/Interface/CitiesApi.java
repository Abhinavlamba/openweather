package com.abhinav.Interface;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CitiesApi {
    @GET("bins/lw9qf/")
    Call<JsonObject> getCities();
}