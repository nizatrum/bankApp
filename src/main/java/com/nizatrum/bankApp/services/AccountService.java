package com.nizatrum.bankApp.services;

import com.nizatrum.bankApp.models.Account;
import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.repositories.AccountRepository;
import com.nizatrum.bankApp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.nizatrum.bankApp.services.validation.Validation.validateAccountName;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    public void createAccount(Long clientId, String nameAccount) {
        if (accountRepository.findByName(nameAccount).isPresent()) {
            throw new IllegalArgumentException("Клиент с указанный счетом уже существует");
        }
        Optional<Client> clientOwner = clientRepository.findById(clientId);
        if (clientOwner.isPresent()) {
            if (validateAccountName(nameAccount)) {
                Account account = new Account(nameAccount, 0);
                accountRepository.save(account);
//            clientOwner.get().getAccounts().add(accountRepository.findTopByOrderByIdDesc());
                clientOwner.get().getAccounts().add(accountRepository.findById(account.getId()).get());
                clientRepository.save(clientOwner.get());
            } else {
                throw new IllegalArgumentException("Некорректный номер счета");
            }
        } else {
            throw new NoSuchElementException("Пользователя не существует или указанный Id - некорректный");
        }
    }
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAccounts(Long id) {
        Client client = clientRepository.findById(id).get();
        return client.getAccounts();
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
