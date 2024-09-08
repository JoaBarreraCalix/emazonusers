package com.example.emazonusers.infrastructure.out.jpa.entity;

import com.example.emazonusers.common.Constants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = Constants.ROL_DB_NAME)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
}
