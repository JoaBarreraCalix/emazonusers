package com.example.emazonusers.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidUserRequest() {
        UserRequest userRequest = new UserRequest(
                "John",
                "Doe",
                123456789L,
                "+571234567890",
                LocalDate.of(1990, 1, 1),
                "john.doe@example.com",
                "password123",
                2L
        );
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertTrue(violations.isEmpty(), "No debería haber violaciones con un UserRequest válido");
    }

    @Test
    void testInvalidEmail() {
        UserRequest userRequest = new UserRequest(
                "John",
                "Doe",
                123456789L,
                "+571234567890",
                LocalDate.of(1990, 1, 1),
                "invalid-email",
                "password123",
                2L
        );
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertFalse(violations.isEmpty(), "Debería haber una violación de email inválido");
    }

    @Test
    void testInvalidPhoneNumber() {
        UserRequest userRequest = new UserRequest(
                "John",
                "Doe",
                123456789L,
                "1234567890", // Sin el +57
                LocalDate.of(1990, 1, 1),
                "john.doe@example.com",
                "password123",
                2L
        );
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertFalse(violations.isEmpty(), "Debería haber una violación por número de teléfono inválido");
    }
}
