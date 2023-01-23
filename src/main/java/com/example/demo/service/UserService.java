package com.example.demo.service;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO get(String email);

    void delete(String email);

    User getUser(String email);
}
