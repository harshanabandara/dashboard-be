package io.github.harshanabandara.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.harshanabandara.dashboard.dto.UserRequest;
import io.github.harshanabandara.dashboard.dto.UserResponse;
import io.github.harshanabandara.dashboard.model.User;
import io.github.harshanabandara.dashboard.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest
    // loginRequest) {
    //
    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody final UserRequest userRequest) {
        User user = userRequest.getUserFromRequest();
        User createdUser = userService.create(user);
        UserResponse userResponse = new UserResponse(createdUser);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

}
