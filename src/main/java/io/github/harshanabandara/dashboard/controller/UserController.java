package io.github.harshanabandara.dashboard.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.harshanabandara.dashboard.dto.LoginResponse;
import io.github.harshanabandara.dashboard.dto.UserRequest;
import io.github.harshanabandara.dashboard.dto.UserResponse;
import io.github.harshanabandara.dashboard.model.User;
import io.github.harshanabandara.dashboard.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin("*")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    @ApiResponse(responseCode = "200", description = "Login attempt successful.jwt created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))
    public ResponseEntity<UserResponse> createUser(@RequestBody final UserRequest userRequest) {
        User user = userRequest.getUserFromRequest();
        User createdUser = userService.create(user);
        UserResponse userResponse = new UserResponse(createdUser);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/api/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody final UserRequest userRequest,
            @PathVariable Long userId) {
        User user = userService.updateById(userRequest.getUserFromRequest(), userId);
        if (user != null) {
            return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserResponse> retrieveUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user != null) {
            return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/users")
    public ResponseEntity<Page<UserResponse>> retrieveUsers(@ParameterObject Pageable pageable) {
        Page<User> userPage = userService.getAll(pageable);
        Page<UserResponse> userResponsePage = userPage.map(user -> new UserResponse(user));
        return new ResponseEntity<Page<UserResponse>>(userResponsePage, HttpStatus.FOUND);
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        if (userService.deleteById(userId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
