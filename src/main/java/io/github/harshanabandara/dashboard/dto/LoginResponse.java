package io.github.harshanabandara.dashboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class LoginResponse {
    private String token;

    public LoginResponse() {
    };

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
