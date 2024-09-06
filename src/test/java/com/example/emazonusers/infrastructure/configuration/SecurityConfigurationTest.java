package com.example.emazonusers.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.emazonusers.application.dto.UserRequest;
import com.example.emazonusers.infrastructure.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SecurityConfigurationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void accessCreateAuxBodegaWithoutAuthentication() throws Exception {
        // Generar datos únicos
        long uniqueDocumentId = System.currentTimeMillis();
        String uniqueEmail = "test" + uniqueDocumentId + "@example.com";

        UserRequest userRequest = new UserRequest("John", "Doe", uniqueDocumentId, "+571234567890",
                LocalDate.of(1990, 1, 1), // Fecha de nacimiento válida
                uniqueEmail, "password123", 2L);

        mockMvc.perform(post("/api/users/aux_bodega")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userRequest))) // Convertir a JSON
                .andExpect(status().isForbidden()); // Usuario no autenticado
    }

    @Test
    void accessCreateAuxBodegaWithAdmin() throws Exception {
        // Generar datos únicos
        long uniqueDocumentId = System.currentTimeMillis();
        String uniqueEmail = "admin" + uniqueDocumentId + "@example.com";

        UserRequest userRequest = new UserRequest("John", "Doe", uniqueDocumentId, "+571234567890",
                LocalDate.of(1990, 1, 1), // Fecha de nacimiento válida
                uniqueEmail, "password123", 2L);

        // Generar el token con el rol ADMIN
        String jwtToken = jwtUtil.generateToken("admin@example.com", "ADMIN");

        mockMvc.perform(post("/api/users/aux_bodega")
                        .header("Authorization", "Bearer " + jwtToken)  // Pasa el token en la cabecera
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userRequest))) // Convertir a JSON
                .andExpect(status().isOk()); // Usuario autenticado con rol de ADMIN
    }

    @Test
    void accessCreateAuxBodegaWithNonAdmin() throws Exception {
        // Generar datos únicos
        long uniqueDocumentId = System.currentTimeMillis();
        String uniqueEmail = "nonadmin" + uniqueDocumentId + "@example.com";

        UserRequest userRequest = new UserRequest("John", "Doe", uniqueDocumentId, "+571234567890",
                LocalDate.of(1990, 1, 1), // Fecha de nacimiento válida
                uniqueEmail, "password123", 2L);

        // Generar el token con el rol USER
        String jwtToken = jwtUtil.generateToken("user@example.com", "USER");

        mockMvc.perform(post("/api/users/aux_bodega")
                        .header("Authorization", "Bearer " + jwtToken)  // Pasa el token en la cabecera
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userRequest))) // Convertir a JSON
                .andExpect(status().isForbidden()); // Usuario autenticado pero sin rol de ADMIN
    }
}
