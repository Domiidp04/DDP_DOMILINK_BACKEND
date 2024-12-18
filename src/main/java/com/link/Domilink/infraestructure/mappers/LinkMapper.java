package com.link.Domilink.infraestructure.mappers;

import com.link.Domilink.domain.models.Link;
import com.link.Domilink.infraestructure.dtos.LinkResponseDto;
import com.link.Domilink.infraestructure.entities.LinkEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LinkMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "shortUrl", source = "shortUrl"),
            @Mapping(target = "originalUrl", source = "originalUrl"),
            @Mapping(target = "client", ignore = true)  // Ignoramos el cliente aquí para evitar el ciclo
    })
    Link entityToModel(LinkEntity linkEntity);

    List<Link> listEntityToModel(List<LinkEntity> linksEntity);

    @InheritInverseConfiguration
    @Mapping(target = "client", ignore = true)
        // Ignoramos el cliente aquí también
    LinkEntity modelToEntity(Link link);

    List<LinkEntity> listModelToEntity(List<Link> links);

    // Métodos para convertir a DTO
    LinkResponseDto modelToDto(Link link);

    List<LinkResponseDto> listModelToDto(List<Link> links);

    @InheritInverseConfiguration
    Link dtoToModel(LinkResponseDto linkDto);

    List<Link> listDtoToModel(List<LinkResponseDto> linkDtos);
}
