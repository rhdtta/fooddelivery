package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDTO orderDTO) {
        Order placedOrder = orderService.placeOrder(orderDTO);
        return ResponseEntity.ok(placedOrder);
    }

    @GetMapping("/user/all-orders/{userId}")
    public ResponseEntity<List<Order>> getAllUserOrders(@PathVariable Long userId) {
        List<Order> allOrders = orderService.getAllOrdersByUserId(userId);
        return ResponseEntity.ok(allOrders);
    }

    @GetMapping("/rider/all-orders/{riderId}")
    public ResponseEntity<List<Order>> getAllRiderOrders(@PathVariable Long riderId) {
        List<Order> allOrders = orderService.getAllOrdersByRiderId(riderId);
        return ResponseEntity.ok(allOrders);
    }

    @GetMapping("/info/{orderId}")
    public ResponseEntity<Order> getOrderInfo(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

}

//
