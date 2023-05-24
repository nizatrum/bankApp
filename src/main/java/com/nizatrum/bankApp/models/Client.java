package com.nizatrum.bankApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.thymeleaf.engine.IterationStatusVar;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity //сущность нужна, чтобы в том числе по ее образу и подобию (класса Client) создать в БД соответствующую таблицу
@Setter
@Getter
@ToString
 //с помощью библиотеки lombok мы спрятали через аннотации наши геттеры, сеттеры и переопределенный метод toString.
// Также может добавить аналогично конструктор с параметрами и без
public class Client implements UserDetails {
    @Id //аннотация для того чтобы id поля Client сопоставлялось id этой сущности в БД ()
    @GeneratedValue(strategy = GenerationType.IDENTITY) //аннотация для определения, как мы будет нумеровать наши сущности
    // в БД, в данном случае инкремент по порядку (1,2,3 ...n)
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private String password;
    @ManyToOne // определяем отношения таблиц, в данном случае primary key Role, будет вторичным ключем в таблице Client
    private Role role;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Account> accounts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() // аккаунт не просроченный
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() // аккаунт не заблокирован
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() // если учетные данные не устарели
    {
        return true;
    }

    @Override
    public boolean isEnabled() // аккаунт работает
    {
        return true;
    }
}
