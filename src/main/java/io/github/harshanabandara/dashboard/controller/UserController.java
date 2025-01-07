package io.github.harshanabandara.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody final UserRequest userRequest) {
        User user = userRequest.getUserFromRequest();
        User createdUser = userService.create(user);
        UserResponse userResponse = new UserResponse(createdUser);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody final UserRequest userRequest,
            @PathVariable Long userId) {
        User user = userService.updateById(userRequest.getUserFromRequest(), userId);
        if (user != null) {
            return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> retrieveUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user != null) {
            return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> retrieveUsers(Pageable pageable) {
        Page<User> userPage = userService.getAll(pageable);
        Page<UserResponse> userResponsePage = userPage.map(user -> new UserResponse(user));
        return new ResponseEntity<Page<UserResponse>>(userResponsePage, HttpStatus.FOUND);
    }

}
