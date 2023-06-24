package com.nizatrum.bankApp.services;

import com.nizatrum.bankApp.models.Account;
import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.repositories.AccountRepository;
import com.nizatrum.bankApp.repositories.ClientRepository;
import com.nizatrum.bankApp.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class TransferService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    public void execute(Authentication authentication, Long idAccountSender, Long idAccountRecipient, Double sumForPayment) {
        Client sender = clientRepository.findByUsername(authentication.getPrincipal().toString()).get();
        Optional<Account> fromAccount = accountRepository.findById(idAccountSender);
        Optional<Account> toAccount = accountRepository.findById(idAccountRecipient);

        boolean isCorrectSum;
        boolean isAccountBelongsSender;
        boolean isAccountRecipientExist ;

        //действия:
        //снять sumForPayment с счета idAccountSender
        //положить sumForPayment на счет idAccountRecipient

        //условия:
        //idAccountSender принадлежит текущему пользователю
        if (sender.getAccounts().contains(fromAccount.get())) {
            log.info("Счет списания принадлежит пользователю");
            isAccountBelongsSender = true;
            //sumForPayment сумма валидная, больше 0
            if (sumForPayment > 0) {
                log.info("Сумма для перевода корректная");
                //sumForPayment имеется на счете idAccountSender
                if (fromAccount.get().getBalance() >=  sumForPayment) {
                    log.info("Пользователь располагает суммой " + sumForPayment);
                    isCorrectSum = true;
                } else {
                    throw new IllegalArgumentException("Недостаточно средств на указанном счете");
                }
            } else {
                throw new IllegalArgumentException("Некорректная сумма");
            }
        } else {
            throw new IllegalArgumentException("Некорректный счет списания");
        }
        //если добавим блокировку счета, необходимо добавить условие.
        //idAccountRecipient существует, чтобы на него перевести
        if (toAccount.isPresent()) {
            log.info("Аккаунт получателя средств существует");
            isAccountRecipientExist = true;
        } else {
            throw new IllegalArgumentException("Данный счет получателя не существует");
        }
        //в дальнейшем необходимо провести рефакторинг, заменить на транзакцию.
        if (isCorrectSum && isAccountBelongsSender && isAccountRecipientExist) {
            fromAccount.get().setBalance(fromAccount.get().getBalance() - sumForPayment);
            toAccount.get().setBalance(toAccount.get().getBalance() + sumForPayment);
            accountRepository.save(fromAccount.get());
            accountRepository.save(toAccount.get());
        } else {
            throw new IllegalArgumentException("Операция невозможна");
        }
    }
}
