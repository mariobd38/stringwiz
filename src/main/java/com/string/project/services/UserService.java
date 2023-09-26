package com.string.project.services;

import com.string.project.models.User;
import com.string.project.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void saveUser(UserRegistrationDto registration);
    User findUserByEmail(String email);
    List<UserRegistrationDto> findAllUsers();
}
