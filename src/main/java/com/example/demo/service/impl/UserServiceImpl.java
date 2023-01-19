package com.example.demo.service.impl;

import com.example.demo.model.dto.HouseDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.House;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

//        User user = new User();
//        user.setAge(userDTO.getAge());
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        user.setGender(userDTO.getGender());

        User user = mapper.convertValue(userDTO, User.class);

        user.setCreatedAt(LocalDateTime.now());

        List<House> houses = userDTO.getHouses()
                .stream()
                .map(h -> {
                    House house = new House();
                    house.setMaterial(h.getMaterial());
                    house.setFloorsCount(h.getFloorsCount());
                    try {
                        house.setBuiltDate(LocalDate.parse(h.getBuiltDate()));
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e);
                    }
                    return house;
                })
                .collect(Collectors.toList());

        user.setHouses(houses);

        User entity = userRepository.save(user);


        UserDTO result = mapper.convertValue(entity, UserDTO.class);
        List<HouseDTO> housesDTO = entity.getHouses().stream()
                .map(h -> {

                    HouseDTO houseDTO = new HouseDTO();
                    houseDTO.setMaterial(h.getMaterial());
                    houseDTO.setFloorsCount(h.getFloorsCount());
                    houseDTO.setBuiltDate(String.valueOf(h.getBuiltDate()));
                    return houseDTO;
                })
                .collect(Collectors.toList());

        result.setHouses(housesDTO);

        return result;
    }
}
