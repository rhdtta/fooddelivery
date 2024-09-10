package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(OrderDTO orderDTO);
    Order getOrderById(Long orderId);
    List<Order> getAllOrdersByUserId(Long userId);
    List<Order> getAllOrdersByRiderId(Long riderId);

}
