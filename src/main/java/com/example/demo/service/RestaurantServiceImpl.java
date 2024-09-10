package com.example.demo.service;

import com.example.demo.dto.DishDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.RestaurantNearbyDTO;
import com.example.demo.dto.RestaurantNearbyResponseDTO;
import com.example.demo.entity.Dish;
import com.example.demo.entity.Restaurant;
import com.example.demo.helper.Helper;
import com.example.demo.repository.DishRepository;
import com.example.demo.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    private final RestaurantRepository restaurantRepository;
    private final AccessTokenService accessTokenService;
    private final DistanceMatrixService distanceMatrixService;
    private final Helper helper;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, AccessTokenService accessTokenService, DistanceMatrixService distanceMatrixService, Helper helper) {
        this.restaurantRepository = restaurantRepository;
        this.accessTokenService = accessTokenService;
        this.distanceMatrixService = distanceMatrixService;
        this.helper = helper;
    }

    public Restaurant registerRestaurant(RestaurantDTO restaurantDTO) {
        if(restaurantDTO.getEmail() == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "please provide email"
            );
        }

        List<Restaurant> restaurants = restaurantRepository.findByEmail(restaurantDTO.getEmail());
        if (!restaurants.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "email already in use"
            );
        }

        Restaurant restaurant = new Restaurant(restaurantDTO.getName(), restaurantDTO.getEmail(), restaurantDTO.getPhoneNo(), restaurantDTO.getAddress(), restaurantDTO.getStatus(), restaurantDTO.getLatitude(), restaurantDTO.getLongitude());

        return restaurantRepository.save(restaurant);
    }

    public List<RestaurantNearbyResponseDTO> findNearbyRestaurants(RestaurantNearbyDTO restaurantNearbyDTO) {
        List<Restaurant> allRestaurants =  restaurantRepository.findAll();

        List<Restaurant> nearbyRestaurants = new ArrayList<>();
        if (!allRestaurants.isEmpty()) {
            for (Restaurant restaurant : allRestaurants) {
                double restaurantLatitude = restaurant.getLatitude();
                double restaurantLongitude = restaurant.getLongitude();

                double calculatedDistance = helper.calculateDistance(restaurantNearbyDTO.getLatitude(), restaurantNearbyDTO.getLongitude(), restaurantLatitude, restaurantLongitude);

                if (calculatedDistance <= restaurantNearbyDTO.getDistance()) {
                    // add only if restaurant is available
                    nearbyRestaurants.add(restaurant);
                }

            }

        }

        String originLatLong = restaurantNearbyDTO.getLatitude() + "," + restaurantNearbyDTO.getLongitude();

        String token = accessTokenService.getAccessToken();

        Map<Long, LocalTime> restaurantDeliveryTime = null;

        try {
            restaurantDeliveryTime = distanceMatrixService.getDistanceMatrix(originLatLong, nearbyRestaurants, token);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get distance matrix", e);
        }

        List<RestaurantNearbyResponseDTO> responseList = new ArrayList<>();

        for (Restaurant restaurant : nearbyRestaurants) {
            LocalTime deliveryTime = restaurantDeliveryTime.get(restaurant.getId());

            RestaurantNearbyResponseDTO dto = new RestaurantNearbyResponseDTO(
                    restaurant.getId(),
                    restaurant.getName(),
                    restaurant.getAddress(),
                    deliveryTime
            );
            responseList.add(dto);
        }

        logger.info("here is the response {}", responseList);
        return responseList;
    }

}
