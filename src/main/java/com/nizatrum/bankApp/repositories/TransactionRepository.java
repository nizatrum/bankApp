package com.nizatrum.bankApp.repositories;

import com.nizatrum.bankApp.models.Account;
import com.nizatrum.bankApp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM TRANSACTION WHERE RECIPIENT_ID IN (?1) OR SENDER_ID IN (?1) ORDER BY ID desc",
           nativeQuery = true)
    List<Transaction> findAllTransactions(List<Long> accountsId);

    //@Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
    //User findUserByStatusAndName(Integer status, String name);

}
