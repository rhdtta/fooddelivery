package com.example.demo.entity;

import jakarta.persistence.*;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

@Setter
@Getter
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_email", columnList = "email")
})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String email;
    private String address;

    public Users(String email, String address) {
        this.email = email;
        this.address = address;
    }

    public Users() {
    }
}
