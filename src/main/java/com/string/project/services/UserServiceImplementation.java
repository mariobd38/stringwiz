package com.string.project.services;

import com.string.project.models.User;
import com.string.project.repositories.UserRepository;
import com.string.project.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    public UserServiceImplementation(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                    userRegistrationDto.getFullName(), userRegistrationDto.getEmail(),
                    userRegistrationDto.getPassword());
        return userRepository.save(user);
    }
}
