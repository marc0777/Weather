package com.example.marco.weather.Tool;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataRetriever extends AsyncTask<String, Void, String> {

    private static final String API_KEY = "KPG9Olkd338PiKKvGTcURXdjc6sRPIfr";
    private static final String API_URL = "http://dataservice.accuweather.com/";

    protected void onPreExecute() {
    }

    protected String doInBackground(String... params) {

        try {
            URL url = new URL(API_URL + params[0] + "?apikey=" + API_KEY  + "&" + params[1]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
    }
}