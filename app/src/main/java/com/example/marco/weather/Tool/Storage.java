package com.example.marco.weather.Tool;

import java.util.HashMap;

import io.realm.Realm;

public class Storage {
    private static void addCity (final City city) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(city);
            }
        });
    }

    public static void addCity (final HashMap<String,String> city) {
        addCity(hashToCity(city));
    }

    private static void removeCity(final int id) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                findById(id).deleteFromRealm();
            }
        });
    }

    public static void removeCity(HashMap<String, String> city) {
        removeCity(Integer.parseInt(city.get("id")));
    }

    public static void removeCity(City city) {
        removeCity(city.getId());
    }

    private static City hashToCity(final HashMap<String,String> city) {
        final City cityObj =  new City();
        cityObj.setId(city.get("id"));
        cityObj.setName(city.get("name"));
        cityObj.setCountry(city.get("country"));
        cityObj.setUpdateTime(0);
        return cityObj;
    }


    public static boolean isPresent (final City city) {
        return findById(city.getId())!=null;
    }
    public static boolean isPresent (final HashMap<String,String> city) {
        return findById(Integer.parseInt(city.get("id")))!=null;
    }

    private static City findById (int id) {
        return Realm.getDefaultInstance().where(City.class).equalTo("id",id).findFirst();
    }

}
