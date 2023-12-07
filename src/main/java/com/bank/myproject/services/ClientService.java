package com.bank.myproject.services;

import com.bank.myproject.models.Bank;
import com.bank.myproject.models.Client;
import com.bank.myproject.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void SaveUser(Bank bank, Client client) {
         client.setBank(bank);
         clientRepository.save(client);
    }
    public void deleteUser(Long id)
    {
        clientRepository.deleteById(id);
    }
    public List<Client> FindByBankId(Long id, String Search, String sortBy)
    {
        if(Search!=null) {
            if(!isNumeric(Search))
            return clientRepository.findAllByBankIdAndNameOrSurname(id,Search,Search);
            else return clientRepository.findAllByBankIdAndAgeOrMoney(id,Integer.parseInt(Search),Integer.parseInt(Search));
        }
        List<Client> list = clientRepository.findAllByBankId(id);
        switch (sortBy){
            case "Name":list.sort(Comparator.comparing(Client::getName));
            break;
            case "Surname":list.sort(Comparator.comparing(Client::getSurname));
            break;
            case "money":list.sort(Comparator.comparingInt(Client::getMoney).reversed());
            break;
            case "age":list.sort(Comparator.comparingInt(Client::getAge));
            break;
            default:
        }
        return list;
     }
    public Client FindById(Long id)
    {
        return clientRepository.findById(id).orElse(null);
    }
    public List<Client> GetUsers()
    {
        return clientRepository.findAll();
    }
    public void EditUser(Client client)
    {
        clientRepository.save(client);
    }
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
