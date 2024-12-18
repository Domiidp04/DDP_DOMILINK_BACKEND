package com.link.Domilink.application.services;

import com.link.Domilink.application.repositories.LinkRepository;
import com.link.Domilink.infraestructure.dtos.LinkRequestDto;
import com.link.Domilink.infraestructure.dtos.LinkResponseDto;
import com.link.Domilink.infraestructure.dtos.RedirectResponseDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;

    public List<LinkResponseDto> getAll() {
        return linkRepository.getAll();
    }

    public Optional<LinkResponseDto> getById(Long id) {
        return linkRepository.getById(id);
    }

    public List<LinkResponseDto> getByClientId(Long id) {
        return linkRepository.getByClientId(id);
    }

    public LinkResponseDto createLink(Long clientId, LinkRequestDto linkRequestDto) {
        return linkRepository.createLink(clientId, linkRequestDto);
    }

    public void delete(Long linkId){ linkRepository.delete(linkId); }

    public Optional<RedirectResponseDto> getByShortUrl(String shortUrl) {
        return linkRepository.getByShortUrl(shortUrl);
    }
}
