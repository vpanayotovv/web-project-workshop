package softuni.workshop.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

}
