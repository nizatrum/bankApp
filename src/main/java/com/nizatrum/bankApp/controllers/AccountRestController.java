package com.nizatrum.bankApp.controllers;

import com.nizatrum.bankApp.models.AppError;
import com.nizatrum.bankApp.models.MessageDTO;
import com.nizatrum.bankApp.models.Transaction;
import com.nizatrum.bankApp.services.AccountService;
import com.nizatrum.bankApp.services.ClientService;
import com.nizatrum.bankApp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AccountRestController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/createAccount")
    public MessageDTO createAccount(@RequestParam Long clientId, @RequestParam String nameAccount) {
        try {
            accountService.createAccount(clientId, nameAccount);
            return new MessageDTO("Аккаунт успешно создан", true);
        } catch (Exception e) {
            return new MessageDTO("Ошибка: " + e.getMessage(), false);
        }
    }

    @GetMapping("/getTransactionByClient")
    public ResponseEntity<?> getTransactionByClient(Long id) {
        try {
            List<Transaction> transactions = transactionService.getTransactionsByClient(id);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Transaction for client with id " + id + " not found"),
                    HttpStatus.NOT_FOUND);
        }
    }
}
