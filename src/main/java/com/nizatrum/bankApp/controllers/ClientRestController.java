package com.nizatrum.bankApp.controllers;

import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClientRestController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/getClient")
    public Client getClient(@RequestParam Long id) {
        return clientService.getClient(id);
    }

//    @GetMapping("/getCurrentUser")
//    public Client getCurrentUser(Authentication authentication) {
//        log.info("Получить получить текущего пользователя");
//        System.out.println(authentication);
//        return clientService.getClientByEmail(authentication.getPrincipal().toString());
//    }
    @GetMapping("/getCurrentUser")
    public Client getCurrentUser(Authentication authentication) {
        log.info("Получить получить текущего пользователя");
        System.out.println(authentication);
        return clientService.getClientByUsername(authentication.getPrincipal().toString());
    }

    @PostMapping("/executeTransfer")
    public void executeTransfer(@RequestParam Long idAccountSender, @RequestParam Long idAccountRecipient, @RequestParam Double sum) {

    }
}
