package com.nizatrum.bankApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private String username;
    private String password;
    @ManyToOne
    private Role roles;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Account> accounts;

    public Client() {}

    public Client(String email, String name, String surname, String patronymic, String username, String password, Role roles) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
