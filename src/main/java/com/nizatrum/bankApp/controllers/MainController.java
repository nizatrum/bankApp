package com.nizatrum.bankApp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    //прописываем логику взаимодействия клиента и сервера по запросам и для того что бы Java поняла этого,
    // помечаем аннотакцией "@Controller" (возвращает html) или @RestController (возвращает json или текст)
    @GetMapping("/") //указываем адрес (в данном случае домашняя страницв), при переходе на который вызывается метод, помеченный данной аннотацией.
    public String index(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    } //возвращаем страницу html по адресу "resources/templates/index.html"

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/userPage")
    public String userPage(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "userPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/adminPage")
    public String adminPage(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "adminPage";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
