package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.*;
import com.example.demo.enums.OrderStatus;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final RiderRepository riderRepository;
    private final RiderService riderService;

    @Autowired
    public OrderServiceImpl(RestaurantRepository restaurantRepository, UserRepository userRepository, DishRepository dishRepository, OrderRepository orderRepository, RiderService riderService, RiderRepository riderRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.riderService = riderService;
        this.riderRepository = riderRepository;
    }

    public Order placeOrder(OrderDTO orderDTO) {
        Restaurant restaurant = restaurantRepository.findById(orderDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        Users user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Dish> dishes = dishRepository.findAllById(orderDTO.getDishIds());

        if (dishes.isEmpty()) {
            throw new RuntimeException("No dishes found  for the proivded dish IDs");
        }

        for(Dish dish: dishes) {
            if(!Objects.equals(dish.getRestaurant().getId(), restaurant.getId())) {
                throw new RuntimeException("Dishes need to be from  same restaurant");
            }
        }

        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setDishes(dishes);
        order.setOrderStatus(OrderStatus.PREPARING);

        // need to check if order is placed succesfully given that items may go out of stock
        Order dummy =  orderRepository.save(order);

        // calling this function asynchronously
        riderService.assingRider(order);

        return dummy;

    }

    public List<Order> getAllOrdersByUserId(Long userId) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findAllByUser(user);
    }

    public List<Order> getAllOrdersByRiderId(Long riderId) {
        Rider rider = riderRepository.findById(riderId)
            .orElseThrow(() -> new RuntimeException("Rider not found"));

        return orderRepository.findAllByRider(rider);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
