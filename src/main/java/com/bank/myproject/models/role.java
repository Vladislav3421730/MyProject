package com.bank.myproject.models;

import org.springframework.security.core.GrantedAuthority;

public enum role implements GrantedAuthority {
    ROLE_MANAGER,ROLE_ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }
}
