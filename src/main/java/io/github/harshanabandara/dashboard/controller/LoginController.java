package io.github.harshanabandara.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.harshanabandara.dashboard.dto.LoginRequest;
import io.github.harshanabandara.dashboard.dto.LoginResponse;
import io.github.harshanabandara.dashboard.model.Credential;
import io.github.harshanabandara.dashboard.service.CredentialService;
import io.github.harshanabandara.dashboard.util.JwtTokenUtil;

@RestController
public class LoginController {
    private final CredentialService credentialService;

    @Autowired
    public LoginController(final CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        Credential credential = credentialService.validateCredential(loginRequest);
        if (credential != null) {
            String token = JwtTokenUtil.createToken(credential);
            LoginResponse loginResponse = new LoginResponse(token);
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
