package com.example.marco.weather.Weather;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marco.weather.R;

public class WeatherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_weather, container, false);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.weather_pager);
        viewPager.setAdapter(new WeatherAdapter(getFragmentManager()));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.weather_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}

