package com.example.marco.weather.Tool;

import com.google.gson.*;
import java.lang.reflect.Type;
import io.realm.RealmList;
import io.realm.RealmObject;

public class City extends RealmObject {

    private int id;
    private String name;
    private String country;
    private long updateTime;
    private final RealmList<Weather> forecast = new RealmList<>();

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getCountry() {return country;}

    private void setCountry(String country) {
        this.country = country;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public Weather getForecast (int date) {
        return forecast.get(date);
    }

    public void setForecast (int date, Weather weather) {
        forecast.add(date, weather);
    }

    @Override
    public boolean equals(Object other) {
        return other != null && (other == this || other instanceof City && id == ((City) other).id);
    }
    @Override
    public String toString() {
        return name+"\n"+country+"\n"+id+"\n";
    }

    public static class CityDeserializer implements JsonDeserializer<City> {

        @Override
        public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            City city = new Gson().fromJson(json, City.class);
            JsonObject jsonObject = json.getAsJsonObject();
            city.setId(jsonObject.get("Key").getAsInt());
            city.setName(jsonObject.get("LocalizedName").getAsString());
            city.setCountry(jsonObject.getAsJsonObject("Country").get("LocalizedName").getAsString());
            return city;
        }
    }
}
