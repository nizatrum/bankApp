package com.nizatrum.bankApp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Data Transfer Object - объект, передающий данные. Данные - это и есть поля в классе.
@Getter
@Setter
@ToString
public class TransferDTO {
    private Long idAccountSender;
    private Long idAccountRecipient;
    private double sumForPayment;
}
