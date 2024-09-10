package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishDTO {
    private Long restaurantId;
    private String name;
    private double price;
}
