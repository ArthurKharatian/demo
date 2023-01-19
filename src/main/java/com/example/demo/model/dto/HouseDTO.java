package com.example.demo.model.dto;

import com.example.demo.model.enums.MaterialType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseDTO {

    MaterialType material;
    String builtDate;
    Integer floorsCount;

}
