package com.link.Domilink.infraestructure.configuration;

import com.link.Domilink.application.repositories.ClientRepository;
import com.link.Domilink.application.repositories.LinkRepository;
import com.link.Domilink.application.services.ClientService;
import com.link.Domilink.application.services.LinkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.link.Domilink")
public class BeanConfiguration {

    @Bean
    public ClientService clientService(final ClientRepository clientRepository) {
        return new ClientService(clientRepository);
    }

    @Bean
    public LinkService linkService(final LinkRepository linkRepository) {
        return new LinkService(linkRepository);
    }
}
