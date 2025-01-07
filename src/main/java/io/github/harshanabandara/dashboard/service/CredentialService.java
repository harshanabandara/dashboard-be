package io.github.harshanabandara.dashboard.service;

import io.github.harshanabandara.dashboard.dto.LoginRequest;
import io.github.harshanabandara.dashboard.model.Credential;

public interface CredentialService {
    public Credential create(Credential credential);

    public boolean validateCredential(LoginRequest loginRequest);
}
