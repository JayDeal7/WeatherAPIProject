package com.example.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    @Test
    void shouldReturnCode200() throws IOException {

        var weather = new Weather();
        assertEquals(200,weather.getConnection("Toronto","44ebcda2c5fbe5840a6d1303d00bd0ab"));
    }

    @Test
    void weatherShouldReturnString() throws ParseException {

        String weatherdata = "[{\"coord\":{\"lon\":-79.4163,\"lat\":43.7001},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":276.11,\"feels_like\":269.81,\"temp_min\":274.92,\"temp_max\":277.08,\"pressure\":1008,\"humidity\":89},\"visibility\":10000,\"wind\":{\"speed\":10.8,\"deg\":60},\"clouds\":{\"all\":100},\"dt\":1672775521,\"sys\":{\"type\":2,\"id\":2043365,\"country\":\"CA\",\"sunrise\":1672750282,\"sunset\":1672782746},\"timezone\":-18000,\"id\":6167865,\"name\":\"Toronto\",\"cod\":200}]\n";

        JSONParser parser = new JSONParser();
        JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(weatherdata));
        JSONObject data = (JSONObject) dataObject.get(0);

        var weather = new Weather();
        assertNotNull(weather.retrieveTemp(data));
        assertTrue(!weather.retrieveTemp(data).isEmpty());
    }

    @Test
    void descriptionShouldReturnString() throws ParseException {

        String weatherdata = "[{\"coord\":{\"lon\":-79.4163,\"lat\":43.7001},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":276.11,\"feels_like\":269.81,\"temp_min\":274.92,\"temp_max\":277.08,\"pressure\":1008,\"humidity\":89},\"visibility\":10000,\"wind\":{\"speed\":10.8,\"deg\":60},\"clouds\":{\"all\":100},\"dt\":1672775521,\"sys\":{\"type\":2,\"id\":2043365,\"country\":\"CA\",\"sunrise\":1672750282,\"sunset\":1672782746},\"timezone\":-18000,\"id\":6167865,\"name\":\"Toronto\",\"cod\":200}]\n";

        JSONParser parser = new JSONParser();
        JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(weatherdata));
        JSONObject data = (JSONObject) dataObject.get(0);

        var weather = new Weather();
        assertNotNull(weather.retrieveDescription(data));
        assertTrue(!weather.retrieveDescription(data).isEmpty());
    }

    @Test
    void humidityShouldReturnString() throws ParseException {

        String weatherdata = "[{\"coord\":{\"lon\":-79.4163,\"lat\":43.7001},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":276.11,\"feels_like\":269.81,\"temp_min\":274.92,\"temp_max\":277.08,\"pressure\":1008,\"humidity\":89},\"visibility\":10000,\"wind\":{\"speed\":10.8,\"deg\":60},\"clouds\":{\"all\":100},\"dt\":1672775521,\"sys\":{\"type\":2,\"id\":2043365,\"country\":\"CA\",\"sunrise\":1672750282,\"sunset\":1672782746},\"timezone\":-18000,\"id\":6167865,\"name\":\"Toronto\",\"cod\":200}]\n";

        JSONParser parser = new JSONParser();
        JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(weatherdata));
        JSONObject data = (JSONObject) dataObject.get(0);

        var weather = new Weather();
        assertNotNull(weather.retrieveHumidity(data));
        assertTrue(!weather.retrieveHumidity(data).isEmpty());
    }

}