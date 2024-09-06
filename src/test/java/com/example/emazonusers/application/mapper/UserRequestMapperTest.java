package com.example.emazonusers.application.mapper;

import com.example.emazonusers.application.dto.UserRequest;
import com.example.emazonusers.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestMapperTest {

    private UserRequestMapper userRequestMapper = Mappers.getMapper(UserRequestMapper.class);

    @Test
    void testToUser() {
        UserRequest userRequest = new UserRequest("John", "Doe", 123456789L, "+571234567890",
                LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", 2L);
        User user = userRequestMapper.toUser(userRequest);

        assertEquals(userRequest.getFirstName(), user.getFirstName());
        assertEquals(userRequest.getLastName(), user.getLastName());
        assertEquals(userRequest.getDocumentId(), user.getDocumentId());
        assertEquals(userRequest.getPhoneNumber(), user.getPhoneNumber());
        assertEquals(userRequest.getBirthDate(), user.getBirthDate());
        assertEquals(userRequest.getEmail(), user.getEmail());
        assertEquals(userRequest.getPassword(), user.getPassword());
        assertEquals(userRequest.getRoleId(), user.getRoleId());
    }

    @Test
    void testToUserRequest() {
        User user = new User(1L, "John", "Doe", 123456789L, "+571234567890",
                LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", 2L);
        UserRequest userRequest = userRequestMapper.toUserRequest(user);

        assertEquals(user.getFirstName(), userRequest.getFirstName());
        assertEquals(user.getLastName(), userRequest.getLastName());
        assertEquals(user.getDocumentId(), userRequest.getDocumentId());
        assertEquals(user.getPhoneNumber(), userRequest.getPhoneNumber());
        assertEquals(user.getBirthDate(), userRequest.getBirthDate());
        assertEquals(user.getEmail(), userRequest.getEmail());
        assertEquals(user.getPassword(), userRequest.getPassword());
        assertEquals(user.getRoleId(), userRequest.getRoleId());
    }
}
