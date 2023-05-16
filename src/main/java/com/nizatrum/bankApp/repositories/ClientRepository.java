package com.nizatrum.bankApp.repositories;

import com.nizatrum.bankApp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository<T,ID> интерфейс библиотеки jpa, внутри которой есть методы, заменяющие запросы на языке sql,
//а параметризуем типом нашей сущности и типом id нашей сущности
// репозиторий создается, чтобы мы создали анонимный объект, чтобы в итоге из языка java транслировать при помощи jpa в sql
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
