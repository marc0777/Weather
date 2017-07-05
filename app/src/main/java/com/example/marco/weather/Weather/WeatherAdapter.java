package com.example.marco.weather.Weather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class WeatherAdapter extends FragmentStatePagerAdapter {
    WeatherViewModel viewModel;

    WeatherAdapter(FragmentManager fm, WeatherViewModel viewModel) {
        super(fm);
        this.viewModel = viewModel;
    }

    @Override
    public int getCount() {
        return viewModel.getLocationsNumber();
    }

    @Override
    public Fragment getItem(int position) {
        return CityWeatherFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return viewModel.getLocation(position).getName();
    }
}
