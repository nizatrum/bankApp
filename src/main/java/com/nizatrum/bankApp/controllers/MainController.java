package com.nizatrum.bankApp.controllers;


import com.nizatrum.bankApp.models.Client;
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
    public String index() {
        return "index";
    } //возвращаем страницу html по адресу "resources/templates/index.html"

    //@PreAuthorize("hasAuthority('USER')")
    @GetMapping("/userPage")
    public String userPage() {
        return "userPage";
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/adminPage")
    public String adminPage(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "adminPage";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/client")
    public String client(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "client/index";
    } //возвращаем страницу html по адресу "resources/templates/client/index.html" и тд соответственно

    //мы можем отправлять параметры, например "/sayHello?name=Тимур", это означает, что на адрес "/sayHello"
    // отправить параметр "name=Тимур" и как в результате мы можем принять данный параметр в методе,
    // помеченным аннотацией для этого адреса с помощью @RequestParam
    // наименование принимаемого параметра в методе и передаваемого параметра в адресе должно совпадать!
    @GetMapping("/account")
    public String account() {
        return "account/index";
    }
}
