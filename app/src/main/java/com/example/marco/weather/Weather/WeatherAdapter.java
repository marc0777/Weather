package com.example.marco.weather.Weather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.marco.weather.Tool.City;

import io.realm.Realm;
import io.realm.RealmResults;

class WeatherAdapter extends FragmentStatePagerAdapter {
    private final RealmResults<City> cities;

    WeatherAdapter(FragmentManager fm) {
        super(fm);
        cities = Realm.getDefaultInstance().where(City.class).findAll();
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Fragment getItem(int position) {
        return CityWeatherFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return cities.get(position).getName();
    }
}
