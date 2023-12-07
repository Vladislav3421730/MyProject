package com.bank.myproject.controllers;

import com.bank.myproject.models.User;
import com.bank.myproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LogRegController {

    private final UserService userService;

    @PostMapping("/user/bun/{id}")
    public String BunUser(@PathVariable Long id)
    {
        userService.banUser(id);
        return "redirect:/admin";
    }


    @PostMapping("/user/admin/{id}")
    public String MakeAdmin(@PathVariable Long id)
    {
        userService.MakeAdmin(id);
         return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String GetAdminPanel(Model model,Principal principal)
    {
        model.addAttribute("UserList",userService.AllUsers());
        model.addAttribute("userPrincipal",userService.getUserByPrincipal(principal));
        return "users";
    }

    @GetMapping("/registration")
    public String Registration(Model model)
    {
        model.addAttribute("user",new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String SaveUser(@Valid User user,BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("PasswordsAreNotTheSame", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.SaveUser(user)) {
            model.addAttribute("UserAlsoInSystem", "Пользователь с таким логином уже есть в системе");
            return "registration";
        }
        return "redirect:/login";
    }

}
