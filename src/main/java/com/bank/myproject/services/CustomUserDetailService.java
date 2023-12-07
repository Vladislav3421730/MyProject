package com.bank.myproject.services;

import com.bank.myproject.models.User;
import com.bank.myproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user=userRepository.findByLogin(login);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
