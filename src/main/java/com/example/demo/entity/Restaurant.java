package com.example.demo.entity;

import com.example.demo.enums.RestaurantStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String phoneNo;
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantStatus status;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes;

    // Getters and Setters

    // Constructor
    public Restaurant(String name, String email, String phoneNo, String address, RestaurantStatus status, double latitude, double longitude) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Restaurant() {
    }

}
