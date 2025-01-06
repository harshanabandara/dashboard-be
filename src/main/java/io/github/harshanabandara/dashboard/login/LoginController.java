package io.github.harshanabandara.dashboard.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private String userToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoidXNlciIsInJvbGUiOiJ1c2VyIn0.P-Tlw6Q_gT6NSROFoYFdN5C7NpVirFjXqBDETOF0YKs";
    private String adminToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiaGF1bG1hdGljIiwicm9sZSI6ImFkbWluIn0.an3Ij7HK01wzS1uqT-oMiD4MjK0LUQLHlUCvZmztNgU";

    @PostMapping("/login")
    public String login() {
        return userToken;
    }
}
