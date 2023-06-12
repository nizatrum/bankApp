package com.nizatrum.bankApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Setter
@Getter
@ToString
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String systemName; //ADMIN, USER, MODERATOR
    private String displayName; //Администратор, Пользователь, Оператор, Супервизор

    @Override
    public String getAuthority() {
        return systemName;
    }
}
