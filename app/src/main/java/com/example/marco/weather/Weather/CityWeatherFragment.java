package com.example.marco.weather.Weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marco.weather.R;

public class CityWeatherFragment extends Fragment {
    private WeatherViewModel viewModel;
    TextView textView;
    private int position;

    public static CityWeatherFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position",position);
        CityWeatherFragment fragment = new CityWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");
        viewModel = new WeatherViewModel();

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        textView = (TextView) view;

        viewModel.setWeather(position, textView);

        return view;
    }
}