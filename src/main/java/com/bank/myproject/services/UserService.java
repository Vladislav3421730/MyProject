package com.bank.myproject.services;

import com.bank.myproject.models.Bank;
import com.bank.myproject.models.User;
import com.bank.myproject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void SaveUser(Bank bank, User user) {
         user.setBank(bank);
        userRepository.save(user);
    }
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }
    public List<User> FindByBankId(Long id,String Search,String sortBy)
    {
        if(Search!=null) {
            if(!isNumeric(Search))
            return userRepository.findAllByBankIdAndNameOrSurname(id,Search,Search);
            else return userRepository.findAllByBankIdAndAgeOrMoney(id,Integer.parseInt(Search),Integer.parseInt(Search));
        }
        }
        List<User> list = userRepository.findAllByBankId(id);
        switch (sortBy){
            case "Name":list.sort(Comparator.comparing(User::getName));
            break;
            case "Surname":list.sort(Comparator.comparing(User::getSurname));
            break;
            case "money":list.sort(Comparator.comparingInt(User::getMoney).reversed());
            break;
            case "age":list.sort(Comparator.comparingInt(User::getAge));
            break;
            default:
        }
        return list;
     }
    public User FindById(Long id)
    {
        return userRepository.findById(id).orElse(null);
    }
    public List<User> GetUsers()
    {
        return userRepository.findAll();
    }
    public void EditUser(User user)
    {
        userRepository.save(user);
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
