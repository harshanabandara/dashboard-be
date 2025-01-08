package io.github.harshanabandara.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.harshanabandara.dashboard.dto.LoginRequest;
import io.github.harshanabandara.dashboard.dto.LoginResponse;
import io.github.harshanabandara.dashboard.model.Credential;
import io.github.harshanabandara.dashboard.service.CredentialService;
import io.github.harshanabandara.dashboard.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin("*")
@RestController
public class LoginController {
    private final CredentialService credentialService;

    @Autowired
    public LoginController(final CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/api/login")
    @Operation(summary = "User Login", description = "Retrieve a jwt using the username, and password of a user")
    @ApiResponse(responseCode = "200", description = "Login attempt successful.jwt created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)))
    @ApiResponse(responseCode = "401", description = "Login failed", content = @Content)
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        Credential credential = credentialService.validateCredential(loginRequest);
        if (credential != null) {
            String token = JwtTokenUtil.createToken(credential);
            LoginResponse loginResponse = new LoginResponse(token);
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
