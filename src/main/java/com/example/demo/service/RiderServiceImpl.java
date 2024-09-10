package com.example.demo.service;

import com.example.demo.dto.RiderDTO;
import com.example.demo.dto.RiderLocationUpdateDTO;
import com.example.demo.dto.RiderStatusUpdateDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Rider;

import com.example.demo.entity.Users;
import com.example.demo.enums.RiderStatus;
import com.example.demo.helper.Helper;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.RiderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RiderServiceImpl implements RiderService {
    private final double maximumDistance = 50;
    private final RiderRepository riderRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final Helper helper;
    private static final Logger logger = LoggerFactory.getLogger(RiderServiceImpl.class);
//    private final

    @Autowired
    public RiderServiceImpl(RiderRepository riderRepository, Helper helper, RestaurantRepository restaurantRepository, OrderRepository orderRepository) {
        this.riderRepository = riderRepository;
        this.helper = helper;
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Rider saveRider(RiderDTO riderDTO) {
        List<Rider> riders = riderRepository.findByEmail(riderDTO.getEmail());
        if (!riders.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "email already in use"
            );
        }

        if (!StringUtils.hasLength(riderDTO.getEmail()) || !StringUtils.hasLength(riderDTO.getPhoneNo())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "please provide email and phone"
            );
        }

        Rider rider  = new Rider();
        rider.setEmail(riderDTO.getEmail());
        rider.setPhoneNo(riderDTO.getPhoneNo());
        rider.setStatus(RiderStatus.AVAILABLE);
        return riderRepository.save(rider);
    }

    @Transactional
    public void updateRiderLocation(RiderLocationUpdateDTO riderLocationUpdateDTO) {
        Optional<Rider> riders = riderRepository.findById(riderLocationUpdateDTO.getRiderId());
        if (riders.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "no rider found"
            );
        }

        riderRepository.updateRiderLocation(
                riderLocationUpdateDTO.getRiderId(),
                riderLocationUpdateDTO.getLatitude(),
                riderLocationUpdateDTO.getLongitude()
        );
    }

    @Transactional
    public void updateRiderStatus(RiderStatusUpdateDTO riderStatusUpdateDTO) {
        Optional<Rider> riders = riderRepository.findById(riderStatusUpdateDTO.getRiderId());
        if (riders.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "no rider found"
            );
        }

        riderRepository.updateRiderStatus(
                riderStatusUpdateDTO.getRiderId(),
                riderStatusUpdateDTO.getStatus().name()
        );
    }

    public void assingRider(Order order) {
        Restaurant restaurant = restaurantRepository.findById(order.getRestaurant().getId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        List<Rider> riders = riderRepository.findAll();

        Rider assignedRider = null;
        double min_distance = Double.MAX_VALUE;;

        if(!riders.isEmpty()) {
            for(Rider rider: riders) {
                if(rider.getStatus() == RiderStatus.AVAILABLE) {
                    Double riderLatitude = rider.getLatitude();
                    Double riderLongitude = rider.getLongitude();
                    Double restaurantLatitude = restaurant.getLatitude();
                    Double restaurantLongitude = restaurant.getLongitude();

                    if (riderLatitude != null && riderLongitude != null) {
                        double distance = helper.calculateDistance(restaurantLatitude, restaurantLongitude, riderLatitude, riderLongitude);
//                        logger.info("Rider {} and their distance {}", rider.getRiderId(), distance);
                        if(distance <=  maximumDistance && distance < min_distance) {
                            min_distance = distance;
                            assignedRider = rider;
                        }
                    }
                }

            }
        }

        if(assignedRider == null) {
            throw new RuntimeException("Rider not found");
        }

        // else persist data into order table
        order.setRider(assignedRider);
        orderRepository.save(order);
        assignedRider.setStatus(RiderStatus.ON_TRIP);
        riderRepository.save(assignedRider);
        logger.info("Rider assigned {}", assignedRider.getRiderId());
    }
}
