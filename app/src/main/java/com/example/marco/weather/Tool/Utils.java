package com.example.marco.weather.Tool;

import com.example.marco.weather.Data.Location;
import com.example.marco.weather.Data.Weather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    public static String getLocale() {
        return Locale.getDefault().toString().toLowerCase().replace("_","-");
    }

    public static AccuweatherAPI getSearchAPI() {
        Gson gson = new GsonBuilder()
                .setLenient().registerTypeAdapter(Location.class, new Location.CityDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AccuweatherAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(AccuweatherAPI.class);
    }

    public static AccuweatherAPI getForecastAPI() {
        Gson gson = new GsonBuilder()
                .setLenient().registerTypeAdapter(Weather.class, new Weather.WeatherDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AccuweatherAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(AccuweatherAPI.class);
    }
}
