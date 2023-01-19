package com.example.demo.model.dto;

import com.example.demo.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    Integer age;
    String firstName;
    String lastName;
    Gender gender;

    List<HouseDTO> houses;

}
