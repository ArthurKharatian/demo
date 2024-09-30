package com.example.demo.model.dto.request;

import com.example.demo.model.enums.Color;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarInfoRequest {
    String brand;
    String model;
    Color color;
    Integer year;
    BigDecimal price;
    Boolean isNew;
}
