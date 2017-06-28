package com.example.marco.weather;

import io.realm.RealmObject;

public class City extends RealmObject {
    private int id;
    private String name;
    private String country;
    private int updateTime;

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

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = Integer.parseInt(updateTime);
    }
}
