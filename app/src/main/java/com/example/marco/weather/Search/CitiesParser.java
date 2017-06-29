package com.example.marco.weather.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class CitiesParser {

    private List<HashMap<String, String>> cities = new ArrayList<>();

    CitiesParser(String json) {
            try {
                JSONArray jsonCities = new JSONArray(json);
                for (int i = 0; i < jsonCities.length(); i++) {
                    cities.add(jsonToHashMap(jsonCities.getJSONObject(i)));
                }
            } catch (final JSONException ignored) {}

    }

    private HashMap<String, String> jsonToHashMap (JSONObject json) throws JSONException {

        HashMap<String, String> temp = new HashMap<>();

        temp.put("id", json.getString("Key"));
        temp.put("name", json.getString("LocalizedName"));
        temp.put("country", json.getJSONObject("Country").getString("LocalizedName"));

        return temp;

    }

    List<HashMap<String, String>> getParsedCities() {
        return cities;
    }
}
