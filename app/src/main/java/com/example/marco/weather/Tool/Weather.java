package com.example.marco.weather.Tool;

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
}
