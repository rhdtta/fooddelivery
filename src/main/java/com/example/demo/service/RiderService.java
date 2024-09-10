package com.example.demo.service;

import com.example.demo.dto.RiderDTO;
import com.example.demo.dto.RiderLocationUpdateDTO;
import com.example.demo.dto.RiderStatusUpdateDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Rider;
import org.springframework.scheduling.annotation.Async;

public interface RiderService {
    Rider saveRider(RiderDTO riderDTO);
    void updateRiderLocation(RiderLocationUpdateDTO riderLocationUpdateDTO);
    void updateRiderStatus(RiderStatusUpdateDTO riderStatusUpdateDTO);
    @Async
    void assingRider(Order order);
}
