package com.nizatrum.bankApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.ConstructorParameters;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
public class Transaction {

    public Transaction(Account sender, Account recipient, double valueOfPayment, Date dateOfPayment) {
        this.sender = sender;
        this.recipient = recipient;
        this.valueOfPayment = valueOfPayment;
        this.dateOfPayment = dateOfPayment;
    }

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
