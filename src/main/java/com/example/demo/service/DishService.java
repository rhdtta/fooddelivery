package com.example.demo.service;

import com.example.demo.dto.DishDTO;
import com.example.demo.entity.Dish;

import java.util.List;

public interface DishService {
    Dish addDish(DishDTO dishDTO);
    List<Dish> getDishesByRestaurant(Long restaurantId);
}
