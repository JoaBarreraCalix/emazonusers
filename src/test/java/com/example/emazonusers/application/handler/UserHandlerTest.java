package com.example.emazonusers.application.handler;

import com.example.emazonusers.application.dto.UserRequest;
import com.example.emazonusers.application.mapper.UserRequestMapper;
import com.example.emazonusers.domain.api.IUserServicePort;
import com.example.emazonusers.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class UserHandlerTest {

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private UserRequestMapper userRequestMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserHandler userHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Dado
        UserRequest userRequest = new UserRequest("John", "Doe", 123456789L, "+571234567890",
                LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", 2L);
        User mappedUser = new User(1L, "John", "Doe", 123456789L, "+571234567890", LocalDate.of(1990, 1, 1),
                "john.doe@example.com", "password123", 2L);

        when(userRequestMapper.toUser(userRequest)).thenReturn(mappedUser);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        // Cuando
        userHandler.registerUser(userRequest);

        // Entonces
        verify(userRequestMapper, times(1)).toUser(userRequest);
        verify(passwordEncoder, times(1)).encode("password123");
        verify(userServicePort, times(1)).registerUser(mappedUser);
    }
}
