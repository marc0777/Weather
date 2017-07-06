package com.example.marco.weather.Weather

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.marco.weather.R

class WeatherFragment : Fragment() {
    internal var viewModel = WeatherViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_weather, container, false)

        val viewPager = view.findViewById(R.id.weather_pager) as ViewPager
        viewPager.adapter = WeatherAdapter(fragmentManager, viewModel)

        val tabLayout = view.findViewById(R.id.weather_tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

        return view
    }
}

