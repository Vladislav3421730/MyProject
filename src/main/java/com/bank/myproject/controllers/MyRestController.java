package com.bank.myproject.controllers;

import com.bank.myproject.models.Bank;
import com.bank.myproject.models.Client;
import com.bank.myproject.models.User;
import com.bank.myproject.services.BankService;
import com.bank.myproject.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class MyRestController {

    private final UserService userService;
    private final BankService bankService;
    @GetMapping("/users")
    public List<User> GetAllUsers(){
        List<User> list =userService.AllUsers();
        return list;
    }

}
