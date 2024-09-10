package com.example.demo.repository;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Modifying
    @Query(value = "INSERT INTO users (email, address, latitude, longitude, location) VALUES (:email, :address, :latitude, :longitude, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) RETURNING *", nativeQuery = true)
    List<Users> saveUserWithCoordinates(
            @Param("email") String email,
            @Param("address") String address,
            @Param("latitude") double latitude,
            @Param("longitude") double longitude
    );


    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    List<Users> findByEmail(@Param("email") String email);

}
