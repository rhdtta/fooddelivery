package com.example.demo.repository;

import com.example.demo.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Modifying
    @Query(value = "INSERT INTO restaurants (name, phone_no, address, status, restaurant_type, opening_time, closing_time, location) VALUES (:name, :phoneNo, :address, :status, :restaurantType, :openingTime, :closingTime, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) RETURNING restaurant_id, name, phone_no, address, status, restaurant_type, opening_time, closing_time, location", nativeQuery = true)
    List<Object[]> registerRestaurant(
            @Param("name") String name,
            @Param("phoneNo") String phoneNo,
            @Param("address") String address,
            @Param("status") Integer status,
            @Param("restaurantType") Integer restaurantType,
            @Param("openingTime") LocalTime openingTime,
            @Param("closingTime") LocalTime closingTime,
            @Param("latitude") double latitude,
            @Param("longitude") double longitude
    );

    @Query(value = "SELECT restaurant_id, name, phone_no, address FROM restaurants WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :distance)", nativeQuery = true)
    List<Object[]> findNearbyRestaurants(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distance);

    @Query(value = "SELECT * FROM restaurants WHERE email = :email", nativeQuery = true)
    List<Restaurant> findByEmail(@Param("email") String email);



}
