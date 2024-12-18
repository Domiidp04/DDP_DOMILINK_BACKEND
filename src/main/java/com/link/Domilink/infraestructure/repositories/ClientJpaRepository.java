package com.link.Domilink.infraestructure.repositories;

import com.link.Domilink.infraestructure.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByUsername(String username);

    @Query("SELECT c.id FROM ClientEntity c WHERE c.username = :username")
    Long getClientIdByUsername(String username);

    Optional<ClientEntity> findById(Long id);

}
