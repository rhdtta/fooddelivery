package com.example.demo.controller;

import com.example.demo.dto.DishDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.RestaurantNearbyDTO;
import com.example.demo.dto.RestaurantNearbyResponseDTO;
import com.example.demo.entity.Dish;
import com.example.demo.entity.Restaurant;
import com.example.demo.service.DishService;
import com.example.demo.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final DishService dishService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @PostMapping("/register")
    public ResponseEntity<Restaurant> registerRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant = restaurantService.registerRestaurant(restaurantDTO);
        return ResponseEntity.ok(savedRestaurant);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<RestaurantNearbyResponseDTO>> getNearbyRestaurants(@RequestBody RestaurantNearbyDTO restaurantNearbyDTO) {
        List<RestaurantNearbyResponseDTO> nearbyRestaurants = restaurantService.findNearbyRestaurants(restaurantNearbyDTO);
        return ResponseEntity.ok(nearbyRestaurants);
    }

    @PostMapping("/add-dish")
    public ResponseEntity<Dish> registerRestaurant(@RequestBody DishDTO dishDTO) {
        Dish dish = dishService.addDish(dishDTO);
        return ResponseEntity.ok(dish);
    }

    @GetMapping("/{restaurantId}/dishes")
    public List<Dish> getDishesByRestaurant(@PathVariable Long restaurantId) {
        return dishService.getDishesByRestaurant(restaurantId);
    }
}
