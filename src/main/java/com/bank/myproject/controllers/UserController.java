package com.bank.myproject.controllers;

import com.bank.myproject.models.Bank;
import com.bank.myproject.models.User;
import com.bank.myproject.services.BankService;
import com.bank.myproject.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final BankService bankService;
    private final UserService userService;

    @GetMapping("/clients/create/{id}")
    public String showCreateUserForm(@PathVariable Long id, Model model) {
        Bank bank = bankService.GetById(id);
        model.addAttribute("bank", bank);
        model.addAttribute("user", new User());
        return "ClientCreating";
    }

    @PostMapping("/clients/create/{id}")
    public String createUser(@PathVariable Long id, User user) {
        Bank bank = bankService.GetById(id);
        userService.SaveUser(bank, user);
        return "redirect:/allUsers/{id}";
    }

    @GetMapping("/allUsers/{id}")
    public String AllUsers(@PathVariable Long id, Model model,@RequestParam(name = "search",required = false)
                String search,@RequestParam(name = "sort",defaultValue = "none") String sortBy) {
        List<User> userList = userService.FindByBankId(id,search,sortBy);
        Bank bank=bankService.GetById(id);
        model.addAttribute("UserList", userList);
        model.addAttribute("bank", bank);
        return "AllClients";
    }

    @PostMapping("/client/delete/{bank_id}/{user_id}")
    public String DeleteUsers(@PathVariable Long user_id, @PathVariable Long bank_id) {
        userService.deleteUser(user_id);
        return "redirect:/allUsers/{bank_id}";
    }

    @GetMapping("/client/edit/{bank_id}/{user_id}")
    public String EditUser(@PathVariable Long bank_id, @PathVariable Long user_id, Model model) {
        User user = userService.FindById(user_id);
        Bank bank = bankService.GetById(bank_id);
        model.addAttribute("user", user);
        model.addAttribute("bank", bank);
        return "ClientCreating";
    }
}