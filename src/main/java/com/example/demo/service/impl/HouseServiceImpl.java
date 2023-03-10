package com.example.demo.service.impl;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.dto.HouseDTORequest;
import com.example.demo.model.dto.HouseDTOResponse;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.House;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.HouseStatus;
import com.example.demo.model.repository.HouseRepository;
import com.example.demo.service.HouseService;
import com.example.demo.service.UserService;
import com.example.demo.utils.PaginationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final UserService userService;
    private final HouseRepository houseRepository;
    private final ObjectMapper mapper;

    @Override
    public HouseDTORequest create(HouseDTORequest houseDTORequest) {
        houseRepository.findByName(houseDTORequest.getName()).ifPresent(
                h -> {
                    throw new CustomException("Дом с таким названием уже существует", HttpStatus.BAD_REQUEST);
                }
        );

        House house = mapper.convertValue(houseDTORequest, House.class);
        houseRepository.save(house);

        return mapper.convertValue(house, HouseDTORequest.class);
    }

    @Override
    public HouseDTORequest update(HouseDTORequest houseDTORequest) {
        House house = getHouse(houseDTORequest.getName());

        house.setMaterial(houseDTORequest.getMaterial() == null ? house.getMaterial() : houseDTORequest.getMaterial());
        house.setFloorsCount(houseDTORequest.getFloorsCount() == null ? house.getFloorsCount() : houseDTORequest.getFloorsCount());
        house.setBuiltDate(StringUtils.isBlank(houseDTORequest.getBuiltDate()) ? house.getBuiltDate() : houseDTORequest.getBuiltDate());
        house.setUpdatedAt(LocalDateTime.now());
        house.setStatus(HouseStatus.UPDATED);

        return mapper.convertValue(houseRepository.save(house), HouseDTORequest.class);
    }

    @Override
    public HouseDTORequest get(String name) {
        return mapper.convertValue(getHouse(name), HouseDTORequest.class);
    }

    @Override
    public void delete(String name) {
        House house = getHouse(name);
        house.setStatus(HouseStatus.DELETED);
        house.setUpdatedAt(LocalDateTime.now());
        houseRepository.save(house);
    }

    private House getHouse(String houseDTO) {
        return houseRepository.findByName(houseDTO)
                .orElseThrow(() -> new CustomException("Дом с таким названием не найден", HttpStatus.NOT_FOUND));
    }


    @Override
    public HouseDTOResponse addToUser(String name, String email) {
        User user = userService.getUser(email);
        House house = getHouse(name);
        house.setUser(user);
        House save = houseRepository.save(house);
        HouseDTOResponse response = mapper.convertValue(save, HouseDTOResponse.class);
        response.setUserDTO(mapper.convertValue(user, UserDTO.class));
        return response;
    }

    @Override
    public ModelMap getAllHouses(Integer page, Integer perPage, String sort, Sort.Direction order) {
        Pageable pageRequest = PaginationUtil.getPageRequest(page, perPage, sort, order);
        Page<House> pageResult = houseRepository.findAll(pageRequest);

        List<HouseDTORequest> content = pageResult.getContent().stream()
                .map(h -> mapper.convertValue(h, HouseDTORequest.class))
                .collect(Collectors.toList());

        PagedListHolder<HouseDTORequest> result = new PagedListHolder<>(content);

        result.setPage(page);
        result.setPageSize(perPage);

        ModelMap map = new ModelMap();
        map.addAttribute("content", result.getPageList());
        map.addAttribute("pageNumber", page);
        map.addAttribute("pageSize", result.getPageSize());
        map.addAttribute("totalPages", result.getPageCount());

        return map;
    }

    public Long multiply(int x, int y) {

        return (long)x * y;

    }


}
