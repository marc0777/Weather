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
    private List<Location> searchResult;
    private Storage storage;

    SearchViewModel () {
        storage = new RealmStorage();
    }

    boolean isLocationSaved (int position) {
        return storage.isLocationPresent(searchResult.get(position).getId());
    }

    List<Location> getResult() {
        return searchResult;
    }

    String saveLocation(int position) {
        Location location = searchResult.get(position);
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

    void search(String query, final Context context, final ListView listView) {
        Call call = Utils.INSTANCE.getSearchAPI().searchCities(query, Utils.INSTANCE.getLocale());
        final SearchViewModel viewModel = this;
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.isSuccessful()) {
                    searchResult = response.body();
                    ListAdapter adapter = new SearchAdapter(context, viewModel);
                    listView.setAdapter(adapter);

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
