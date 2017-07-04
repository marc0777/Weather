package com.example.marco.weather.Weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marco.weather.R;
import com.example.marco.weather.Tool.City;

import io.realm.Realm;

public class CityWeather extends Fragment {

    private int position;

    public static CityWeather newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position",position);
        CityWeather fragment = new CityWeather();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        City city = Realm.getDefaultInstance().where(City.class).findAll().get(position);

        WeatherModel.update();

        View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        TextView textView = (TextView) view;
        //textView.setText("City ID: " + city.getId() + "\nCity name: "+ city.getName() + "\nCity country: " + city.getCountry() + "\nActual weather: " + city.getForecast(0).getDayText());
        return view;
    }
}