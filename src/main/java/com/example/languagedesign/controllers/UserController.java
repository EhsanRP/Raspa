package com.example.languagedesign.controllers;

import com.example.languagedesign.domain.CustomUser;
import com.example.languagedesign.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public CustomUser createUser(@RequestParam String username, @RequestParam String password){
        return userService.createUser(username,password);
    }
}
