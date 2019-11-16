package ru.geekbrains.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.persistence.entity.User;
import ru.geekbrains.service.UserService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/user")
@RestController
public class UserRestController {

    public UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/username={username}")
    public Optional<User> getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }
    @GetMapping(value = "/all")
    public List<User> getAllUsers(){
        return userService.findAllUsersWithAuthorities();
    }
}
