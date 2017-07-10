package com.example.marco.weather.Data;

import com.google.gson.*;

import java.lang.reflect.Type;

import io.realm.RealmObject;

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

    private void setDayText(String dayText) {
        this.dayText = dayText;
    }

    public int getDayIcon() {
        return dayIcon;
    }

    private void setDayIcon(int dayIcon) {
        this.dayIcon = dayIcon;
    }

    private void setDayIcon(String dayIcon) {
        this.dayIcon = Integer.parseInt(dayIcon);
    }

    public String getNightText() {
        return nightText;
    }

    private void setNightText(String nightText) {
        this.nightText = nightText;
    }

    public int getNightIcon() {
        return nightIcon;
    }

    private void setNightIcon(int nightIcon) {
        this.nightIcon = nightIcon;
    }

    public float getMinT() {
        return minT;
    }

    private void setMinT(float minT) {
        this.minT = minT;
    }


    public float getMaxT() {
        return maxT;
    }

    private void setMaxT(float maxT) {
        this.maxT = maxT;
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
            weather.setMinT(temp.getAsJsonObject("Minimum").get("Value").getAsFloat());
            weather.setMaxT(temp.getAsJsonObject("Maximum").get("Value").getAsFloat());

            temp = jsonObject.getAsJsonObject("Day");
            weather.setDayIcon(temp.get("Icon").getAsInt());
            weather.setDayText(temp.get("IconPhrase").getAsString());

            temp = jsonObject.getAsJsonObject("Night");
            weather.setNightIcon(temp.get("Icon").getAsInt());
            weather.setNightText(temp.get("IconPhrase").getAsString());

            return weather;
        }
    }
}
