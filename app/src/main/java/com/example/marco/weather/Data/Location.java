package com.example.marco.weather.Data;

import com.google.gson.*;
import java.lang.reflect.Type;
import io.realm.RealmList;
import io.realm.RealmObject;

public class Location extends RealmObject {

    private int id;
    private String name;
    private String country;
    private long updateTime;
    private RealmList<Weather> forecast = new RealmList<>();

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
        return other != null && (other == this || other instanceof Location && id == ((Location) other).id);
    }
    @Override
    public String toString() {
        return name+"\n"+country+"\n"+id+"\n";
    }

    public static class CityDeserializer implements JsonDeserializer<Location> {

        @Override
        public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Location location = new Gson().fromJson(json, Location.class);
            JsonObject jsonObject = json.getAsJsonObject();
            location.setId(jsonObject.get("Key").getAsInt());
            location.setName(jsonObject.get("LocalizedName").getAsString());
            location.setCountry(jsonObject.getAsJsonObject("Country").get("LocalizedName").getAsString());
            return location;
        }
    }
}
