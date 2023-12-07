package com.bank.myproject.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column( name="login")
    @Size(min = 4,max = 25,message = "Логин должен быть от 4 до 25 символов")
    @NotBlank(message = "Не должен состоять из пробелов")
    private String login;

    @Column(name = "date_of_creating")
    private LocalDateTime DateOfCreating;

    @Column(name="email")
    private String email;

    @Column(name = "password",length = 1000)
    private String password;

    @Column(name = "isBun")
    private boolean isBun;

    @Transient
    private String ConfirmPassword;

    public boolean isAdmin()
    {
        return roles.contains(role.ROLE_ADMIN);
    }

    @ElementCollection(targetClass = role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<role> roles=new HashSet<>();

    @PrePersist
    private void init(){
        DateOfCreating=LocalDateTime.now();
        isBun=false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBun;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { return !isBun;
    }
}
