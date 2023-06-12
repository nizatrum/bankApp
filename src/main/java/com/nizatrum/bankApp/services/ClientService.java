package com.nizatrum.bankApp.services;

import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.repositories.ClientRepository;
import com.nizatrum.bankApp.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service // для того чтобы spring понимал, что в данном классе у нас содержится методы для бизнес логики
public class ClientService {
    @Autowired //@Autowired - Аннотация позволяет автоматически установить значение поля.
    private ClientRepository clientRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createClient(Client client) throws Exception {
        log.info("Запуск процесса создания client c id " + client.getId());
        if (StringUtils.isEmptyOrWhitespace(client.getEmail())) {
            throw new IllegalArgumentException("поле Email должно содержать корректное значение");
        }
        if (StringUtils.isEmptyOrWhitespace(client.getUsername())) {
            throw new IllegalArgumentException("поле Username должно содержать корректное значение");
        }
        if (StringUtils.isEmptyOrWhitespace(client.getPassword())) {
            throw new IllegalArgumentException("поле Password должно содержать корректное значение");
        }
        if (StringUtils.isEmptyOrWhitespace(client.getName())) {
            throw new IllegalArgumentException("поле Name должно содержать корректное значение");
        }
        if (StringUtils.isEmptyOrWhitespace(client.getSurname())) {
            throw new IllegalArgumentException("поле Surname должно содержать корректное значение");
        }
        if (StringUtils.isEmptyOrWhitespace(client.getPatronymic())) {
            throw new IllegalArgumentException("поле Patronymic должно содержать корректное значение");
        }
        client.setAccounts(new ArrayList<>());
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRole(roleRepository.findBySystemName("ROLE_USER"));
        clientRepository.save(client);
        log.info("client с id " + client.getId() + " успешно создан");
    }
    public Client getClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new IllegalArgumentException("отсутствует в БД");
        }
    }
    public boolean updateClient(Client client) {
        Client clientForChange = clientRepository.findById(client.getId()).get();
        clientForChange.setName(client.getName());
        clientForChange.setSurname(client.getSurname());
        clientForChange.setPatronymic(client.getPatronymic());
        clientForChange.setPassword(client.getPassword());
        clientRepository.save(clientForChange);
        return true;
    }
    public boolean deleteClient(Long id) {
        Optional<Client> clientForDeleted = clientRepository.findById(id);
        if (clientForDeleted.isPresent()) {
            clientRepository.delete(clientForDeleted.get());
            return true;
        }
        else {
            return false;
        }
    }
}
