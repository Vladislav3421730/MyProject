package com.bank.myproject.services;

import com.bank.myproject.models.Bank;
import com.bank.myproject.repositories.BankRepository;
import com.bank.myproject.repositories.ClientRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankService  {

    private final BankRepository bankRepository;

    private final ClientRepository clientRepository;

    public void SaveBank(Bank bank)
    {
        bankRepository.save(bank);
    }

    public List<Bank> ListBank(String sortBy,String search)
    {
        if(search!=null) {
            return bankRepository.findAllByTitle(search);
        }
        List<Bank> bankList= bankRepository.findAll();
        switch (sortBy) {
            case "title":
                bankList.sort((x, y) -> x.getTitle().compareTo(y.getTitle()));
                break;
            case "NumOfClients":
                bankList.sort((x, y) -> y.getAmountOfClients() - x.getAmountOfClients());
                break;
            case "date":
                bankList.sort((x, y) -> x.getDate().compareTo(y.getDate()));
            default:
        }
        return bankList;
    }
    public List<Bank> AllBanks()
    {
        return bankRepository.findAll();
    }
    public Bank GetById(Long id)
    {
        Bank bank =bankRepository.getById(id);
        return bank;
    }
    public void DeleteBank(Long id)
    {
        bankRepository.deleteById(id);
    }

}
