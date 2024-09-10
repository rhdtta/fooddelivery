package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
public class RestaurantNearbyResponseDTO {
    private Long restaurantId;
    private String restaurantName;
    private String address;
    private LocalTime deliveryTime;

    public RestaurantNearbyResponseDTO(Long restaurantId, String restaurantName, String address, LocalTime deliveryTime) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.address = address;
        this.deliveryTime = deliveryTime;
    }
}
