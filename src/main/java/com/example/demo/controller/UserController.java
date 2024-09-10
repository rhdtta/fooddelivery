package com.example.demo.controller;

import com.example.demo.dto.UsersDTO;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody UsersDTO usersDTO) {
        Users savedUsers = userService.saveUser(usersDTO);
        return ResponseEntity.ok(savedUsers);
    }

}
