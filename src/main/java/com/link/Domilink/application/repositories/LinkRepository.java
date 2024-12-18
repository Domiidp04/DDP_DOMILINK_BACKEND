package com.link.Domilink.application.repositories;

import com.link.Domilink.domain.models.Link;
import com.link.Domilink.infraestructure.dtos.LinkRequestDto;
import com.link.Domilink.infraestructure.dtos.LinkResponseDto;
import com.link.Domilink.infraestructure.dtos.RedirectResponseDto;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public interface LinkRepository {

    List<LinkResponseDto> getAll();

    Optional<LinkResponseDto> getById(Long id);

    List<LinkResponseDto> getByClientId(Long id);

    LinkResponseDto createLink(Long clientId, LinkRequestDto linkRequestDto);

    void save(Link link);

    void delete(Long linkId);

    Optional<RedirectResponseDto> getByShortUrl(String shortUrl);
}
