package com.example.demo.service.impl;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.dto.HouseDTORequest;
import com.example.demo.model.dto.HouseDTOResponse;
import com.example.demo.model.entity.House;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.HouseRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HouseServiceImplTest {

    @InjectMocks
    private HouseServiceImpl houseService;

//    @Mock
//    private UserService userService;

    @Mock
    private HouseRepository houseRepository;

    @Spy
    private ObjectMapper mapper;

    @Test
    public void create() {
        HouseDTORequest request = new HouseDTORequest();
        request.setName("Tower");

        when(houseRepository.save(any(House.class))).thenReturn(any(House.class));

        HouseDTORequest result = houseService.create(request);
        assertEquals(request.getName() , result.getName());
    }

    @Test(expected = CustomException.class)
    public void create_exception() {
        HouseDTORequest request = new HouseDTORequest();
        request.setName("Tower");

        when(houseRepository.findByName(anyString())).thenThrow(CustomException.class);
        houseService.create(request);

    }

    @Test
    public void update() {



    }

    @Test
    public void get() {
    }

    @Test
    public void delete() {
        House house = new House();
        house.setName("Tower");
        when(houseRepository.findByName(anyString())).thenReturn(Optional.of(house));

        when(houseRepository.save(any(House.class))).thenReturn(any(House.class));

//        verify(houseRepository, times(1)).save(house);

        houseService.delete("Tower");

    }

    @Test
    public void addToUser() {
        UserService userService = mock(UserService.class);

        User user = new User();
        user.setEmail("test@test.com");
        user.setAge(25);

        when(userService.getUser(anyString())).thenReturn(user);
        HouseDTOResponse building = houseService.addToUser("Building", "test@test.com");
        building.getUserDTO().getEmail();
    }

    @Test
    public void getAllHouses() {
    }


    @Test
    public void multiply() {

        Long multiply = houseService.multiply(Integer.MAX_VALUE, Integer.MAX_VALUE);
        System.out.println(multiply);


    }
}