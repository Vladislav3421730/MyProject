package com.bank.myproject.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="banks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Size(min = 2,message = "Строка должна быть больше 2 символов")
    @Column(name = "title")
    private String title;

    @Column(name = "Date")
    private String Date;

    @Min( value = 200,message = "Минимум 200 клиентов")
    @Column(name="AmountOfClients")
    private int AmountOfClients;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bank",fetch = FetchType.EAGER)
    private List<Client> clients =new ArrayList<>();

}
