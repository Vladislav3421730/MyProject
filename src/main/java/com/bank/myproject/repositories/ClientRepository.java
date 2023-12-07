package com.bank.myproject.repositories;

import com.bank.myproject.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ClientRepository extends JpaRepository<Client,Long> {
    public List<Client> findAllByBankIdAndNameOrSurname(Long id, String surname, String name);
    public List<Client> findAllByBankIdAndAgeOrMoney(Long id, int age, int money);
    public List<Client> findAllByBankId(Long id);
}

