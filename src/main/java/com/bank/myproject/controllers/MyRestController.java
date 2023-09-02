package com.bank.myproject.controllers;

import com.bank.myproject.models.Bank;
import com.bank.myproject.models.User;
import com.bank.myproject.services.BankService;
import com.bank.myproject.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MyRestController {

    private final BankService bankService;
    private final UserService userService;

    @GetMapping("/getBanks")
    public List<Bank> GetBanks()
    {
        return bankService.AllBanks();
    }
    @GetMapping("/getUsers")
    public  List<User>  getUsers()
    {
        return userService.GetUsers();
    }

}
