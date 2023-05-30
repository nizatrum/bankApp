package com.nizatrum.bankApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTestRestController {
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/user")
    public String user() {
        return "Hello User!";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin!";
    }
}
