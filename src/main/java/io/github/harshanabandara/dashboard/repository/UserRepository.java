package io.github.harshanabandara.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.harshanabandara.dashboard.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
