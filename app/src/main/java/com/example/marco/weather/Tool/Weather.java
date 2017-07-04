package com.example.marco.weather.Tool;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

import io.realm.RealmObject;
import io.realm.RealmList;

public class Weather extends RealmObject{

    private String dayText;
    private int dayIcon;
    private String nightText;
    private int nightIcon;
    private float minT;
    private float maxT;

    public String getDayText() {
        return dayText;
    }

    public void setDayText(String dayText) {
        this.dayText = dayText;
    }

    public int getDayIcon() {
        return dayIcon;
    }

    public void setDayIcon(int dayIcon) {
        this.dayIcon = dayIcon;
    }

    public void setDayIcon(String dayIcon) {
        this.dayIcon = Integer.parseInt(dayIcon);
    }

    public String getNightText() {
        return nightText;
    }

    public void setNightText(String nightText) {
        this.nightText = nightText;
    }

    public int getNightIcon() {
        return nightIcon;
    }

    public void setNightIcon(int nightIcon) {
        this.nightIcon = nightIcon;
    }

    public void setNightIcon(String nightIcon) {
        this.dayIcon = Integer.parseInt(nightIcon);
    }

    public float getMinT() {
        return minT;
    }

    public void setMinT(float minT) {
        this.minT = minT;
    }

    public void setMinT(String minT) {
        this.minT = Float.parseFloat(minT);
    }


    public float getMaxT() {
        return maxT;
    }

    public void setMaxT(float maxT) {
        this.maxT = maxT;
    }

    public void setMaxT(String maxT) {
        this.minT = Float.parseFloat(maxT);
    }

    public String toString() {
        return "Weather: "+dayText+"\nTemperature: "+maxT;
    }

    public static class WeatherDeserializer implements JsonDeserializer<Weather> {
        @Override
        public Weather deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            Weather weather = new Gson().fromJson(json, Weather.class);

            JsonObject temp = jsonObject.getAsJsonObject("Temperature");
            weather.setMinT(temp.getAsJsonObject("Minimum").get("Value").getAsString());
            weather.setMaxT(temp.getAsJsonObject("Maximum").get("Value").getAsString());

            temp = jsonObject.getAsJsonObject("Day");
            weather.setDayIcon(temp.get("Icon").getAsString());
            weather.setDayText(temp.get("IconPhrase").getAsString());

            temp = jsonObject.getAsJsonObject("Night");
            weather.setNightIcon(temp.get("Icon").getAsString());
            weather.setNightText(temp.get("IconPhrase").getAsString());

            return weather;
        }
    }
}
