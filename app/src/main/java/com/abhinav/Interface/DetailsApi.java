package com.abhinav.Interface;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailsApi {

    @GET(".")
    Call<JsonObject> getDetails(@Query("q") String city_name, @Query("appid") String appid);
}
