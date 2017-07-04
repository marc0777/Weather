package com.example.marco.weather.Tool;

import java.util.Locale;

public class Utils {
    public static String getLocale() {
        return Locale.getDefault().toString().toLowerCase().replace("_","-");
    }
}
