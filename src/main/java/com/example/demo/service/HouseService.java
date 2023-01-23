package com.example.demo.service;

import com.example.demo.model.dto.HouseDTORequest;
import com.example.demo.model.dto.HouseDTOResponse;

public interface HouseService {

    HouseDTORequest create(HouseDTORequest houseDTORequest);

    HouseDTORequest update(HouseDTORequest houseDTORequest);

    HouseDTORequest get(String name);

    void delete(String name);

    HouseDTOResponse addToUser(String name, String email);
}
