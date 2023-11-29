package com.bank.myproject.controllers;

import com.bank.myproject.models.Bank;
import com.bank.myproject.services.BankService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class BankController {

    private final BankService bankService;

    @GetMapping("/bank/create")
    public String firstpage(Model model, Bank bank)
    {
        model.addAttribute("bank",bank);
        return "BankCreating";
    }
    @PostMapping("/bank/save")
    public String SaveBank(@Valid  Bank bank,  Model model)
    {
        bankService.SaveBank(bank);
        return "redirect:/";
    }
    @GetMapping("/")
    public String AllBank(Model model,@RequestParam(name="sort",defaultValue = "none") String nameBy,
                          @RequestParam(name="search",required = false) String search)
    {
      List<Bank> bankList=bankService.ListBank(nameBy,search);
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
