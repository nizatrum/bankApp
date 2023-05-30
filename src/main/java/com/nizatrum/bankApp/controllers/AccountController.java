package com.nizatrum.bankApp.controllers;

import com.nizatrum.bankApp.models.Account;
import com.nizatrum.bankApp.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ModelAndView create(@RequestParam Long id, RedirectAttributes model) {
        if (accountService.createAccount(id)) {
            model.addFlashAttribute("msg", "Счет успешно создан");
        } else {
            model.addFlashAttribute("msg", "Не удалось создать счет");
        }

        return new ModelAndView("redirect:/account");
    }

    @GetMapping("/get")
    public ModelAndView get(@RequestParam Long id, RedirectAttributes model) {
        Optional<Account> accountForReturn = accountService.getAccount(id);
        if (accountForReturn.isPresent()) {
            model.addFlashAttribute("account", accountForReturn.get());
        } else {
            model.addFlashAttribute("msg", "Аккаунт не найден");
        }
        return new ModelAndView("redirect:/account");
    }
    @GetMapping("/getAccounts")
    public ModelAndView getAccounts(@RequestParam Long id, RedirectAttributes model) {
        model.addFlashAttribute("accounts", accountService.getAccounts(id));
        return new ModelAndView("redirect:/account");
    }
}
