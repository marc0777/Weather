package com.example.marco.weather.Weather;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marco.weather.R;
import com.example.marco.weather.Tool.AccuweatherAPI;
import com.example.marco.weather.Tool.City;
import com.example.marco.weather.Tool.Forecast;
import com.example.marco.weather.Tool.Utils;
import com.example.marco.weather.Tool.Weather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_weather, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.weather_pager);
        viewPager.setAdapter(new WeatherAdapter(getFragmentManager()));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.weather_tabs);
        tabLayout.setupWithViewPager(viewPager);

        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<City> cities = realm.where(City.class).findAll();

        for (int i = 0; i < cities.size(); i++) {
            final City city = cities.get(i);
            if (System.currentTimeMillis() - city.getUpdateTime() > 300000) {
                getAPI().getForecast(city.getId(), Utils.getLocale()).enqueue(new retrofit2.Callback<Forecast>() {
                    @Override
                    public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                        Forecast forecastObj = response.body();
                        if (forecastObj != null) {
                            List<Weather> forecast = forecastObj.getForecast();
                            realm.beginTransaction();
                            for (int i = 0; i < 5; i++)
                                city.setForecast(i, forecast.get(i));
                            city.setUpdateTime(System.currentTimeMillis());
                            realm.commitTransaction();
                        }
                        else Log.e("Network: ","Unauthorized");
                    }
                    @Override
                    public void onFailure(Call<Forecast> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }
        return view;
    }

    private static AccuweatherAPI getAPI() {
        Gson gson = new GsonBuilder()
                .setLenient().registerTypeAdapter(Weather.class, new Weather.WeatherDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AccuweatherAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(AccuweatherAPI.class);
    }
}

