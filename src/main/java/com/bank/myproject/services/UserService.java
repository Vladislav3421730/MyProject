package com.bank.myproject.services;

import com.bank.myproject.models.User;
import com.bank.myproject.models.role;
import com.bank.myproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean SaveUser(User user)
    {
        User user1=userRepository.findByLogin(user.getLogin());
        if(user1!=null) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(role.ROLE_MANAGER));
        userRepository.save(user);
        return true;
    }


    public User GetById(Long id)
    {
        return userRepository.getById(id);
    }
    public List<User> AllUsers()
    {
        List<User> UserList=userRepository.findAll();
        return UserList;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByLogin(principal.getName());
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isBun() && !user.getRoles().contains(role.ROLE_ADMIN)) {
                user.setBun(false);
            } else {
                user.setBun(true);
            }
        }
        userRepository.save(user);

    }
    public void MakeAdmin(Long id)
    {
        User user=userRepository.findById(id).orElse(null);
        if(user!=null)
        {
            if(user.getRoles().contains(role.ROLE_ADMIN)) {
                user.getRoles().remove(role.ROLE_ADMIN);
            }
            else {
                user.getRoles().add(role.ROLE_ADMIN);
            }
        }
        userRepository.save(user);
    }

}
