package com.example.marco.weather.Search;

import com.example.marco.weather.Tool.City;
import com.example.marco.weather.Tool.DataRetriever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


class SearchModel {


    private final static String savedResult = "[\n" +
            "  {\n" +
            "    \"Version\": 1,\n" +
            "    \"Key\": \"216517\",\n" +
            "    \"Type\": \"City\",\n" +
            "    \"Rank\": 45,\n" +
            "    \"LocalizedName\": \"Mestre\",\n" +
            "    \"EnglishName\": \"Mestre\",\n" +
            "    \"PrimaryPostalCode\": \"\",\n" +
            "    \"Region\": {\n" +
            "      \"ID\": \"EUR\",\n" +
            "      \"LocalizedName\": \"Europe\",\n" +
            "      \"EnglishName\": \"Europe\"\n" +
            "    },\n" +
            "    \"Country\": {\n" +
            "      \"ID\": \"IT\",\n" +
            "      \"LocalizedName\": \"Italy\",\n" +
            "      \"EnglishName\": \"Italy\"\n" +
            "    },\n" +
            "    \"AdministrativeArea\": {\n" +
            "      \"ID\": \"34\",\n" +
            "      \"LocalizedName\": \"Veneto\",\n" +
            "      \"EnglishName\": \"Veneto\",\n" +
            "      \"Level\": 1,\n" +
            "      \"LocalizedType\": \"Region\",\n" +
            "      \"EnglishType\": \"Region\",\n" +
            "      \"CountryID\": \"IT\"\n" +
            "    },\n" +
            "    \"TimeZone\": {\n" +
            "      \"Code\": \"CEST\",\n" +
            "      \"Name\": \"Europe/Rome\",\n" +
            "      \"GmtOffset\": 2,\n" +
            "      \"IsDaylightSaving\": true,\n" +
            "      \"NextOffsetChange\": \"2017-10-29T01:00:00Z\"\n" +
            "    },\n" +
            "    \"GeoPosition\": {\n" +
            "      \"Latitude\": 45.473,\n" +
            "      \"Longitude\": 12.239,\n" +
            "      \"Elevation\": {\n" +
            "        \"Metric\": {\n" +
            "          \"Value\": 2,\n" +
            "          \"Unit\": \"m\",\n" +
            "          \"UnitType\": 5\n" +
            "        },\n" +
            "        \"Imperial\": {\n" +
            "          \"Value\": 6,\n" +
            "          \"Unit\": \"ft\",\n" +
            "          \"UnitType\": 0\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    \"IsAlias\": false,\n" +
            "    \"SupplementalAdminAreas\": [\n" +
            "      {\n" +
            "        \"Level\": 2,\n" +
            "        \"LocalizedName\": \"Venezia\",\n" +
            "        \"EnglishName\": \"Venezia\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"Level\": 3,\n" +
            "        \"LocalizedName\": \"Venezia\",\n" +
            "        \"EnglishName\": \"Venezia\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"DataSets\": [\n" +
            "      \"Alerts\"\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"Version\": 1,\n" +
            "    \"Key\": \"1456856\",\n" +
            "    \"Type\": \"City\",\n" +
            "    \"Rank\": 85,\n" +
            "    \"LocalizedName\": \"Mestre\",\n" +
            "    \"EnglishName\": \"Mestre\",\n" +
            "    \"PrimaryPostalCode\": \"\",\n" +
            "    \"Region\": {\n" +
            "      \"ID\": \"EUR\",\n" +
            "      \"LocalizedName\": \"Europe\",\n" +
            "      \"EnglishName\": \"Europe\"\n" +
            "    },\n" +
            "    \"Country\": {\n" +
            "      \"ID\": \"ES\",\n" +
            "      \"LocalizedName\": \"Spain\",\n" +
            "      \"EnglishName\": \"Spain\"\n" +
            "    },\n" +
            "    \"AdministrativeArea\": {\n" +
            "      \"ID\": \"GA\",\n" +
            "      \"LocalizedName\": \"Galicia\",\n" +
            "      \"EnglishName\": \"Galicia\",\n" +
            "      \"Level\": 1,\n" +
            "      \"LocalizedType\": \"Autonomous Community\",\n" +
            "      \"EnglishType\": \"Autonomous Community\",\n" +
            "      \"CountryID\": \"ES\"\n" +
            "    },\n" +
            "    \"TimeZone\": {\n" +
            "      \"Code\": \"CEST\",\n" +
            "      \"Name\": \"Europe/Madrid\",\n" +
            "      \"GmtOffset\": 2,\n" +
            "      \"IsDaylightSaving\": true,\n" +
            "      \"NextOffsetChange\": \"2017-10-29T01:00:00Z\"\n" +
            "    },\n" +
            "    \"GeoPosition\": {\n" +
            "      \"Latitude\": 43.226,\n" +
            "      \"Longitude\": -7.189,\n" +
            "      \"Elevation\": {\n" +
            "        \"Metric\": {\n" +
            "          \"Value\": 393,\n" +
            "          \"Unit\": \"m\",\n" +
            "          \"UnitType\": 5\n" +
            "        },\n" +
            "        \"Imperial\": {\n" +
            "          \"Value\": 1289,\n" +
            "          \"Unit\": \"ft\",\n" +
            "          \"UnitType\": 0\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    \"IsAlias\": false,\n" +
            "    \"SupplementalAdminAreas\": [\n" +
            "      {\n" +
            "        \"Level\": 3,\n" +
            "        \"LocalizedName\": \"Ribeira de Piquín\",\n" +
            "        \"EnglishName\": \"Ribeira de Piquín\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"DataSets\": [\n" +
            "      \"Alerts\",\n" +
            "      \"MinuteCast\"\n" +
            "    ]\n" +
            "  }\n" +
            "]";

    static List<City> getSearchResult(String query) {
        return parseJson(savedResult);
    }

    private static List<City> parseJson (String json) {
        List<City> parsedJson = new ArrayList<>();
        try {
            JSONArray jsonCities = new JSONArray(json);
            for (int i = 0; i < jsonCities.length(); i++) {
                parsedJson.add(jsonToCity(jsonCities.getJSONObject(i)));
            }
        } catch (final JSONException ignored) {}
        return parsedJson;
    }

    private static String getJson (String query) {
        String json = "[]";
        try {
            json = new DataRetriever().execute("locations/v1/cities/search","q="+query).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static City jsonToCity (JSONObject json) throws JSONException {

        City temp = new City();

        temp.setId(json.getString("Key"));
        temp.setName(json.getString("LocalizedName"));
        temp.setCountry(json.getJSONObject("Country").getString("LocalizedName"));

        return temp;
    }
}
