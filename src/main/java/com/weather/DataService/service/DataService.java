package com.weather.DataService.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.DataService.DTO.CurrentLocationInfo;
import com.weather.DataService.DTO.WeatherDTO;
import com.weather.DataService.exception.LocationDataFetchException;
import com.weather.DataService.exception.WeatherDataFetchException;
import com.weather.DataService.util.Helper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class DataService {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Helper helper = new Helper();


    private WeatherDTO getWeatherData(CurrentLocationInfo currentLocationInfo) throws WeatherDataFetchException, IOException {
        String apiKey = System.getenv("API_KEY");
        String baseUrl = "http://api.openweathermap.org/data/2.5/weather";
        String url = baseUrl + "?q=" + currentLocationInfo.getCity() + "&appid=" + apiKey;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                //this reads json string and converts it to json tree which is represented by jsin node class and then
                // we can parse the tree to get the desired field values
                JsonNode jsonNode = objectMapper.readTree(response.body().byteStream());
                double temp = jsonNode.get("main").get("temp").asDouble();
                double humidity = jsonNode.get("main").get("humidity").asDouble();
                return WeatherDTO.builder()
                        .temp(helper.kelvinToCelsius(temp))
                        .humidity(humidity)
                        .city(currentLocationInfo.getCity())
                        .state(currentLocationInfo.getRegion_name())
                        .build();
            } else {
                throw new WeatherDataFetchException("Error fetching weather data");
            }
        }
    }

    public WeatherDTO getCurrentLocationData() throws LocationDataFetchException, IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/current-location")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                ObjectMapper objectMapper = new ObjectMapper();
                CurrentLocationInfo currentLocationInfo = objectMapper.readValue(responseBody.string(), CurrentLocationInfo.class);
                return getWeatherData(currentLocationInfo);
            }
            else {
                throw new LocationDataFetchException("Error Fetching Current Location Data");
            }
        }
    }

}


