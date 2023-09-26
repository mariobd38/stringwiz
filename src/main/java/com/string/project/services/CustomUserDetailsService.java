package com.string.project.services;

import com.string.project.models.Role;
import com.string.project.models.User;
import com.string.project.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);


        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(mapRolesToAuthorities(user.getRoles()));

        if (user != null) {
            try {
                return new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),
                        mapRolesToAuthorities(user.getRoles()));
            } catch(Exception e) {
                System.out.println("ERROR!!!!!");
                e.printStackTrace();
                return null;
            }
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}