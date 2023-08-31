package com.weather.DataService.exception;

public class LocationDataFetchException extends RuntimeException{

    public LocationDataFetchException(String message) {
        super(message);
    }
}
