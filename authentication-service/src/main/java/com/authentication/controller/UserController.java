package com.authentication.controller;

import com.authentication.model.User;
import com.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public void register(@Valid @RequestBody User user) {
        userService.register(user);
    }

    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetails getUser(@RequestParam(required = true) String username) {
        return userService.loadUserByUsername(username);
    }

    @GetMapping(value = "/currentUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public User currentUser() {
        return userService.currentUser();
    }

    @PutMapping(value = "/updateUser")
    public void updateUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
    }

}