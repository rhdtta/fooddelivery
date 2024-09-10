package com.example.demo.service;

import com.example.demo.dto.DishDTO;
import com.example.demo.entity.Dish;
import com.example.demo.entity.Restaurant;
import com.example.demo.repository.DishRepository;
import com.example.demo.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService{
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private static final Logger logger = LoggerFactory.getLogger(DishServiceImpl.class);

    @Autowired
    public DishServiceImpl(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    public Dish addDish(DishDTO dishDTO) {
        Restaurant restaurant = restaurantRepository.findById(dishDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        Dish dish = new Dish(dishDTO.getName(), dishDTO.getPrice(), restaurant);
        Dish result =  dishRepository.save(dish);
        logger.info("hereyougo {}", result.getName());

        return result;
    }

    public List<Dish> getDishesByRestaurant(Long restaurantId) {
        return dishRepository.findAll();
    }
}
