package com.nizatrum.bankApp.services;

import com.nizatrum.bankApp.models.Account;
import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.models.Role;
import com.nizatrum.bankApp.repositories.AccountRepository;
import com.nizatrum.bankApp.repositories.ClientRepository;
import com.nizatrum.bankApp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.nizatrum.bankApp.services.validators.ClientValidator.clientValidator;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    public boolean createAccount(Long clientId) {
        Account account = new Account();
        account.setBalance(0);
        Optional<Client> clientForEdit = Optional.of(clientRepository.getById(clientId));
        if (clientForEdit.isPresent()) {
            account.setName("Счет " + clientForEdit.get().getName());
            accountRepository.save(account);
            clientForEdit.get().getAccounts().add(account);
            clientRepository.save(clientForEdit.get());
            return true;
        }
        return false;
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
