package com.example.demo.controllers;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateHouse(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(userDTO));
    }


    @GetMapping
    public ResponseEntity<UserDTO> getHouse(@RequestParam String email) {
        return ResponseEntity.ok(userService.get(email));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteHouse(@RequestParam String email) {

        userService.delete(email);
        return ResponseEntity.ok().build();
    }


}
