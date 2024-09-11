package com.example.emazonusers.infrastructure.out.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "login_attempts")
public class LoginAttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private int attempts;
    @Column(name = "last_attempt",nullable = false)
    private LocalDateTime lastAttempt;
    @Column(name = "locked_until")
    private LocalDateTime lockedUntil;
}
