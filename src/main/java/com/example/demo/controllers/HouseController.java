package com.example.demo.controllers;

import com.example.demo.model.dto.HouseDTORequest;
import com.example.demo.model.dto.HouseDTOResponse;
import com.example.demo.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @PostMapping
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


}
