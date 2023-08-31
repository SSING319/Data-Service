package com.weather.DataService.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherDTO {
    private String city;
    private String state;
    private double temp;
    private double humidity;
}
