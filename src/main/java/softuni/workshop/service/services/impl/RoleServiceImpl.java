package softuni.workshop.service.services.impl;

import org.springframework.stereotype.Service;
import softuni.workshop.service.models.RoleServiceModel;
import softuni.workshop.service.services.RoleService;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public void seedRolesInDb() {
        //TODO seed roels in database
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        //TODO find all roles
        return null;
    }

    @Override
    public RoleServiceModel findByAuthority(String role) {
        //TODO find role by authority
        return null;
    }
}
