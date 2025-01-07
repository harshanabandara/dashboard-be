package io.github.harshanabandara.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.harshanabandara.dashboard.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
