package com.example.marco.weather.Tool;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

public class City extends RealmObject {

    private int id;
    private String name;
    private String country;
    private long updateTime;
    private RealmList<Weather> forecast = new RealmList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {return country;}

    public void setCountry(String country) {
        this.country = country;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public Weather getForecast (int date) {
        return forecast.get(date);
    }
    public void addForecast (Weather weather) {
        forecast.add(weather);
    }

    @Override
    public boolean equals(Object other) {
        return other != null && (other == this || other instanceof City && id == ((City) other).id);
    }

}
