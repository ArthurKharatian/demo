package com.example.demo.controllers;

import com.example.demo.model.dto.request.UserInfoRequest;
import com.example.demo.model.dto.response.UserInfoResponse;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.demo.constants.Constants.USERS;

@Tag(name = "Пользователи")
@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Создать пользователя")
    public UserInfoResponse createUser(@RequestBody @Valid UserInfoRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по ID")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить пользователя по ID")
    public UserInfoResponse updateUser(@PathVariable Long id, @RequestBody UserInfoRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя по ID")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

//    @GetMapping("/all")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Успех"),
//            @ApiResponse(responseCode = "404", description = "Не найден"),
//            @ApiResponse(responseCode = "401", description = "Не авторизован"),
//    })
//    @Operation(summary = "Получить список пользователей")
//    public List<UserInfoResponse> getAllUsers() {
//        return userService.getAllUsers();
//    }

    @GetMapping("/all")
    public Page<UserInfoResponse> getAllUsers(@RequestParam Integer page,
                                              @RequestParam Integer perPage,
                                              @RequestParam String sort,
                                              @RequestParam Sort.Direction order,
                                              @RequestParam String filter

    ) {
        return new PageImpl<>(null);
    }


}
