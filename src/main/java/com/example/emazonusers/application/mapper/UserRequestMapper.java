//application.mapper.UserRequestMapper
package com.example.emazonusers.application.mapper;

import com.example.emazonusers.application.dto.UserRequest;
import com.example.emazonusers.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    @Mapping(target = "password", source = "password")
    User toUser(UserRequest userRequest);
    UserRequest toUserRequest(User user);
}