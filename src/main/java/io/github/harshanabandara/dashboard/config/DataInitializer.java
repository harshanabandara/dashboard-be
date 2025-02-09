package io.github.harshanabandara.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.github.harshanabandara.dashboard.model.Credential;
import io.github.harshanabandara.dashboard.model.User;
import io.github.harshanabandara.dashboard.repository.CredentialRepository;
import io.github.harshanabandara.dashboard.repository.UserRepository;
import io.github.harshanabandara.dashboard.util.PasswordUtil;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CredentialRepository credentialRepository;
    private final UserRepository userRepository;

    @Autowired
    public DataInitializer(CredentialRepository credentialRepository, UserRepository userRepository) {
        this.credentialRepository = credentialRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setFirstName("Harshana");
            admin.setLastName("Bandara");
            userRepository.save(admin);
            User user = new User();
            user.setFirstName("John");
            user.setLastName("Doe");
            userRepository.save(user);
        }
        if (credentialRepository.count() == 0) {
            Credential adminCredential = new Credential();
            Credential userCredential = new Credential();

            adminCredential.setUsername("admin");
            adminCredential.setPassword(PasswordUtil.digestPassword("123456"));
            adminCredential.setRole("ADMIN");
            adminCredential.setUserId(1);
            credentialRepository.save(adminCredential);

            userCredential.setUsername("default");
            userCredential.setPassword(PasswordUtil.digestPassword("123456"));
            userCredential.setRole("user");
            userCredential.setUserId(2);
            credentialRepository.save(userCredential);
        }

    }

}
