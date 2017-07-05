package com.example.marco.weather.Tool;

import com.example.marco.weather.Data.Forecast;
import com.example.marco.weather.Data.Location;

import java.util.List;



public interface Storage {
    List<Location> getLocations();
    Location getLocation(int position);
    void addLocation(Location location);
    void removeLocation(int id);
    Location findLocation(int id);
    boolean isLocationPresent(int id);
    void updateLocationWeather(Location location, Forecast forecast, long time);
    int size();
}

