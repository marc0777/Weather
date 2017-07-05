package com.example.marco.weather.Tool;

import io.realm.Realm;

public class Storage {
    public static void addCity (final City city) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(city);
            }
        });
    }

    private static void removeCity(final int id) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                findById(id).deleteFromRealm();
            }
        });
    }

    public static void removeCity(City city) {
        removeCity(city.getId());
    }


    public static boolean isPresent (final City city) {
        return findById(city.getId())!=null;
    }

    private static City findById (int id) {
        return Realm.getDefaultInstance().where(City.class).equalTo("id",id).findFirst();
    }

}
