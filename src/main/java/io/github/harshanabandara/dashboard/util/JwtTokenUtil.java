package io.github.harshanabandara.dashboard.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.github.harshanabandara.dashboard.model.Credential;
import io.github.harshanabandara.dashboard.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;;

/**
 *
 */
public class JwtTokenUtil {
    private static final SecretKey SECRET = Keys
            .hmacShaKeyFor(
                    "dashboard-for-haulmatic-se-interview-coding-assignment"
                            .getBytes(StandardCharsets.UTF_8));
    private static final String ROLE = "role";
    private static final String USERNAME = "username";

    public static String createToken(Map<String, String> tokenData, String username) {
        return Jwts.builder()
                .setClaims(tokenData)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String createToken(Credential credential) {
        Map<String, String> map = new HashMap<>();
        map.put(ROLE, credential.getRole());
        map.put(USERNAME, credential.getUsername());
        return createToken(map, credential.getUsername());
    }

    private static Claims extractAllClaims(String token) {
        System.out.println(SECRET);
        return Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static boolean validateToken(String token, Credential credential) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET).build().parse(token);
        } catch (Exception e) {
            return false;
        }
        String username = extractUserName(token);
        return username.equals(credential.getUsername()) && !isTokenExpired(token);
    }

}
