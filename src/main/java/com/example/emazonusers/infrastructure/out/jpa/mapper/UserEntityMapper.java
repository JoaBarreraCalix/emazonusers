package com.example.emazonusers.infrastructure.out.jpa.mapper;

import com.example.emazonusers.common.Constants;
import com.example.emazonusers.domain.model.User;
import com.example.emazonusers.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    @Mapping(source = Constants.USER_DB_ROLE_ID, target = Constants.USER_OBJ_ROLE_ID)
    User toDomain(UserEntity userEntity);

    @Mapping(source = Constants.USER_OBJ_ROLE_ID, target = Constants.USER_DB_ROLE_ID)
    UserEntity toEntity(User user);
}