package com.example.marco.weather.Data

import com.google.gson.*

import java.lang.reflect.Type

import io.realm.RealmObject

open class Weather(var dayText: String = "", var dayIcon: Int = 0, var nightText: String = "",
                   var nightIcon: Int = 0, var minT: Float = 0.toFloat(), var maxT: Float = 0.toFloat()
                    ) : RealmObject() {


    class WeatherDeserializer : JsonDeserializer<Weather> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Weather {
            val jsonObject = json.asJsonObject

            val weather = Gson().fromJson<Weather>(json, Weather::class.java)

            var temp = jsonObject.getAsJsonObject("Temperature")
            weather.minT = temp.getAsJsonObject("Minimum").get("Value").asFloat
            weather.maxT = temp.getAsJsonObject("Maximum").get("Value").asFloat

            temp = jsonObject.getAsJsonObject("Day")
            weather.dayIcon = temp.get("Icon").asInt
            weather.dayText = temp.get("IconPhrase").asString

            temp = jsonObject.getAsJsonObject("Night")
            weather.nightIcon = temp.get("Icon").asInt
            weather.nightText = temp.get("IconPhrase").asString

            return weather
        }
    }
}
