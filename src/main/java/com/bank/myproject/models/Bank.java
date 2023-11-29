package com.bank.myproject.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Size(min = 2)
    @NotEmpty(message = "Строка не должна быть пустой")
    @Column(name = "title")
    private String title;

    @Column(name = "Date")
    private String Date;

    @Column(name="AmountOfClients")
    @Min(value = 2,message = "Минимум 200 клиентов")
    private int AmountOfClients;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bank",fetch = FetchType.EAGER)
    private List<User> users=new ArrayList<>();

}
