package com.example.marco.weather.Tool;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
public class Forecast {

    @SerializedName("DailyForecasts")
    private RealmList<Weather> forecast;

    public RealmList<Weather> getForecast() {
        return forecast;
    }


}

