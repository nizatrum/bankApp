package com.nizatrum.bankApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Setter
@Getter
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Account sender;
    @ManyToOne
    private Account recipient;
    private double valueOfPayment;
    private Date dateOfPayment;

}
