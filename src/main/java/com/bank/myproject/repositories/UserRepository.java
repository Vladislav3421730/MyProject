package com.bank.myproject.repositories;

import com.bank.myproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByLogin(String login);
}
