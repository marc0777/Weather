package com.example.marco.weather.Tool

import com.example.marco.weather.Data.Forecast
import com.example.marco.weather.Data.Location
import com.example.marco.weather.Data.Weather

import io.realm.Realm

class RealmStorage : Storage {

    private val realm: Realm = Realm.getDefaultInstance()

    override val locations: List<Location>
        get() = realm.where<Location>(Location::class.java).findAll()

    override fun getLocation(position: Int): Location {
        return realm.where<Location>(Location::class.java).findAll()[position]
    }

    override fun addLocation(location: Location) {
        realm.executeTransaction { realm -> realm.copyToRealm(location) }
    }

    override fun removeLocation(id: Int) {
        realm.executeTransaction { findLocation(id)!!.deleteFromRealm() }
    }

    override fun findLocation(id: Int): Location? {
        return realm.where<Location>(Location::class.java).equalTo("id", id).findFirst()
    }

    override fun isLocationPresent(id: Int): Boolean {
        return findLocation(id)!=null
    }

    override fun updateLocationWeather(location: Location, forecast: Forecast, time: Long) {
        val forecastList = forecast.forecast
        realm.executeTransaction {
            for (i in 0..4) location.setForecastAt(i, forecastList[i])
            location.updateTime = time
        }
    }

    override fun size(): Int {
        return realm.where<Location>(Location::class.java).findAll().size
    }

}
