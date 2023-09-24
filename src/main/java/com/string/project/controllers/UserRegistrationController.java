package com.string.project.controllers;

import com.string.project.services.UserService;
import com.string.project.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class UserRegistrationController {
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistration() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            userService.save(userRegistrationDto);
//        return "redirect:/success";
            return "redirect:/success";
        } else {
            return "redirect:/signup?failure";
        }
    }





}
