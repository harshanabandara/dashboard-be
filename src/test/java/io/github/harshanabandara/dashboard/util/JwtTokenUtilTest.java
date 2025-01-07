package io.github.harshanabandara.dashboard.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.harshanabandara.dashboard.model.Credential;

public class JwtTokenUtilTest {
    @Test
    public void validateToken() {
        Credential credential = new Credential();
        credential.setPassword("123456");
        credential.setUsername("haulmatic");
        credential.setRole("ADMIN");
        String token = JwtTokenUtil.createToken(credential);
        assertEquals(true, JwtTokenUtil.validateToken(token, credential));
    }

    @Test
    public void tokenClaimTest() {
        Credential credential = new Credential();
        credential.setPassword("123456");
        credential.setUsername("haulmatic");
        credential.setRole("ADMIN");
        String token = JwtTokenUtil.createToken(credential);
        JwtTokenUtil.extractUserName(token);

    }
}
