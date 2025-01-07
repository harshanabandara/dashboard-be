package io.github.harshanabandara.dashboard.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class PasswordUtilTest {
    @Test
    public void testSimilarPasswordReturnDifferentDigests() {
        String password1 = "123456";
        String password2 = "123456";
        String digest1 = PasswordUtil.digestPassword(password1);
        String digest2 = PasswordUtil.digestPassword(password2);
        assertFalse(digest1.equals(digest2));
        assert (PasswordUtil.verifyPassword(password1, digest2));
    }

    @Test
    public void testVerifyDigest() {
        String password1 = "123456";
        String digest1 = PasswordUtil.digestPassword(password1);
        assert (PasswordUtil.verifyPassword(password1, digest1));
    }
}
