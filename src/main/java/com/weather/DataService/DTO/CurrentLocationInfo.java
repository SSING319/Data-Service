package com.weather.DataService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentLocationInfo {
    private String ip;
    private double latitude;
    private double longitude;
    private String city;
    private String region_name;
    private String country_name;
}
