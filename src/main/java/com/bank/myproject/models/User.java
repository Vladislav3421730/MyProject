package com.bank.myproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name="name")
    @NotEmpty
    @Size(min = 3)
    private String name;
    @Column(name="surname")
    @Size(min=3)
    @NotEmpty
    private String surname;
    @Min(18)
    @Column(name="age")
    private int age;
    @Min(1200)
    @Column(name="money")
    private int money;
    @ManyToOne(cascade =CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="bank_id")
    private Bank bank;
}
