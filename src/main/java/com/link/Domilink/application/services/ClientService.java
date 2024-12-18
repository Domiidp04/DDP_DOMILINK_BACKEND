package com.link.Domilink.application.services;

import com.link.Domilink.application.repositories.ClientRepository;
import com.link.Domilink.infraestructure.dtos.ClientRequestDto;
import com.link.Domilink.infraestructure.dtos.ClientResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientResponseDto register(ClientRequestDto clientRequestDto) {
        return clientRepository.register(clientRequestDto);
    }

    public boolean login(ClientRequestDto clientRequestDto) {
        return clientRepository.login(clientRequestDto);
    }

    public Long getClientIdByUsername(String username) {
        return clientRepository.getIdByUsername(username);
    }


}
