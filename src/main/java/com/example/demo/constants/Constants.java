package com.example.demo.constants;

import com.example.demo.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public interface Constants {
    String API = "/api";
    String USERS = API + "/users";
    String CARS = API + "/cars";

    String API_KEY = "SmF2YSBEZXZlbG9wZXI=";

    static void validateKey(String apiKey) {
        if (!apiKey.equals(API_KEY)) {
            throw new CustomException("Wrong api key", HttpStatus.FORBIDDEN);
        }
    }
}
