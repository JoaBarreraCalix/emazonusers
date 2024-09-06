package com.example.emazonusers.domain.usecase;

import com.example.emazonusers.domain.model.User;
import com.example.emazonusers.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_successful() {
        // Dado
        User user = new User(1L, "John", "Doe", 123456789L, "+571234567890",
                LocalDate.of(1990, 1, 1), "john.doe@example.com",
                "password123", 2L);

        when(userPersistencePort.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.findUserByDocumentId(user.getDocumentId())).thenReturn(Optional.empty());

        // Cuando
        userUseCase.registerUser(user);

        // Entonces
        verify(userPersistencePort, times(1)).registerUser(user);
    }

    @Test
    void registerUser_emailAlreadyExists() {
        // Dado
        User user = new User(1L, "John", "Doe", 123456789L, "+571234567890",
                LocalDate.of(1990, 1, 1), "john.doe@example.com",
                "password123", 2L);

        when(userPersistencePort.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // Cuando / Entonces
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userUseCase.registerUser(user);
        });

        assertEquals("El correo ya está en uso", exception.getMessage());
    }

    @Test
    void registerUser_documentIdAlreadyExists() {
        // Dado
        User user = new User(1L, "John", "Doe", 123456789L, "+571234567890",
                LocalDate.of(1990, 1, 1), "john.doe@example.com",
                "password123", 2L);

        when(userPersistencePort.findUserByDocumentId(user.getDocumentId())).thenReturn(Optional.of(user));

        // Cuando / Entonces
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userUseCase.registerUser(user);
        });

        assertEquals("El documento de identificación ya está en uso", exception.getMessage());
    }

    @Test
    void registerUser_invalidPhoneNumber() {
        // Dado
        User user = new User(1L, "John", "Doe", 123456789L, "1234567890",
                LocalDate.of(1990, 1, 1), "john.doe@example.com",
                "password123", 2L);

        // Cuando / Entonces
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userUseCase.registerUser(user);
        });

        assertEquals("El número de teléfono debe empezar con +57 y tener 10 dígitos", exception.getMessage());
    }

    @Test
    void registerUser_userUnderAge() {
        // Dado
        User user = new User(1L, "John", "Doe", 123456789L, "+571234567890",
                LocalDate.of(2010, 1, 1), "john.doe@example.com",
                "password123", 2L);

        // Cuando / Entonces
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userUseCase.registerUser(user);
        });

        assertEquals("El usuario debe ser mayor de edad (18 años o más)", exception.getMessage());
    }
}
