package com.string.project.services;

import com.string.project.models.Role;
import com.string.project.models.User;
import com.string.project.repositories.RoleRepository;
import com.string.project.repositories.UserRepository;
import com.string.project.web.dto.UserRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    public UserServiceImplementation(UserRepository userRepository,
                                     RoleRepository roleRepository,
                                     PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                    userRegistrationDto.getFullName(), userRegistrationDto.getEmail(),
                    userRegistrationDto.getPassword());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        String domain = getEmailDomain(user.getEmail());
        String roleName = domain.equals("stringwiz") ? "ROLE_ADMIN" : "ROLE_IND_USER";
        Role role = roleRepository.findByName(roleName);
        if (role == null) role = checkRoleExists(roleName);
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserRegistrationDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserRegistrationDto)
                .collect(Collectors.toList());
    }

    private UserRegistrationDto mapToUserRegistrationDto(User user) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setFirstName(user.getFullName());
        userRegistrationDto.setLastName(user.getFullName());
        userRegistrationDto.setEmail(user.getEmail());
        return userRegistrationDto;
    }

//    private Role createRole(String email) {
//        Role role = new Role();
//        String domain = someEmail.substring(someEmail.indexOf("@") + 1);
//        if(email)
//        role.setName("ROLE_IND_USER");
//        return roleRepository.save(role)
//    }
    private String getEmailDomain(String email) {
        return email.substring(email.indexOf("@") + 1,email.indexOf("."));
    }

    private Role checkRoleExists(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

}
