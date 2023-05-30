package com.nizatrum.bankApp.services;

import com.nizatrum.bankApp.models.Client;

import com.nizatrum.bankApp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service // для того чтобы spring понимал, что в данном классе у нас содержится методы для бизнес логики
public class ClientService {
    @Autowired //@Autowired - Аннотация позволяет автоматически установить значение поля.
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createClient(Client client) {
        client.setAccounts(new ArrayList<>());
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
        return true;
    }
    public Optional<Client> getClient(Long id) {
        return clientRepository.findById(id);
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
        clientRepository.delete(clientRepository.findById(id).get());
        return true;
    }
}
