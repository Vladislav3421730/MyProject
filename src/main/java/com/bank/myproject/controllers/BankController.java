package com.bank.myproject.controllers;

import com.bank.myproject.models.Bank;
import com.bank.myproject.services.BankService;

import com.bank.myproject.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class BankController {

    private final BankService bankService;
    private final UserService userService;

    @GetMapping("/bank/create")
    public String firstpage(Model model, Bank bank)
    {
        model.addAttribute("bank",bank);
        return "BankCreating";
    }
    @PostMapping("/bank/save")
    public String SaveBank(@Valid Bank bank, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "BankCreating";
        }

        bankService.SaveBank(bank);
        return "redirect:/";
    }
    @GetMapping("/")
    public String AllBank(Model model,@RequestParam(name="sort",defaultValue = "none") String nameBy,
                          @RequestParam(name="search",required = false) String search,Principal principal)
    {
      List<Bank> bankList=bankService.ListBank(nameBy,search);
      model.addAttribute("user",userService.getUserByPrincipal(principal));
      model.addAttribute("BankList",bankList);
      model.addAttribute("search",search);
      return "AllBanks";
    }

    @GetMapping("/bank/edit/{id}")
    public String EditBanks(@PathVariable Long id,Model model)
    {
        Bank bank=bankService.GetById(id);
        model.addAttribute("bank",bank);
        return "BankCreating";
    }

    @PostMapping("/bank/delete/{id}")
    public String DeleteBank(@PathVariable Long id)
    {
        bankService.DeleteBank(id);
        return "redirect:/";
    }

}