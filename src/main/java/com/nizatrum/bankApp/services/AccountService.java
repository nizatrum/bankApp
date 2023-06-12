package com.nizatrum.bankApp.services;

import com.nizatrum.bankApp.models.Account;
import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.repositories.AccountRepository;
import com.nizatrum.bankApp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    public boolean createAccount(Long clientId) {
        Account account = new Account();
        account.setBalance(0);

        Optional<Client> clientForEdit = clientRepository.findById(clientId);
        if (clientForEdit.isPresent()) {
            account.setName("Счет " + clientForEdit.get().getName());
            accountRepository.save(account);
            clientForEdit.get().getAccounts().add(accountRepository.findTopByOrderByIdDesc());
            clientRepository.save(clientForEdit.get());
            return true;
        }
        return false;
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
