package com.example.languagedesign.services;

import com.example.languagedesign.domain.CustomUser;
import com.example.languagedesign.exceptions.ResourceNotFoundException;
import com.example.languagedesign.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Value
@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean validateLogin(String username, String password) {
        var userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty())
            throw new ResourceNotFoundException("User with Username " + username + " not found");

        return bCryptPasswordEncoder.matches(password,userOptional.get().getPassword());
    }

    @Override
    public CustomUser createUser(String username, String password) {
        var user = new CustomUser();

        if (userRepository.findByUsername(username).isPresent())
            throw new ResourceNotFoundException("User with Username " + username + " already exists");

        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }
}
