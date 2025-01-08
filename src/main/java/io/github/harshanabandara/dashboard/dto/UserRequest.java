package io.github.harshanabandara.dashboard.dto;

import io.github.harshanabandara.dashboard.model.User;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    private String firstName;
    private String lastName;

    @Hidden
    public User getUserFromRequest() {
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        return user;
    }
}
