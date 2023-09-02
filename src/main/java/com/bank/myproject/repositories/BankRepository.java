package com.bank.myproject.repositories;

import com.bank.myproject.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank,Long> {
    public List<Bank> findAllByTitle(String title);
}
