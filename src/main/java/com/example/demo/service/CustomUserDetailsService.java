package com.example.demo.service;

///import com.example.demo.model.Role;
import com.example.demo.exception.UserAlreadyExists;
import com.example.demo.model.User;
///import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }

    public boolean saveUser(String name,String password) throws UserAlreadyExists {
        if (userRepository.findByUsername(name).isEmpty()) {
            User newUs = new User(name, passwordEncoder.encode(password), Collections.singletonList("ROLE_USER"));
            userRepository.save(newUs);
            return true;
        }
        else
        {
            throw new UserAlreadyExists("User with this name already exists");
        }
    }
}
