package io.github.harshanabandara.dashboard.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.harshanabandara.dashboard.dto.DashboardResponse;
import io.github.harshanabandara.dashboard.model.User;
import io.github.harshanabandara.dashboard.service.UserService;

@RestController
public class DashboardController {

    private final UserService userService;

    @Autowired
    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/dashboard")
    public ResponseEntity<DashboardResponse> getDashboard() {
        List<User> users = userService.getAll();
        Map<Object, Long> activityData = users.stream()
                .collect(Collectors.groupingBy(user -> user.getCreatedAt().truncatedTo(ChronoUnit.MINUTES),
                        Collectors.counting()));
        LocalDateTime lastCreatedAt = userService.getLastCreated().getCreatedAt();
        DashboardResponse dResponse = new DashboardResponse(userService.count(), lastCreatedAt, activityData);
        return new ResponseEntity<DashboardResponse>(dResponse, HttpStatus.OK);
    }
}
