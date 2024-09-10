package com.example.demo.dto;

import com.example.demo.enums.RestaurantStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class RestaurantDTO {
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private RestaurantStatus status;
    private double latitude;
    private double longitude;

    // Getters and Setters
}
