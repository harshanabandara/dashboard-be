package io.github.harshanabandara.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.harshanabandara.dashboard.dto.LoginRequest;
import io.github.harshanabandara.dashboard.dto.LoginResponse;
import io.github.harshanabandara.dashboard.service.CredentialService;
import io.github.harshanabandara.dashboard.service.UserService;

@RestController
public class LoginController {
    private String userToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoidXNlciIsInJvbGUiOiJ1c2VyIn0.P-Tlw6Q_gT6NSROFoYFdN5C7NpVirFjXqBDETOF0YKs";
    private String adminToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiaGF1bG1hdGljIiwicm9sZSI6ImFkbWluIn0.an3Ij7HK01wzS1uqT-oMiD4MjK0LUQLHlUCvZmztNgU";

    private final CredentialService credentialService;

    @Autowired
    public LoginController(final CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        if (credentialService.validateCredential(loginRequest)) {
            LoginResponse loginResponse = new LoginResponse(userToken);
            return ResponseEntity.ok(loginResponse);
        }
        return null;
    }
}
