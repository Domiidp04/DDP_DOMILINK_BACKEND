package com.link.Domilink.domain.models;

import lombok.Data;

@Data
public class Link {

    private Long id;
    private String shortUrl;
    private String originalUrl;
    private Long clientId;
    private Client client;
}
