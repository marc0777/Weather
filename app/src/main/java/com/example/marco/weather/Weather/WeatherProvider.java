package com.example.marco.weather.Weather;

import android.util.Log;

import com.example.marco.weather.Tool.City;
import com.example.marco.weather.Tool.DataRetriever;
import com.example.marco.weather.Tool.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import io.realm.Realm;
import io.realm.RealmResults;

class WeatherProvider {
    private final static String savedResult = "{\n" +
            "  \"Headline\": {\n" +
            "    \"EffectiveDate\": \"2017-07-04T20:00:00+02:00\",\n" +
            "    \"EffectiveEpochDate\": 1499191200,\n" +
            "    \"Severity\": 5,\n" +
            "    \"Text\": \"Temporali martedì sera\",\n" +
            "    \"Category\": \"thunderstorm\",\n" +
            "    \"EndDate\": \"2017-07-05T02:00:00+02:00\",\n" +
            "    \"EndEpochDate\": 1499212800,\n" +
            "    \"MobileLink\": \"http://m.accuweather.com/it/it/mestre/216517/extended-weather-forecast/216517?unit=c&lang=it-it\",\n" +
            "    \"Link\": \"http://www.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?unit=c&lang=it-it\"\n" +
            "  },\n" +
            "  \"DailyForecasts\": [\n" +
            "    {\n" +
            "      \"Date\": \"2017-07-03T07:00:00+02:00\",\n" +
            "      \"EpochDate\": 1499058000,\n" +
            "      \"Temperature\": {\n" +
            "        \"Minimum\": {\n" +
            "          \"Value\": 20.1,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        },\n" +
            "        \"Maximum\": {\n" +
            "          \"Value\": 27.9,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        }\n" +
            "      },\n" +
            "      \"Day\": {\n" +
            "        \"Icon\": 3,\n" +
            "        \"IconPhrase\": \"Parzialmente soleggiato\"\n" +
            "      },\n" +
            "      \"Night\": {\n" +
            "        \"Icon\": 34,\n" +
            "        \"IconPhrase\": \"Prevalentemente sereno\"\n" +
            "      },\n" +
            "      \"Sources\": [\n" +
            "        \"AccuWeather\"\n" +
            "      ],\n" +
            "      \"MobileLink\": \"http://m.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=1&unit=c&lang=it-it\",\n" +
            "      \"Link\": \"http://www.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=1&unit=c&lang=it-it\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Date\": \"2017-07-04T07:00:00+02:00\",\n" +
            "      \"EpochDate\": 1499144400,\n" +
            "      \"Temperature\": {\n" +
            "        \"Minimum\": {\n" +
            "          \"Value\": 20.5,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        },\n" +
            "        \"Maximum\": {\n" +
            "          \"Value\": 27.6,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        }\n" +
            "      },\n" +
            "      \"Day\": {\n" +
            "        \"Icon\": 2,\n" +
            "        \"IconPhrase\": \"Prevalentemente soleggiato\"\n" +
            "      },\n" +
            "      \"Night\": {\n" +
            "        \"Icon\": 41,\n" +
            "        \"IconPhrase\": \"Parzialmente Nuvoloso Con Isolati Temporali\"\n" +
            "      },\n" +
            "      \"Sources\": [\n" +
            "        \"AccuWeather\"\n" +
            "      ],\n" +
            "      \"MobileLink\": \"http://m.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=2&unit=c&lang=it-it\",\n" +
            "      \"Link\": \"http://www.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=2&unit=c&lang=it-it\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Date\": \"2017-07-05T07:00:00+02:00\",\n" +
            "      \"EpochDate\": 1499230800,\n" +
            "      \"Temperature\": {\n" +
            "        \"Minimum\": {\n" +
            "          \"Value\": 21.2,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        },\n" +
            "        \"Maximum\": {\n" +
            "          \"Value\": 29,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        }\n" +
            "      },\n" +
            "      \"Day\": {\n" +
            "        \"Icon\": 17,\n" +
            "        \"IconPhrase\": \"Parzialmente soleggiato con episodi temporaleschi\"\n" +
            "      },\n" +
            "      \"Night\": {\n" +
            "        \"Icon\": 33,\n" +
            "        \"IconPhrase\": \"Sereno\"\n" +
            "      },\n" +
            "      \"Sources\": [\n" +
            "        \"AccuWeather\"\n" +
            "      ],\n" +
            "      \"MobileLink\": \"http://m.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=3&unit=c&lang=it-it\",\n" +
            "      \"Link\": \"http://www.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=3&unit=c&lang=it-it\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Date\": \"2017-07-06T07:00:00+02:00\",\n" +
            "      \"EpochDate\": 1499317200,\n" +
            "      \"Temperature\": {\n" +
            "        \"Minimum\": {\n" +
            "          \"Value\": 22.1,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        },\n" +
            "        \"Maximum\": {\n" +
            "          \"Value\": 29.6,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        }\n" +
            "      },\n" +
            "      \"Day\": {\n" +
            "        \"Icon\": 3,\n" +
            "        \"IconPhrase\": \"Parzialmente soleggiato\"\n" +
            "      },\n" +
            "      \"Night\": {\n" +
            "        \"Icon\": 38,\n" +
            "        \"IconPhrase\": \"Prevalentemente Nuvoloso\"\n" +
            "      },\n" +
            "      \"Sources\": [\n" +
            "        \"AccuWeather\"\n" +
            "      ],\n" +
            "      \"MobileLink\": \"http://m.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=4&unit=c&lang=it-it\",\n" +
            "      \"Link\": \"http://www.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=4&unit=c&lang=it-it\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Date\": \"2017-07-07T07:00:00+02:00\",\n" +
            "      \"EpochDate\": 1499403600,\n" +
            "      \"Temperature\": {\n" +
            "        \"Minimum\": {\n" +
            "          \"Value\": 22.2,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        },\n" +
            "        \"Maximum\": {\n" +
            "          \"Value\": 30,\n" +
            "          \"Unit\": \"C\",\n" +
            "          \"UnitType\": 17\n" +
            "        }\n" +
            "      },\n" +
            "      \"Day\": {\n" +
            "        \"Icon\": 4,\n" +
            "        \"IconPhrase\": \"Nubi sparse\"\n" +
            "      },\n" +
            "      \"Night\": {\n" +
            "        \"Icon\": 34,\n" +
            "        \"IconPhrase\": \"Prevalentemente sereno\"\n" +
            "      },\n" +
            "      \"Sources\": [\n" +
            "        \"AccuWeather\"\n" +
            "      ],\n" +
            "      \"MobileLink\": \"http://m.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=5&unit=c&lang=it-it\",\n" +
            "      \"Link\": \"http://www.accuweather.com/it/it/mestre/216517/daily-weather-forecast/216517?day=5&unit=c&lang=it-it\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    static void update() {
        RealmResults<City> cities = Realm.getDefaultInstance().where(City.class).findAll();

