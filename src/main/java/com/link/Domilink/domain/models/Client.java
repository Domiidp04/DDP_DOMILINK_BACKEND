package com.link.Domilink.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Client {

    private Long id;
    private String username;
    private String password;
    private List<Link> links;
}
