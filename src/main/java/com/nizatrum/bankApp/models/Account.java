package com.nizatrum.bankApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double balance;

    public Account() {

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = Math.round(balance * 100.0) / 100.0;
    }
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
}
