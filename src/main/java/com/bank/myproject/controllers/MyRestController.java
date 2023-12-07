package com.bank.myproject.controllers;

import com.bank.myproject.models.Client;
import com.bank.myproject.models.User;
import com.bank.myproject.services.ClientService;
import com.bank.myproject.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class MyRestController {

    private final UserService userService;
    @GetMapping("/users")
    public List<User> GetAllUsers(){
        List<User> list =userService.AllUsers();
        return list;
    }

}
