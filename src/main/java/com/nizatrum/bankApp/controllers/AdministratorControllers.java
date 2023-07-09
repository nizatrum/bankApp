package com.nizatrum.bankApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/adminPage")
@Controller
public class AdministratorControllers {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/client")
    public String client(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "client/index";
    } //возвращаем страницу html по адресу "resources/templates/client/index.html" и тд соответственно

    //мы можем отправлять параметры, например "/sayHello?name=Тимур", это означает, что на адрес "/sayHello"
    // отправить параметр "name=Тимур" и как в результате мы можем принять данный параметр в методе,
    // помеченным аннотацией для этого адреса с помощью @RequestParam
    // наименование принимаемого параметра в методе и передаваемого параметра в адресе должно совпадать!
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/account")
    public String account(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "account/index";
    }
}
