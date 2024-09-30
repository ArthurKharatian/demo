package com.example.demo.service;

import com.example.demo.model.db.entity.Car;
import com.example.demo.model.db.entity.User;
import com.example.demo.model.db.repository.CarRepository;
import com.example.demo.model.dto.request.CarToUserRequest;
import com.example.demo.model.dto.response.CarInfoResponse;
import com.example.demo.model.enums.CarStatus;
import com.example.demo.model.enums.UserStatus;
import com.example.demo.utils.PaginationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
    @InjectMocks
    private CarService carService;

    @Mock
    private UserService userService;

    @Mock
    private CarRepository carRepository;

    @Spy
    private ObjectMapper mapper;

    @Test
    public void addCarToUser() {

        Car car = new Car();
        car.setId(1L);

        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));

        User user = new User();
        user.setId(1L);
        user.setCars(new ArrayList<>());


        when(userService.getUserFromDB(user.getId())).thenReturn(user);
        when(userService.updateUserData(any(User.class))).thenReturn(user);


        CarToUserRequest request = CarToUserRequest.builder()
                .carId(car.getId())
                .userId(user.getId())
                .build();

        carService.addCarToUser(request);

        verify(carRepository, times(1)).save(any(Car.class));
        assertEquals(user.getId(), car.getUser().getId());
    }

    @Test
    public void getAllCars() {

        Pageable pageRequest = PaginationUtil.getPageRequest(1, 10, "brand", Sort.Direction.DESC);

        List<Car> cars = new ArrayList<>();

        cars.add(new Car());
        cars.add(new Car());

        PageImpl<Car> page = new PageImpl<>(cars, pageRequest, cars.size());

        when(carRepository.findAllByStatusNotFiltered(pageRequest, CarStatus.DELETED, "bm".toUpperCase())).thenReturn(page);

        Page<CarInfoResponse> allCars = carService.getAllCars(1, 10, "brand", Sort.Direction.DESC, "bm");

        assertEquals(cars.size(), allCars.getTotalElements());

        CarInfoResponse carInfoResponse = allCars.getContent().get(0);

        assertNotNull(carInfoResponse);
    }
}