package com.example.demo.service;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.db.entity.User;
import com.example.demo.model.db.repository.UserRepository;
import com.example.demo.model.dto.request.UserInfoRequest;
import com.example.demo.model.dto.response.UserInfoResponse;
import com.example.demo.model.enums.UserStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.constants.Constants.validateKey;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final ObjectMapper mapper;
    private final UserRepository userRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    public UserInfoResponse createUser(String apiKey, UserInfoRequest request) {
        log.info(baseUrl);
        validateKey(apiKey);
        validateEmail(request);

        userRepository.findByEmailIgnoreCase(request.getEmail())
                .ifPresent(user -> {
                    throw new CustomException(String.format("User with email: %s already exists", request.getEmail()), HttpStatus.BAD_REQUEST);
                });


        User user = mapper.convertValue(request, User.class);
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(UserStatus.CREATED);

        User save = userRepository.save(user);

        return mapper.convertValue(save, UserInfoResponse.class);
    }

    private void validateEmail(UserInfoRequest request) {
        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            throw new CustomException("Invalid email format", HttpStatus.BAD_REQUEST);
        }
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }

    public UserInfoResponse getUser(Long id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        User user;
//        if (optionalUser.isPresent()) {
//            user = optionalUser.get();
//        } else {
//            user = null;
//        }
//
//        if (optionalUser.isEmpty()) {
//            user = null;
//        } else {
//            user = optionalUser.get();
//        }
        User user = getUserFromDB(id);
        return mapper.convertValue(user, UserInfoResponse.class);
    }

    public User getUserFromDB(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }

    public UserInfoResponse updateUser(Long id, UserInfoRequest request) {
        validateEmail(request);

        User user = getUserFromDB(id);

        user.setEmail(request.getEmail());
        user.setGender(request.getGender() == null ? user.getGender() : request.getGender());
        user.setAge(request.getAge() == null ? user.getAge() : request.getAge());
        user.setFirstName(request.getFirstName() == null ? user.getFirstName() : request.getFirstName());
        user.setLastName(request.getLastName() == null ? user.getLastName() : request.getLastName());
        user.setMiddleName(request.getMiddleName() == null ? user.getMiddleName() : request.getMiddleName());
        user.setPassword(request.getPassword() == null ? user.getPassword() : request.getPassword());

        user.setUpdatedAt(LocalDateTime.now());
        user.setStatus(UserStatus.UPDATED);

        User save = userRepository.save(user);

        return mapper.convertValue(save, UserInfoResponse.class);
    }

    public void deleteUser(Long id) {
//        userRepository.deleteById(id); физическое удаление
        User user = getUserFromDB(id);
        user.setUpdatedAt(LocalDateTime.now());
        user.setStatus(UserStatus.DELETED);
        userRepository.save(user);
    }

    public List<UserInfoResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.convertValue(user, UserInfoResponse.class))
                .collect(Collectors.toList());
    }

    public User updateUserData(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUser(email);
        log.info(String.format("User [EMAIL: %s] found in db", email));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), authorities);
    }
}
