package com.example.demo.service;

import com.example.demo.dto.DishDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.RestaurantNearbyDTO;
import com.example.demo.dto.RestaurantNearbyResponseDTO;
import com.example.demo.entity.Dish;
import com.example.demo.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant registerRestaurant(RestaurantDTO restaurantDTO);
    List<RestaurantNearbyResponseDTO> findNearbyRestaurants(RestaurantNearbyDTO restaurantNearby);
}
