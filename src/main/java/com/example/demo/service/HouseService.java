package com.example.demo.service;

import com.example.demo.model.dto.HouseDTORequest;
import com.example.demo.model.dto.HouseDTOResponse;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;

public interface HouseService {

    HouseDTORequest create(HouseDTORequest houseDTORequest);

    HouseDTORequest update(HouseDTORequest houseDTORequest);

    HouseDTORequest get(String name);

    void delete(String name);

    HouseDTOResponse addToUser(String name, String email);

    ModelMap getAllHouses(Integer page, Integer perPage, String sort, Sort.Direction order);
}
