package com.nizatrum.bankApp.repositories;

import com.nizatrum.bankApp.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findTopByOrderByIdDesc();
    Optional<Account> findByName(String name);
}
