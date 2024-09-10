package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.Rider;
import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(Users user);
    List<Order> findAllByRider(Rider rider);
}
