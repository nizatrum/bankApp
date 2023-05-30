package com.nizatrum.bankApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTestController {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/forAdmin")
    public String forAdmin() {
        return "Hello Admin!";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/forUser")
    public String forUser() {
        return "Hello User!";
    }

}
