package com.example.demo.model.dto;

import com.example.demo.model.enums.MaterialType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HouseDTORequest {

    MaterialType material;
    String builtDate;
    Integer floorsCount;
    String name;

}
