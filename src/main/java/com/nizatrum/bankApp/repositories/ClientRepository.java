package com.nizatrum.bankApp.repositories;

import com.nizatrum.bankApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JpaRepository<T,ID> интерфейс библиотеки jpa, внутри которой есть методы, заменяющие запросы на языке sql,
//а параметризуем типом нашей сущности и типом id нашей сущности
// репозиторий создается, чтобы мы создали анонимный объект, чтобы в итоге из языка java транслировать при помощи jpa в sql
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
