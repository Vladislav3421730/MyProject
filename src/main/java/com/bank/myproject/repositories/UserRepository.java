package com.bank.myproject.repositories;

import com.bank.myproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findAllByNameOrSurnameAndBankId(String name,String surname,Long id);
    public List<User> findAllByAgeOrMoneyAndBankId(int money,int age,Long id);
    public List<User> findAllByBankId(Long id);
}
