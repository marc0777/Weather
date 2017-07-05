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
import io.realm.RealmResults;

public class CityWeatherFragment extends Fragment {

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

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        final TextView textView = (TextView) view;

        final RealmResults<City> cities = Realm.getDefaultInstance().where(City.class).findAll();
        City city = cities.get(position);

        textView.setText("City ID: " + city.getId() + "\nCity name: "+ city.getName() + "\nCity country: " + city.getCountry() + "\nActual weather: " + city.getForecast(0).getDayText());

        return view;
    }
}