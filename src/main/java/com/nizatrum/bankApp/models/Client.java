package com.nizatrum.bankApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Entity //сущность нужна, чтобы в том числе по ее образу и подобию (класса Client) создать в БД соответствующую таблицу
@Setter
@Getter
@ToString
 //с помощью библиотеки lombok мы спрятали через аннотации наши геттеры, сеттеры и переопределенный метод toString.
// Также может добавить аналогично конструктор с параметрами и без
public class Client {
    @Id //аннотация для того чтобы id поля Client сопоставлялось id этой сущности в БД ()
    @GeneratedValue//аннотация для определения, как мы будет нумеровать наши сущности
    // в БД, в данном случае инкремент по порядку (1,2,3 ...n)
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private String username;
    private String password;
    private String roles;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Account> accounts;
    public Client() { }
    public Client(String email, String name, String surname, String patronymic, String username, String password, String roles) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
