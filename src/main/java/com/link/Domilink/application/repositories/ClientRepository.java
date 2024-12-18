package com.link.Domilink.application.repositories;

import com.link.Domilink.domain.models.Client;
import com.link.Domilink.infraestructure.dtos.ClientRequestDto;
import com.link.Domilink.infraestructure.dtos.ClientResponseDto;

public interface ClientRepository {
    ClientResponseDto register(ClientRequestDto client);

    boolean login(ClientRequestDto clientRequestDto);

    void save(Client client);

    Long getIdByUsername(String username);
}
