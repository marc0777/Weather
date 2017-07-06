package com.example.marco.weather.Data

import com.google.gson.annotations.SerializedName

import io.realm.RealmList

class Forecast(@SerializedName("DailyForecasts") var forecast: RealmList<Weather> = RealmList())