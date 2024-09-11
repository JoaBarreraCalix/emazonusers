package com.example.emazonusers.infrastructure.out.jpa.repository;

import com.example.emazonusers.infrastructure.out.jpa.entity.LoginAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginAttemptRepository extends JpaRepository<LoginAttemptEntity, Long> {
    Optional<LoginAttemptEntity> findByEmail(String email);
}
