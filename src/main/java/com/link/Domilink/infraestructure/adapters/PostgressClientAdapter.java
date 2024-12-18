package com.link.Domilink.infraestructure.adapters;

import com.link.Domilink.application.repositories.ClientRepository;
import com.link.Domilink.domain.models.Client;
import com.link.Domilink.infraestructure.dtos.ClientRequestDto;
import com.link.Domilink.infraestructure.dtos.ClientResponseDto;
import com.link.Domilink.infraestructure.entities.ClientEntity;
import com.link.Domilink.infraestructure.mappers.ClientMapper;
import com.link.Domilink.infraestructure.repositories.ClientJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PostgressClientAdapter implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void save(Client client) {
        ClientEntity clientEntity = clientMapper.modelToEntity(client);
    }

    @Override
    public Long getIdByUsername(String username) {
        return clientJpaRepository.getClientIdByUsername(username);
    }


    @Override
    public ClientResponseDto register(ClientRequestDto clientRequestDto) {
        Client client = clientMapper.requestDtoToModel(clientRequestDto);

        Optional<ClientEntity> existingClient = clientJpaRepository.findByUsername(client.getUsername());
        if (existingClient.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Crear la entidad ClientEntity a partir del modelo Client
        ClientEntity newClientEntity = new ClientEntity();
        newClientEntity.setUsername(clientRequestDto.username());
        newClientEntity.setPassword(passwordEncoder.encode(clientRequestDto.password()));

        // Guardar la entidad en la base de datos
        ClientEntity savedClientEntity = clientJpaRepository.save(newClientEntity);

        // Convertir la entidad guardada en un modelo de dominio
        Client savedClient = clientMapper.entityToModel(savedClientEntity);

        // Convertir el modelo de dominio a un DTO de respuesta
        ClientResponseDto clientResponseDto = clientMapper.modelToResponseDto(savedClient);

        return clientResponseDto;
    }

    @Override
    public boolean login(ClientRequestDto clientRequestDto) {
        // De requestDto a modelo
        Client requestDtoToModel = clientMapper.requestDtoToModel(clientRequestDto);

        // Buscar el cliente por el username
        Optional<ClientEntity> existingClient = clientJpaRepository.findByUsername(requestDtoToModel.getUsername());

        // Si no existe, devolver false
        if (existingClient.isEmpty()) {
            return false;
        }

        // Verificar la contraseña (ojo que estás comparando en orden incorrecto)
        if (!passwordEncoder.matches(clientRequestDto.password(), existingClient.get().getPassword())) {
            return false;  // Contraseña incorrecta
        }

        return true;  // Credenciales correctas
    }


}
