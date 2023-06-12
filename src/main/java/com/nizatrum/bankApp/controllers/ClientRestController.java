package com.nizatrum.bankApp.controllers;

import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/getClient")
    public Client getClient(@RequestParam Long id) {
//        try {
//            return clientService.getClient(id);
//
//        }
//        catch(Exception e) {
//            e.getMessage();
//        }
//        return null;
        return clientService.getClient(id);
    }
}
