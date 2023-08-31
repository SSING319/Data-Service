package com.weather.DataService.exception;

public class WeatherDataFetchException extends RuntimeException {
    public WeatherDataFetchException(String message) {
        super(message);
    }
}