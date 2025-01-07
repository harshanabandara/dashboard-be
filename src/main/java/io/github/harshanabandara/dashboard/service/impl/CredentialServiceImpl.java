package io.github.harshanabandara.dashboard.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.harshanabandara.dashboard.dto.LoginRequest;
import io.github.harshanabandara.dashboard.model.Credential;
import io.github.harshanabandara.dashboard.repository.CredentialRepository;
import io.github.harshanabandara.dashboard.service.CredentialService;
import io.github.harshanabandara.dashboard.util.PasswordUtil;

@Service
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialServiceImpl(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Credential create(Credential credential) {
        return this.credentialRepository.save(credential);
    }

    @Override
    public Credential validateCredential(LoginRequest loginRequest) {
        Optional<Credential> loginCredential = this.credentialRepository.findById(loginRequest.getUsername());
        if (loginCredential.isEmpty()) {
            return null;
        }
        String encodedPassword = loginCredential.get().getPassword();
        if (PasswordUtil.verifyPassword(loginRequest.getPassword(), encodedPassword)) {
            return loginCredential.get();
        }
        return null;
    }

    @Override
    public Credential loadCredentialByusername(String username) {
        return this.credentialRepository.findById(username).get();
    }

}
