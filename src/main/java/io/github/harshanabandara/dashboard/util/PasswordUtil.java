package io.github.harshanabandara.dashboard.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.github.harshanabandara.dashboard.model.Credential;

public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static boolean verifyPassword(String password, Credential credential) {
        return encoder.matches(password, credential.getPassword());
    }

    public static boolean verifyPassword(String password, String digest) {
        return encoder.matches(password, digest);
    }

    public static String digestPassword(String password) {
        return encoder.encode(password);
    }
}
