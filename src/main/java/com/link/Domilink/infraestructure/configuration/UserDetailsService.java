package com.link.Domilink.infraestructure.configuration;

import com.link.Domilink.infraestructure.entities.ClientEntity;
import com.link.Domilink.infraestructure.repositories.ClientJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final ClientJpaRepository clientJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientEntity client = clientJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.withUsername(client.getUsername())
                .password(client.getPassword())
                .build();
    }
}
