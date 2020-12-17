package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.workshop.data.entities.Role;
import softuni.workshop.data.repositories.RoleRepository;
import softuni.workshop.service.models.RoleServiceModel;
import softuni.workshop.service.services.RoleService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRolesInDb() {
        Role admin = new Role("ADMIN");
        Role user = new Role("USER");

        roleRepository.saveAndFlush(admin);
        roleRepository.saveAndFlush(user);
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return this.roleRepository.findAll()
                .stream()
                .map(role -> this.modelMapper.map(role,RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String role) {
        return this.modelMapper.map(this.roleRepository.findByAuthority(role),RoleServiceModel.class);
    }
}
