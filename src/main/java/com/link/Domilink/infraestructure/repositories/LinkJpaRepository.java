package com.link.Domilink.infraestructure.repositories;

import com.link.Domilink.infraestructure.entities.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LinkJpaRepository extends JpaRepository<LinkEntity, Long> {
    @Query("SELECT l FROM LinkEntity l WHERE l.client.id = :id")
    List<LinkEntity> getByClientId(Long id);

    boolean existsByShortUrl(String shortUrl);

    Optional<LinkEntity> findByShortUrl(String shortUrl);
}
