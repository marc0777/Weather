package com.example.marco.weather.Tool;

import com.example.marco.weather.Data.Forecast;
import com.example.marco.weather.Data.Location;
import com.example.marco.weather.Data.Weather;

import java.util.List;

import io.realm.Realm;

public class RealmStorage implements Storage {

    private Realm realm;

    public RealmStorage() {
        realm =  Realm.getDefaultInstance();
    }

    public List<Location> getLocations() {
        return realm.where(Location.class).findAll();
    }

    public Location getLocation(int position) {
        return realm.where(Location.class).findAll().get(position);
    }

    public void addLocation (final Location location) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(location);
            }
        });
    }

    public void removeLocation(final int id) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                findLocation(id).deleteFromRealm();
            }
        });
    }

    public Location findLocation(int id) {
        return realm.where(Location.class).equalTo("id",id).findFirst();
    }

    public boolean isLocationPresent(int id) {
        return findLocation(id)!=null;
    }

    public void updateLocationWeather(final Location location, final Forecast forecast, final long time) {
        final List<Weather> forecastList = forecast.getForecast();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (int i = 0; i < 5; i++) location.setForecast(i, forecastList.get(i));
                location.setUpdateTime(time);
            }
        });
    }

    public int size() {
        return realm.where(Location.class).findAll().size();
    }

}
