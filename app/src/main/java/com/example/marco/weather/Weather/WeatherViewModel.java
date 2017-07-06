package com.example.marco.weather.Weather;

import android.util.Log;
import android.widget.TextView;

import com.example.marco.weather.Data.Forecast;
import com.example.marco.weather.Data.Location;
import com.example.marco.weather.Tool.RealmStorage;
import com.example.marco.weather.Tool.Storage;
import com.example.marco.weather.Tool.Utils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

class WeatherViewModel {
    private Storage storage;

    WeatherViewModel () {
        storage = new RealmStorage();
    }

    int getLocationsNumber() {
        return storage.size();
    }

    Location getLocation(int position) {
        return storage.getLocation(position);
    }

    void setWeather(final int position, final TextView textView) {
        final Location location = getLocation(position);
        if (System.currentTimeMillis() - location.getUpdateTime() > 300000) {
            Utils.INSTANCE.getForecastAPI().getForecast(location.getId(), Utils.INSTANCE.getLocale()).enqueue(new retrofit2.Callback<Forecast>() {
                @Override
                public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                    if (response.isSuccessful()) {
                        storage.updateLocationWeather(location, response.body(), System.currentTimeMillis());
                        textView.setText(getWeather(location));
                    }
                    else {
                        try {
                            Log.e("Network: ",response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<Forecast> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
        else textView.setText(getWeather(location));
    }

    private String getWeather(Location location) {
        return "Location ID: " + location.getId() + "\nLocation name: "+ location.getName() + "\nLocation country: " + location.getCountry() + "\nActual weather: " + location.getForecast(0).getDayText();
    }
}
