package com.nizatrum.bankApp.repositories;

import com.nizatrum.bankApp.models.Account;
import com.nizatrum.bankApp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findTopByOrderByIdDesc();
}