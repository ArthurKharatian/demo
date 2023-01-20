package com.example.demo.model.dto;

import com.example.demo.model.enums.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    Integer age;
    String firstName;
    String lastName;
    Gender gender;

    List<HouseDTO> houses;

}
