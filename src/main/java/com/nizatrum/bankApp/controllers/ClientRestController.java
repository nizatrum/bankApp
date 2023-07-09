package com.nizatrum.bankApp.controllers;

import com.nizatrum.bankApp.models.AppError;
import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.models.MessageDTO;
import com.nizatrum.bankApp.models.Transaction;
import com.nizatrum.bankApp.services.ClientService;
import com.nizatrum.bankApp.services.TransactionService;
import com.nizatrum.bankApp.services.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ClientRestController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/getClient")
    public ResponseEntity<?> getClient(@RequestParam Long id) {
        try {
            Client client = clientService.getClient(id).orElseThrow();
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Client with id " + id + "not found"),
                    HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getCurrentUser")
    public Client getCurrentUser(Authentication authentication) {
        log.info("Получить получить текущего пользователя");
        System.out.println(authentication);
        return clientService.getClientByUsername(authentication.getPrincipal().toString());
    }

    //Принимаем отдельные данные с фронта

    @PostMapping("/executeTransfer")
    public MessageDTO executeTransfer(Authentication authentication, @RequestParam Long idAccountSender, @RequestParam Long idAccountRecipient, @RequestParam Double sumForPayment) {
        try {
            transferService.execute(authentication, idAccountSender, idAccountRecipient, sumForPayment);
            return new MessageDTO("Успешно! Перевод выполнен", true);
        } catch (Exception e) {
            return new MessageDTO("Ошибка: " + e.getMessage(), false);
        }
    }

    @GetMapping("/getTransaction")
    public List<Transaction> getTransaction(Authentication authentication) {
        return transactionService.getTransactions(authentication.getPrincipal().toString());
    }
    //Принимаем с фронта объект целиком DTO с нужными нам данными внутри
//    @PostMapping("/executeTransfer")
//    public void executeTransfer(@RequestBody TransferDTO transferDTO) {
//        System.out.println(transferDTO);
//    }
}