        for (int i = 0; i < cities.size(); i++) {
            final City city = cities.get(i);
            if (System.currentTimeMillis() - city.getUpdateTime() > 300000 ) {
                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        updateCity(city);
                        city.setUpdateTime(System.currentTimeMillis());
                    }
                });
            }
        }

    }

    private static void updateCity (City city) {
        try {
            JSONArray jsonForecast = new JSONObject(savedResult).getJSONArray("DailyForecasts");
            for (int i = 0; i < 5; i++) {
                JSONObject dayForecast = jsonForecast.getJSONObject(i);
                city.addForecast(jsonToWeather(dayForecast));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static Weather jsonToWeather (JSONObject json) throws JSONException {
        Weather weather = new Weather();

        JSONObject temp = json.getJSONObject("Temperature");
        weather.setMinT(temp.getJSONObject("Minimum").getString("Value"));
        weather.setMaxT(temp.getJSONObject("Maximum").getString("Value"));

        temp = json.getJSONObject("Day");
        weather.setDayIcon(temp.getString("Icon"));
        weather.setDayText(temp.getString("IconPhrase"));

        temp = json.getJSONObject("Night");
        weather.setNightIcon(temp.getString("Icon"));
        weather.setNightText(temp.getString("IconPhrase"));

        return weather;
    }

    private static String getJson (String id) {
        String json = "[]";
        try {
            json = new DataRetriever().execute("forecasts/v1/daily/5day/"+id,"language=en-us"+"&"+"metric=true").get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return json;
    }
}
