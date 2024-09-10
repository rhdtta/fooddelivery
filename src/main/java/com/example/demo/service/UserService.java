package com.example.demo.service;

import com.example.demo.dto.UsersDTO;
import com.example.demo.entity.Users;
import java.util.Optional;

public interface UserService {
    Users saveUser(UsersDTO usersDTO);
}

