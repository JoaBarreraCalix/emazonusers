package com.example.emazonusers.infrastructure.out.jpa.repository;

import com.example.emazonusers.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByDocumentId(Long documentId); // Nueva implementación
}
