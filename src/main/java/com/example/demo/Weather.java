package com.example.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    private String humidity;
    private String description;
    private String temperature;

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void getWeatherData(String City){

        String API_KEY = "44ebcda2c5fbe5840a6d1303d00bd0ab";

        try{


            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+City+"&APPID="+API_KEY);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connection is made
            int responseCode = conn.getResponseCode();

            //If code is 200 we proceed
            if(responseCode !=200){
                throw new RuntimeException("Error has occured");
            } else{

                //Open a stream to feed in API data
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                informationString.append("[");
                while (scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }
                informationString.append("]");
                scanner.close();

                //Convert string to JSON
                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(informationString));

                //Get data
                JSONObject data = (JSONObject) dataObject.get(0);

                //to get temp
                temperature = retrieveTemp(data);
                //System.out.println(t.get("temp"));

                //get temp description
                description = retrieveDescription(data);
                //System.out.println(description);

                //to get humidity
                humidity = retrieveHumidity(data);
                //System.out.println(humidity);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getConnection(String city, String key) throws IOException {
        int responsecode = 0;

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID="+key);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Check if connection is made
        responsecode = conn.getResponseCode();

        return responsecode;
    }

    public String retrieveTemp(JSONObject data){
        String temperature;

        JSONObject t = (JSONObject) data.get("main");
        temperature = t.get("temp").toString();

        return temperature;

    }

    public String retrieveDescription(JSONObject data){
        String description;

        JSONArray desc = (JSONArray) data.get("weather");
        JSONObject d = (JSONObject) desc.get(0);
        description = d.get("description").toString();

        return description;
    }

    public String retrieveHumidity(JSONObject data){
        String humidity;

        JSONObject h = (JSONObject) data.get("main");
        humidity = h.get("humidity").toString();

        return humidity;
    }
}
