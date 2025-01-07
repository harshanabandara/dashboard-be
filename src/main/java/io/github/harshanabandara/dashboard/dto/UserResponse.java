package io.github.harshanabandara.dashboard.dto;

import io.github.harshanabandara.dashboard.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private String firstName;
    private String lastName;
    private long userId;

    public UserResponse(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userId = user.getUserId();
    }
}
