package com.abhinav.model;

import com.google.gson.JsonObject;

public class DetailsModel {
    String name;
    JsonObject wind, main;

    public DetailsModel(String name, JsonObject wind, JsonObject main) {
        this.name = name;
        this.wind = wind;
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public JsonObject getWind() {
        return wind;
    }

    public JsonObject getMain() {
        return main;
    }
}
