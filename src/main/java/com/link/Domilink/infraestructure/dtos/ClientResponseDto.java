package com.link.Domilink.infraestructure.dtos;

import java.util.List;

public record ClientResponseDto(String username, String password, List<LinkResponseDto> links) {
}
