package com.example.demo.repository;

import com.example.demo.entity.Rider;
import com.example.demo.entity.Users;
import com.example.demo.enums.RiderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RiderRepository extends JpaRepository<Rider, Long> {
    @Modifying
    @Query(value = "UPDATE riders SET latitude = :latitude, longitude = :longitude WHERE rider_id = :riderId", nativeQuery = true)
    void updateRiderLocation(
            @Param("riderId") Long riderId,
            @Param("latitude") double latitude,
            @Param("longitude") double longitude
    );

    @Modifying
    @Query(value = "UPDATE riders SET status = :status WHERE rider_id = :riderId", nativeQuery = true)
    void updateRiderStatus(
            @Param("riderId") Long riderId,
            @Param("status") String status
    );

    @Query(value = "SELECT * FROM riders WHERE email = :email", nativeQuery = true)
    List<Rider> findByEmail(@Param("email") String email);

}
