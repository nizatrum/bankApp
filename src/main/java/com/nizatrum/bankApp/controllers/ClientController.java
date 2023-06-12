package com.nizatrum.bankApp.controllers;

import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


//@Controller - нужен для того чтобы принять данные (post) и отдать данные польхователю (get), а сервис нужен, чтобы эти данные обрабатывать
//@Controller - (Слой представления) Аннотация для маркировки java класса, как класса контроллера. Данный класс представляет собой компонент,
// похожий на обычный сервлет (HttpServlet) (работающий с объектами HttpServletRequest и HttpServletResponse), но с расширенными возможностями от Spring Framework.
//@ModelAttribute - Аннотация, связывающая параметр метода или возвращаемое значение метода с атрибутом модели, которая будет использоваться при выводе jsp-страницы.
//@RequestParam - запросить параметр (по id в бд через репозиторий)
//Optional<T> - тип данных, наш объект или null, удобно проверять на null методами (isPresent or isEmpty)
//@Autowired - Аннотация позволяет автоматически установить значение поля.
@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Client client, RedirectAttributes model) {
        try {
            clientService.createClient(client);
            model.addFlashAttribute("msg", "Пользователь успешно создан, присвоен id: " + client.getId());
        } catch (Exception e) {
            model.addFlashAttribute("msg", "Пользователь не может быть создан, так как " + e.getMessage());
        }
        return new ModelAndView("redirect:/client");
    }

    @GetMapping("/get")
    public ModelAndView get(@RequestParam Long id, RedirectAttributes model) {
        try {
            Client client = clientService.getClient(id);
            model.addFlashAttribute("client", client);
        } catch (Exception e) {
            model.addFlashAttribute("msg", "Клиент с id " + id + " " + e.getMessage());
        }
        return new ModelAndView("redirect:/client");
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute Client client, RedirectAttributes model) {
        if (clientService.updateClient(client)) {
            model.addFlashAttribute("msg", "Пользователь успешно обновлен");
        } else {
            model.addFlashAttribute("msg", "Не удалось обновить пользователя");
        }
        return new ModelAndView("redirect:/client");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam Long id, RedirectAttributes model) {
        if (clientService.deleteClient(id)) {
            model.addFlashAttribute("msg", "Пользователь c id " + id.toString() + " успешно удален");
        } else {
            model.addFlashAttribute("msg", "Не удалось удалить пользователя");
        }
        return new ModelAndView("redirect:/client");
    }

}
