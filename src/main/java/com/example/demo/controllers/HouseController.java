package com.example.demo.controllers;

import com.example.demo.model.dto.HouseDTORequest;
import com.example.demo.model.dto.HouseDTOResponse;
import com.example.demo.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/houses")
@RequiredArgsConstructor
@Tag(name = "Дома")
public class HouseController {

    private final HouseService houseService;

    @PostMapping
    @Operation(summary = "Создание дома")
    public ResponseEntity<HouseDTORequest> createHouse(@RequestBody HouseDTORequest houseDTORequest) {
        return ResponseEntity.ok(houseService.create(houseDTORequest));
    }

    @PutMapping
    public ResponseEntity<HouseDTORequest> updateHouse(@RequestBody HouseDTORequest houseDTORequest) {
        return ResponseEntity.ok(houseService.update(houseDTORequest));
    }


    @GetMapping
    public ResponseEntity<HouseDTORequest> getHouse(@RequestParam String name) {
        return ResponseEntity.ok(houseService.get(name));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteHouse(@RequestParam String name) {

        houseService.delete(name);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/houseToLandlord")
    public ResponseEntity<HouseDTOResponse> addToUser(@RequestParam String name, @RequestParam String email) {
        return ResponseEntity.ok(houseService.addToUser(name, email));
    }


    @GetMapping("/all")
    public ModelMap getAllHouses(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "10") Integer perPage,
                                 @RequestParam(required = false, defaultValue = "name") String sort,
                                 @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order) {
        return houseService.getAllHouses(page, perPage, sort, order);
    }


}
