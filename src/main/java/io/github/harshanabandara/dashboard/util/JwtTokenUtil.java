package io.github.harshanabandara.dashboard.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.github.harshanabandara.dashboard.exception.JwtException;
import io.github.harshanabandara.dashboard.model.Credential;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;;

/**
 *
 */
public class JwtTokenUtil {
    private static final SecretKey SECRET = Keys
            .hmacShaKeyFor(
                    "dashboard-spiring-boot-break-in-project-so-far-so-good"
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

    private static Claims extractAllClaims(String token)
            throws JwtException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw (new JwtException("Expired token", e));
        } catch (UnsupportedJwtException e) {
            throw (new JwtException("Unsupported tokem", e));
        } catch (MalformedJwtException e) {
            throw (new JwtException("Token Error", e));
        } catch (SignatureException e) {
            throw (new JwtException("Invalid signature", e));
        }

    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims;
        try {
            claims = extractAllClaims(token);
        } catch (JwtException e) {
            e.printStackTrace();
            return null;
        }
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
        // TODO: issue a new token if the current one does not work
        return username.equals(credential.getUsername()) && !isTokenExpired(token);
    }

}
