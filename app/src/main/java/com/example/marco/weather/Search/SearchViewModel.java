package com.example.marco.weather.Search;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.marco.weather.Data.Location;
import com.example.marco.weather.Tool.RealmStorage;
import com.example.marco.weather.Tool.Storage;
import com.example.marco.weather.Tool.Utils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class SearchViewModel {
    private List<Location> result;
    private Storage storage;

    SearchViewModel () {
        storage = new RealmStorage();
    }

    boolean isLocationSaved (int position) {
        return storage.isLocationPresent(result.get(position).getId());
    }

    List<Location> getResult() {
        return result;
    }

    String saveLocation(int position) {
        Location location = result.get(position);
        storage.addLocation(location);
        return location.getName();
    }

    String deleteLocation(int position) {
        Location location = storage.getLocation(position);
        String name = location.getName();
        storage.removeLocation(location.getId());
        return name;
    }

    String getWeather(int position) {
        return storage.getLocation(position).getForecast(0).toString();
    }

    void search(String query, final SearchAdapter adapter) {
        Utils.getSearchAPI().searchCities(query, Utils.getLocale()).enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.isSuccessful()) {
                    result = response.body();
                    adapter.clear();
                    adapter.addAll(result);

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
            public void onFailure(Call<List<Location>> call, Throwable t) {

            }
        });
    }
}
