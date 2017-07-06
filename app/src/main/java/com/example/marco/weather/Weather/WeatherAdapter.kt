package com.example.marco.weather.Weather

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

internal class WeatherAdapter(fm: FragmentManager, var viewModel: WeatherViewModel) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return viewModel.locationsNumber
    }

    override fun getItem(position: Int): Fragment {
        return CityWeatherFragment.newInstance(position)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return viewModel.getLocation(position).name
    }
}
