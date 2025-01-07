package io.github.harshanabandara.dashboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.harshanabandara.dashboard.dto.LoginRequest;
import io.github.harshanabandara.dashboard.repository.CredentialRepository;
import io.github.harshanabandara.dashboard.service.CredentialService;

@Service
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialServiceImpl(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public boolean validateCredential(LoginRequest loginRequest) {
        // TODO implement this.
        return true;
    }

}
