package com.string.project.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserRegistrationDto {
    private String fullName;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty(message = "Password should not be empty")
    private String confirmPassword;

    public void setFullName(String fullName) {
        this.fullName = fullName;
        setFirstName(fullName);
        setLastName(fullName);
    }
    public void setFirstName(String fullName) {
        this.firstName = fullName.substring(0,fullName.indexOf(' '));
    }

    public void setLastName(String fullName) {
        this.lastName = fullName.substring(fullName.lastIndexOf(' ') + 1);
    }

}
