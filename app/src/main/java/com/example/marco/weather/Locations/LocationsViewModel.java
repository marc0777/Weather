package com.example.marco.weather.Locations;

import com.example.marco.weather.Data.Location;
import com.example.marco.weather.Tool.RealmStorage;
import com.example.marco.weather.Tool.Storage;

import java.util.List;

class LocationsViewModel {
    private Storage storage;

    LocationsViewModel () {
        storage = new RealmStorage();
    }

    List<Location> getLocations() {
        return storage.getLocations();
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
}
