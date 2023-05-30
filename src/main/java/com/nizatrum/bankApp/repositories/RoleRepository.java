package com.nizatrum.bankApp.repositories;

import com.nizatrum.bankApp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    // findBySystemName(String value) - можно сказать переопределяем findById, ищет вхождение в таблице в поле systemName
    Role findBySystemName(String systemName);
}
