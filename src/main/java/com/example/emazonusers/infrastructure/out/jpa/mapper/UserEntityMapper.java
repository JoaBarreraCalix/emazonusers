package com.example.emazonusers.infrastructure.out.jpa.mapper;

import com.example.emazonusers.domain.model.User;
import com.example.emazonusers.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    @Mapping(source = "role.id", target = "roleId")
    User toDomain(UserEntity userEntity);

    @Mapping(source = "roleId", target = "role.id")
    UserEntity toEntity(User user);
}