package com.nizatrum.bankApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class MainController {
    //прописываем логику взаимодействия клиента и сервера по запросам и для того что бы Java поняла этого,
    // помечаем аннотакцией "@Controller" (возвращает html) или @RestController (возвращает json или текст)
    @GetMapping("/") //указываем адрес (в данном случае домашняя страницв), при переходе на который вызывается метод, помеченный данной аннотацией.
    public String index() {
        return "index";
    } //возвращаем страницу html по адресу "resources/templates/index.html"

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/client")
    public String client() {
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
