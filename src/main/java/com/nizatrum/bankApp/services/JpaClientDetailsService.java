package com.nizatrum.bankApp.services;

import com.nizatrum.bankApp.models.SecurityClient;
import com.nizatrum.bankApp.repositories.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;

    public JpaUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository
                .findByUsername(username)
                .map(SecurityClient::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}