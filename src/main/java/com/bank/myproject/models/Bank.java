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
    @NotEmpty()
    @Column(name = "title")
    private String title;

    @Column(name = "Date")
    @NotEmpty()
    private String Date;

    @Column(name="AmountOfClients")
    @NotNull()
    private int AmountOfClients;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bank",fetch = FetchType.EAGER)
    private List<User> users=new ArrayList<>();

}
