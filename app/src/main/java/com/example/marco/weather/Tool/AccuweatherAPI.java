package com.example.marco.weather.Tool;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccuweatherAPI {
    String BASE_URL = "http://dataservice.accuweather.com/";
    String API_KEY = "KPG9Olkd338PiKKvGTcURXdjc6sRPIfr";

    @GET("/locations/v1/cities/search?apikey="+API_KEY+"&")
    Call<RealmList<City>> searchCities(@Query("q") String query, @Query("language") String language);

    @GET("/forecasts/v1/daily/5day/{id}?apikey="+API_KEY+"&metric=true&")
    Call<Forecast> getForecast(@Path("id") int id, @Query("language") String language);
}