package io.github.harshanabandara.dashboard.service;

import io.github.harshanabandara.dashboard.dto.LoginRequest;

public interface CredentialService {

    public boolean validateCredential(LoginRequest loginRequest);
}
