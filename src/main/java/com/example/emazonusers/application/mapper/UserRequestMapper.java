package com.example.emazonusers.application.mapper;

import com.example.emazonusers.application.dto.UserRequest;
import com.example.emazonusers.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(UserRequest userRequest);
}
