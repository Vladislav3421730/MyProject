package com.bank.myproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    @Size(min = 3,message = "Минимум 3 символа")
    private String name;


    @Column(name="surname")
    @Size(min=3,message = "Минимум 3 символа")
    private String surname;

    @Min(value = 18,message = "Минимум 18 лет")
    @Column(name="age")
    private int age;

    @Min(value = 1200, message = "Минимум 1200")
    @Column(name="money")
    private int money;

    @ManyToOne(cascade =CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="bank_id")
    private Bank bank;
}
