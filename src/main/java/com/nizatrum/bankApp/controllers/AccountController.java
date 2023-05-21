package com.nizatrum.bankApp.controllers;

import com.nizatrum.bankApp.models.Account;
import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.services.AccountService;
import com.nizatrum.bankApp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public String create(@RequestParam Long id) {
        accountService.createAccount(id);
        return "OK 200";
    }

    @GetMapping("/get")
    public ModelAndView get(@RequestParam Long id, RedirectAttributes model) {

        return new ModelAndView("redirect:/client");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute Account account, RedirectAttributes model) {

        return new ModelAndView("redirect:/client");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam Long id, RedirectAttributes model) {

        return new ModelAndView("redirect:/client");
    }

}
