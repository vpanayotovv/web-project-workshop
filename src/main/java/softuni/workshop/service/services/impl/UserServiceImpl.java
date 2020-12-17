package softuni.workshop.service.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.workshop.service.models.UserServiceModel;
import softuni.workshop.service.services.UserService;
import softuni.workshop.web.models.UserRegisterModel;


@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserServiceModel registerUser(UserRegisterModel userRegisterModel) {
        //TODO register user
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //TODO find user by username for Spring Security
        return null;
    }
}
