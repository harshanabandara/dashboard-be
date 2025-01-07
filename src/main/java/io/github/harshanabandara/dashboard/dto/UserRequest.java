package io.github.harshanabandara.dashboard.dto;

import io.github.harshanabandara.dashboard.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    private String firstName;
    private String lastName;

    public User getUserFromRequest() {
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        return user;
    }
}
