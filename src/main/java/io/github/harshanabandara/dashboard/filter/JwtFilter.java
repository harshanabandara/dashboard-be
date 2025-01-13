package io.github.harshanabandara.dashboard.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.harshanabandara.dashboard.model.Credential;
import io.github.harshanabandara.dashboard.service.CredentialService;
import io.github.harshanabandara.dashboard.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final CredentialService credentialService;

    @Autowired
    public JwtFilter(final CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeadeString = request.getHeader("Authorization");
        String token = null;
        if (authHeadeString != null) {
            token = authHeadeString.split(" ")[1].trim();
        }
        String username = null;
        Credential credential;
        if (token != null) {
            username = JwtTokenUtil.extractUserName(token);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            credential = credentialService.loadCredentialByusername(username);
            if (JwtTokenUtil.validateToken(token, credential)) {
                System.out.println("validate token for " + username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(credential, null,
                        credential.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                System.out.println("Cannot validate token: " + token);
            }
        }
        filterChain.doFilter(request, response);
    }

}
