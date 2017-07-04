package com.example.marco.weather.Weather;

import android.util.Log;

import com.example.marco.weather.Tool.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class WeatherModel {
    static void update() {
        final RealmResults<City> cities = Realm.getDefaultInstance().where(City.class).findAll();
        Log.d("Update!","");
        for (int i = 0; i < cities.size(); i++) {
            final City city = cities.get(i);
            if (System.currentTimeMillis() - city.getUpdateTime() > 300000) {
                getAPI().getForecast(city.getId(), Utils.getLocale()).enqueue(new retrofit2.Callback<Forecast>() {
                    @Override
                    public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                        final List<Weather> forecast = response.body().getForecast();
                        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                for (int i = 0; i< 5; i++) {
                                    Log.d("Meteo #"+i+": ",forecast.toString());
                                    city.setForecast(i, forecast.get(i));
                                }
                                city.setUpdateTime(System.currentTimeMillis());
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Forecast> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        }
    }

    private static AccuweatherAPI getAPI() {
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