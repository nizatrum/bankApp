package com.nizatrum.bankApp.services;

import com.nizatrum.bankApp.models.Account;
import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.models.Transaction;
import com.nizatrum.bankApp.repositories.ClientRepository;
import com.nizatrum.bankApp.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientRepository clientRepository;
    public List<Transaction> getTransactions(String username) {
        Long userId = clientRepository.findByUsername(username).get().getId();
        List<Account> userAccounts = clientRepository.findById(userId).get().getAccounts();
        List<Long> idAccounts = new ArrayList<>();
        for (int i = 0; i < userAccounts.size(); i++) {
            idAccounts.add(userAccounts.get(i).getId());
        }
        return transactionRepository.findAllTransactions(idAccounts);
    }
    public List<Transaction> getTransactionsByClient(Long clientId) {
        List<Account> userAccounts = clientRepository.findById(clientId).get().getAccounts();
        List<Long> idAccounts = new ArrayList<>();
        for (int i = 0; i < userAccounts.size(); i++) {
            idAccounts.add(userAccounts.get(i).getId());
        }
        return transactionRepository.findAllTransactions(idAccounts);
    }
}
