package com.example.emazonusers.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testRoleGettersAndSetters() {
        Role role = new Role(1L, "ADMIN", "Administrator role");

        assertEquals(1L, role.getId());
        assertEquals("ADMIN", role.getName());
        assertEquals("Administrator role", role.getDescription());

        role.setId(2L);
        role.setName("USER");
        role.setDescription("User role");

        assertEquals(2L, role.getId());
        assertEquals("USER", role.getName());
        assertEquals("User role", role.getDescription());
    }
}
