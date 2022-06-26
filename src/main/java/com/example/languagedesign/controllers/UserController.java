package com.example.languagedesign.controllers;

import com.example.languagedesign.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Value
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserService userService;

    @GetMapping("/validateLogin")
    public boolean validateLogin(@RequestParam String username, @RequestParam String password){
        return userService.validateLogin(username,password);
    }
}
