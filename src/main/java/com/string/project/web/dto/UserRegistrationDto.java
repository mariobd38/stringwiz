package com.string.project.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserRegistrationDto {
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
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
