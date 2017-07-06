package com.example.marco.weather.Data

import com.google.gson.*
import java.lang.reflect.Type
import io.realm.RealmList
import io.realm.RealmObject

open class Location(var id: Int = 0, var name: String = "", var country: String = "", var updateTime: Long = 0, var forecast: RealmList<Weather> = RealmList<Weather>()) : RealmObject() {

    fun getForecastAt(date: Int): Weather {
        return forecast[date]
    }

    fun setForecastAt(date: Int, weather: Weather) {
        forecast.add(date, weather)
    }

    class CityDeserializer : JsonDeserializer<Location> {

        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Location {
            val location = Gson().fromJson<Location>(json, Location::class.java)
            val jsonObject = json.asJsonObject
            location.id = jsonObject.get("Key").asInt
            location.name = jsonObject.get("LocalizedName").asString
            location.country = jsonObject.getAsJsonObject("Country").get("LocalizedName").asString
            return location
        }
    }
}
