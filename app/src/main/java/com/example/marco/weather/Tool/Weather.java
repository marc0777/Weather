package com.example.marco.weather.Tool;

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

    public void setDayIcon(int dayIcon) {
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

    public void setNightIcon(int nightIcon) {
        this.nightIcon = nightIcon;
    }

    private void setNightIcon(String nightIcon) {
        this.dayIcon = Integer.parseInt(nightIcon);
    }

    public float getMinT() {
        return minT;
    }

    public void setMinT(float minT) {
        this.minT = minT;
    }

    private void setMinT(String minT) {
        this.minT = Float.parseFloat(minT);
    }


    public float getMaxT() {
        return maxT;
    }

    public void setMaxT(float maxT) {
        this.maxT = maxT;
    }

    private void setMaxT(String maxT) {
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
