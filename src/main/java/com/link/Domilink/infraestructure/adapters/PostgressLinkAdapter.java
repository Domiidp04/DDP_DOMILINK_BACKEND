package com.link.Domilink.infraestructure.adapters;

import com.link.Domilink.application.repositories.LinkRepository;
import com.link.Domilink.domain.models.Link;
import com.link.Domilink.infraestructure.dtos.LinkRequestDto;
import com.link.Domilink.infraestructure.dtos.LinkResponseDto;
import com.link.Domilink.infraestructure.dtos.RedirectResponseDto;
import com.link.Domilink.infraestructure.entities.ClientEntity;
import com.link.Domilink.infraestructure.entities.LinkEntity;
import com.link.Domilink.infraestructure.mappers.ClientMapper;
import com.link.Domilink.infraestructure.mappers.LinkMapper;
import com.link.Domilink.infraestructure.repositories.ClientJpaRepository;
import com.link.Domilink.infraestructure.repositories.LinkJpaRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PostgressLinkAdapter implements LinkRepository {

    private final LinkJpaRepository linkJpaRepository;
    private final LinkMapper linkMapper;

    private final ClientJpaRepository clientJpaRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<LinkResponseDto> getAll() {
        List<Link> linkModel = linkMapper.listEntityToModel(linkJpaRepository.findAll());
        return linkMapper.listModelToDto(linkModel);
    }

    @Override
    public Optional<LinkResponseDto> getById(Long id) {
        Optional<Link> linkModel = linkJpaRepository.findById(id).map(linkMapper::entityToModel);
        return linkModel.map(linkMapper::modelToDto);
    }

    @Override
    public List<LinkResponseDto> getByClientId(Long id) {
        List<Link> linkModel = linkMapper.listEntityToModel(linkJpaRepository.getByClientId(id));
        return linkMapper.listModelToDto(linkModel);
    }

    @Override
    public void save(Link link) {
        linkJpaRepository.save(linkMapper.modelToEntity(link));
    }

    @Override
    @Transactional
    public void delete(Long linkId) {

        if (!linkJpaRepository.existsById(linkId)) {
            throw new IllegalArgumentException("Link not found");
        }

        linkJpaRepository.deleteById(linkId);
    }

    @Override
    public Optional<RedirectResponseDto> getByShortUrl(String shortUrl) {
        // Busca en el repositorio utilizando el método adecuado para obtener LinkEntity
        Optional<LinkEntity> linkEntityOpt = linkJpaRepository.findByShortUrl(shortUrl);

        // Si se encuentra, mapea a RedirectResponseDto; de lo contrario, devuelve un Optional vacío
        return linkEntityOpt.map(linkEntity -> new RedirectResponseDto(linkEntity.getOriginalUrl()));
    }

    @Override
    @Transactional
    public LinkResponseDto createLink(Long clientId, LinkRequestDto linkRequestDto) {

        Optional<ClientEntity> existingClient = clientJpaRepository.findById(clientId);

        if (existingClient.isEmpty()) {
            throw new IllegalArgumentException("Client not found");
        }

        String generatedShortUrl = generateShortUrl();

        // Comprobar si el shortUrl ya existe
        if (linkJpaRepository.existsByShortUrl(generatedShortUrl)) {
            throw new IllegalArgumentException("Short URL already exists");
        }

        // Crear la entidad LinkEntity a partir del DTO
        LinkEntity newLinkEntity = LinkEntity.builder()
                .originalUrl(linkRequestDto.originalUrl())
                .shortUrl(generatedShortUrl)
                .clientId(clientId)
                .build();

        // Guardar la entidad en la base de datos
        LinkEntity savedLinkEntity = linkJpaRepository.save(newLinkEntity);

        // Convertir la entidad guardada en un modelo de dominio
        Link savedLink = linkMapper.entityToModel(savedLinkEntity);

        // Convertir el modelo de dominio a un DTO de respuesta
        return linkMapper.modelToDto(savedLink);

    }

    public String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder shortUrl = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            shortUrl.append(characters.charAt(index));
        }

        return shortUrl.toString();
    }


}
