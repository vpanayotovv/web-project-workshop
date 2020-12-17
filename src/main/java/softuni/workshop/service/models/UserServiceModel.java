package softuni.workshop.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {
    private String username;
    private String password;
    private String email;
    private Set<RoleServiceModel> authorities;
}
