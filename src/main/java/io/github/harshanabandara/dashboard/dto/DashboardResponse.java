package io.github.harshanabandara.dashboard.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DashboardResponse {
    private long totalUsers;
    private LocalDateTime lastUserAdded;
    private Map<Object, Long> activityData;
}
