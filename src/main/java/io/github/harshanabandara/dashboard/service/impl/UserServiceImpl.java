package io.github.harshanabandara.dashboard.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.harshanabandara.dashboard.model.User;
import io.github.harshanabandara.dashboard.repository.UserRepository;
import io.github.harshanabandara.dashboard.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(User user) {
        return findById(user.getUserId());
    }

    @Override
    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @Override
    public User updateById(User user, Long userId) {
        return userRepository.findById(userId).map(user_ -> {
            user_.setFirstName(user.getFirstName());
            user_.setLastName(user.getLastName());
            return userRepository.save(user_);
        }).orElse(null);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
