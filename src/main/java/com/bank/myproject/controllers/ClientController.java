package com.bank.myproject.controllers;

import com.bank.myproject.models.Bank;
import com.bank.myproject.models.Client;
import com.bank.myproject.services.BankService;
import com.bank.myproject.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class ClientController {

    private final BankService bankService;
    private final ClientService clientService;

    @GetMapping("/clients/create/{id}")
    public String showCreateUserForm(@PathVariable Long id, Model model) {
        Bank bank = bankService.GetById(id);
        model.addAttribute("bank", bank);
        model.addAttribute("client", new Client());
        return "ClientCreating";
    }

    @PostMapping("/clients/create/{id}")
    public String createUser(@Valid Client client, BindingResult bindingResult, @PathVariable Long id,Model model) {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("bank",bankService.GetById(id));
            return "ClientCreating";
        }
        Bank bank = bankService.GetById(id);
        clientService.SaveUser(bank, client);
        return "redirect:/allUsers/{id}";
    }

    @GetMapping("/allUsers/{id}")
    public String AllUsers(@PathVariable Long id, Model model,@RequestParam(name = "search",required = false)
                String search,@RequestParam(name = "sort",defaultValue = "none") String sortBy) {
        List<Client> clientList = clientService.FindByBankId(id,search,sortBy);
        Bank bank=bankService.GetById(id);
        model.addAttribute("UserList", clientList);
        model.addAttribute("bank", bank);
        return "AllClients";
    }

    @PostMapping("/client/delete/{bank_id}/{user_id}")
    public String DeleteUsers(@PathVariable Long user_id, @PathVariable Long bank_id) {
        clientService.deleteUser(user_id);
        return "redirect:/allUsers/{bank_id}";
    }

    @GetMapping("/client/edit/{bank_id}/{user_id}")
    public String EditUser(@PathVariable Long bank_id, @PathVariable Long user_id, Model model) {
        Client client = clientService.FindById(user_id);
        Bank bank = bankService.GetById(bank_id);
        model.addAttribute("client", client);
        model.addAttribute("bank", bank);
        return "ClientCreating";
    }
}