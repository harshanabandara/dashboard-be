package io.github.harshanabandara.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.harshanabandara.dashboard.model.Credential;

public interface CredentialRepository extends JpaRepository<Credential, String> {

}
