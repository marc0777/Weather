package com.example.marco.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Cities {

    Context context;

    private List<HashMap<String, String>> cities = new ArrayList<>();

    public Cities (String json, Context context) {
        this.context=context;
        if (json != null) {
            try {
                JSONArray contacts = new JSONArray(json);

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String id = c.getString("Key");
                    String name = c.getString("LocalizedName");

                    JSONObject phone = c.getJSONObject("Country");
                    String country = phone.getString("LocalizedName");

                    HashMap<String, String> city = new HashMap<>();

                    city.put("id", id);
                    city.put("name", name);
                    city.put("country", country);
                    city.put("updateTime", "0");

                    cities.add(city);
                }
            } catch (final JSONException e) {
                Log.e("Json parsing error",e.getMessage(), e);
                Toast.makeText(context,
                        "Json parsing error: " + e.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        } else {
            Toast.makeText(context,
                    "Couldn't get json from server. Check LogCat for possible errors!",
                    Toast.LENGTH_LONG)
                    .show();

        }
    }

    public List<HashMap<String, String>> getCities() {
        return cities;
    }
}
