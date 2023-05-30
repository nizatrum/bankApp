package com.nizatrum.bankApp.services;

import com.nizatrum.bankApp.models.User;
import com.nizatrum.bankApp.repositories.RoleRepository;
import com.nizatrum.bankApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service // для того чтобы spring понимал, что в данном классе у нас содержится методы для бизнес логики
public class UserService {
    @Autowired //@Autowired - Аннотация позволяет автоматически установить значение поля.
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        user.setAccounts(new ArrayList<>());
        user.setRoles(roleRepository.findBySystemName("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
    public boolean updateUser(User user) {
        User userForChange = userRepository.findById(user.getId()).get();
        userForChange.setName(user.getName());
        userForChange.setSurname(user.getSurname());
        userForChange.setPatronymic(user.getPatronymic());
        userForChange.setPassword(user.getPassword());
        userRepository.save(userForChange);
        return true;
    }
    public boolean deleteUser(Long id) {
        userRepository.delete(userRepository.findById(id).get());
        return true;
    }
}
