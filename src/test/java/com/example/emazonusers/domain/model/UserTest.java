package com.example.emazonusers.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        User user = new User(1L, "John", "Doe", 123456789L, "+571234567890",
                birthDate, "john.doe@example.com", "password123", 2L);

        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(123456789L, user.getDocumentId());
        assertEquals("+571234567890", user.getPhoneNumber());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(2L, user.getRoleId());

        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setEmail("jane.smith@example.com");

        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("jane.smith@example.com", user.getEmail());
    }
}
