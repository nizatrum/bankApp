package com.nizatrum.bankApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.thymeleaf.engine.IterationStatusVar;

import java.util.List;

@Entity //сущность нужна, чтобы в том числе по ее образу и подобию (класса Client) создать в БД соответствующую таблицу
@Setter
@Getter
 //с помощью библиотеки lombok мы спрятали через аннотации наши геттеры, сеттеры и переопределенный метод toString.
// Также может добавить аналогично конструктор с параметрами и без
public class Client {
    @Id //аннотация для того чтобы id поля Client сопоставлялось id этой сущности в БД ()
    @GeneratedValue(strategy = GenerationType.IDENTITY) //аннотация для определения, как мы будет нумеровать наши сущности
    // в БД, в данном случае инкремент по порядку (1,2,3 ...n)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String password;
    @ManyToOne // определяем отношения таблиц, в данном случае primary key Role, будет вторичным ключем в таблице Client
    private Role role;
    @ManyToMany
    private List<Account> accounts;

}
