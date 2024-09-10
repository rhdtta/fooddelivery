package com.example.demo.controller;

import com.example.demo.dto.RiderDTO;
import com.example.demo.dto.RiderLocationUpdateDTO;
import com.example.demo.dto.RiderStatusUpdateDTO;
import com.example.demo.entity.Rider;
import com.example.demo.service.RiderService;
import com.example.demo.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rider")
public class RiderController {

    private final RiderService riderService;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Rider> createRider(@RequestBody RiderDTO riderDTO) {
        Rider savedRider = riderService.saveRider(riderDTO);
        return ResponseEntity.ok(savedRider);
    }

    @PutMapping("/updateLocation")
    public ResponseEntity<Void> updateRiderLocation(@RequestBody RiderLocationUpdateDTO riderLocationUpdateDTO) {
        riderService.updateRiderLocation(riderLocationUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Void> updateRiderStatus(@RequestBody RiderStatusUpdateDTO riderStatusUpdateDTO) {
        logger.info("Received RiderStatusUpdateDTO: {}", riderStatusUpdateDTO);
        riderService.updateRiderStatus(riderStatusUpdateDTO);
        return ResponseEntity.ok().build();
    }



}
