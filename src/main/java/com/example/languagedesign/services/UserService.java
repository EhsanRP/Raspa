package com.example.languagedesign.services;

import com.example.languagedesign.domain.CustomUser;

public interface UserService {

    boolean validateLogin(String username, String password);

    CustomUser createUser(String username, String password);

}
