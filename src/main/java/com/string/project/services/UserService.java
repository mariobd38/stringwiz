package com.string.project.services;

import com.string.project.models.User;
import com.string.project.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User save(UserRegistrationDto registration);
}
