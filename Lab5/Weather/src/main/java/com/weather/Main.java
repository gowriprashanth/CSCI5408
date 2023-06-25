package com.weather;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String pathToFile = "weather.json";

            String readFile = new String(Files.readAllBytes(Paths.get(pathToFile)));
            // Parse JSON response
            JSONObject weatherData = new JSONObject(readFile);

            // Filter data
            JSONArray filterByFeelsLike = filterWeather(weatherData);

            // Save filtered data to a file
            saveToFile(filterByFeelsLike, "summer_weather.json");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray filterWeather(JSONObject weatherData) {
        JSONArray filteredData = new JSONArray();
        JSONArray dailyArray = weatherData.getJSONArray("daily");
        for (int i = 0; i < dailyArray.length(); i++) {
            JSONObject dailyObject = dailyArray.getJSONObject(i);
            JSONObject feelsLikeObject = dailyObject.getJSONObject("feels_like");
            double dayFeelsLike = feelsLikeObject.getDouble("day");
            if (dayFeelsLike > 15) {
                filteredData.put(dailyObject);
            }
        }
        return filteredData;
    }

    private static void saveToFile(JSONArray filteredData, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(filteredData.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}