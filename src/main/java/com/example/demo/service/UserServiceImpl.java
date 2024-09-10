package com.example.demo.service;

import com.example.demo.dto.UsersDTO;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Users saveUser(UsersDTO usersDTO) {
        List<Users> users = userRepository.findByEmail(usersDTO.getEmail());
        if (!users.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "email already in use"
            );
        }

        Users user = new Users();
        user.setEmail(usersDTO.getEmail());
        return userRepository.save(user);
    }
}
