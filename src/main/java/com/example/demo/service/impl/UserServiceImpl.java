package com.example.demo.service.impl;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.UserStatus;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userRepository.findByEmail(userDTO.getEmail()).ifPresent(
                h -> {
                    throw new CustomException("Пользователь с таким email уже существует", HttpStatus.BAD_REQUEST);
                }
        );

        User house = mapper.convertValue(userDTO, User.class);
        User save = userRepository.save(house);

        return mapper.convertValue(save, UserDTO.class);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
//        User user = getHouse(userDTO.getName());

//        user.setMaterial(userDTO.getMaterial() == null ? user.getMaterial() : userDTO.getMaterial());
//        user.setFloorsCount(userDTO.getFloorsCount() == null ? user.getFloorsCount() : userDTO.getFloorsCount());
////        user.setBuiltDate(StringUtils.isBlank(userDTO.getBuiltDate()) ? user.getBuiltDate() : userDTO.getBuiltDate());
//        user.setUpdatedAt(LocalDateTime.now());
//        user.setStatus(UserStatus.UPDATED);

//        return mapper.convertValue(userRepository.save(user), UserDTO.class);
        return null;

    }

    @Override
    public UserDTO get(String email) {
        return mapper.convertValue(getUser(email), UserDTO.class);
    }

    @Override
    public void delete(String email) {
        User user = getUser(email);
        user.setStatus(UserStatus.DELETED);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("Пользователь с таким email не найден", HttpStatus.NOT_FOUND));
    }
}
