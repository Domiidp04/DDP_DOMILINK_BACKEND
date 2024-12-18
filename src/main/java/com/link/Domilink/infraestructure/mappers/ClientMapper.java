package com.link.Domilink.infraestructure.mappers;

import com.link.Domilink.domain.models.Client;
import com.link.Domilink.infraestructure.dtos.ClientRequestDto;
import com.link.Domilink.infraestructure.dtos.ClientResponseDto;
import com.link.Domilink.infraestructure.entities.ClientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LinkMapper.class})
public interface ClientMapper {

    // Convierte ClientEntity a Client
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "links", source = "links")
    })
    Client entityToModel(ClientEntity clientEntity);

    // Convierte una lista de ClientEntity a una lista de Client
    List<Client> listEntityToModel(List<ClientEntity> clientsEntity);

    // Convierte Client a ClientEntity
    @InheritInverseConfiguration
    ClientEntity modelToEntity(Client client);

    // Convierte una lista de Client a una lista de ClientEntity
    List<ClientEntity> listModelToEntity(List<Client> clients);

    // Métodos para convertir a ClientResponseDto

    // Convierte Client a ClientResponseDto
    ClientResponseDto modelToResponseDto(Client client);

    // Convierte una lista de Client a una lista de ClientResponseDto
    List<ClientResponseDto> listModelToResponseDto(List<Client> clients);

    // Convierte ClientResponseDto a Client
    @InheritInverseConfiguration
    Client responseDtoToModel(ClientResponseDto clientDto);

    // Convierte una lista de ClientResponseDto a una lista de Client
    List<Client> listResponseDtoToModel(List<ClientResponseDto> clientDto);


    // Métodos para convertir a ClientRequestDto

    // Convierte Client a ClientRequestDto
    ClientRequestDto modelToRequestDto(Client client);

    // Convierte una lista de Client a una lista de ClientRequestDto
    List<ClientRequestDto> listModelToRequestDto(List<Client> clients);

    // Convierte ClientRequestDto a Client
    @InheritInverseConfiguration
    Client requestDtoToModel(ClientRequestDto clientDto);

    // Convierte una lista de ClientRequestDto a una lista de Client
    List<Client> listRequestDtoToModel(List<ClientRequestDto> clientDto);
}
