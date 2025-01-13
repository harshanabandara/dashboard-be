package io.github.harshanabandara.dashboard.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.harshanabandara.dashboard.model.User;

public interface UserService {
    User create(User user);

    User findById(User user);

    User findById(Long userId);

    User updateById(User user, Long userId);

    Page<User> getAll(Pageable pageable);

    boolean deleteById(Long userId);

    long count();

    List<User> getAll();

    User getLastCreated();

}