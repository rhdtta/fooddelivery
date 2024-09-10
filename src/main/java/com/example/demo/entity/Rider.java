package com.example.demo.entity;

import com.example.demo.enums.RiderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Setter
@Getter
@Entity
@Table(name = "riders", indexes = {
        @Index(name = "idx_rider_email", columnList = "email")
})
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riderId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiderStatus status = RiderStatus.AVAILABLE;

    private double latitude;
    private double longitude;

    public Rider(String email, String phoneNo, RiderStatus status, double latitude, double longitude) {
        this.email = email;
        this.phoneNo = phoneNo;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Rider() {
    }

    // Getters and Setters


}

