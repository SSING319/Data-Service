package com.weather.DataService.controller;

import com.weather.DataService.DTO.WeatherDTO;
import com.weather.DataService.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/current-location-temp")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping
    public WeatherDTO getCurrentLocationTemp() throws Exception {
        return dataService.getCurrentLocationData();
    }
}
