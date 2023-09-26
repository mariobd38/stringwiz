package com.string.project.controllers;

import com.string.project.models.User;
import com.string.project.services.UserService;
import com.string.project.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//@RequestMapping("/signup")
public class MainController {
    private UserService userService;

    public MainController(UserService userService) {
        //super();
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user",user);
        return "signup";
    }

    @PostMapping("/signup/save")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto,
                                      BindingResult result,
                                      Model model) {
        System.out.println("GETTING EMAIL..");
        System.out.println(userRegistrationDto.getEmail());
        User existingUser = userService.findUserByEmail(userRegistrationDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email","error.email",
                    "There is already an account registered with the same email");
//            return "redirect:/signup?emailAlreadyExists";
        }
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            result.rejectValue("password","error.password",
                    "Sign up attempt unsuccessful. Passwords must match");
            //return "redirect:/success";
//            return "redirect:/signup?failure";
        }
        if (result.hasErrors()) {
            model.addAttribute("user",userRegistrationDto);
            return "/signup";
        }
        userService.saveUser(userRegistrationDto);
        return "redirect:/success";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserRegistrationDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }
}
