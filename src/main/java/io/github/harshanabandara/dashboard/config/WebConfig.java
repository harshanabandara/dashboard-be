package io.github.harshanabandara.dashboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS for all endpoints under /api
                .allowedOrigins("http://localhost:4200") // Specify the frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific HHTP methods
                .allowedHeaders("*"); // Allow all headers
        // .allowCredentials(true); // Allow sending of credentials (like cookies)
    }
}
