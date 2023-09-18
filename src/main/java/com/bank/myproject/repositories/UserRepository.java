package com.bank.myproject.repositories;

import com.bank.myproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findAllByBankIdAndNameOrSurname(Long id,String surname,String name);
    public List<User> findAllByBankIdAndAgeOrMoney(Long id,int age,int money);
    public List<User> findAllByBankId(Long id);
}

