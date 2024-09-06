package com.example.emazonusers.infrastructure.in.rest;

import com.example.emazonusers.application.dto.UserRequest;
import com.example.emazonusers.application.handler.IUserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

class UserRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IUserHandler userHandler;

    @InjectMocks
    private UserRestController userRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    void createAuxBodega_successful() throws Exception {
        UserRequest userRequest = new UserRequest("John", "Doe", 123456789L, "+571234567890",
                null, "john.doe@example.com", "password123", 2L);

        String json = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"documentId\": 123456789, \"phoneNumber\": \"+571234567890\", \"birthDate\": \"1990-01-01\", \"email\": \"john.doe@example.com\", \"password\": \"password123\", \"roleId\": 2 }";

        mockMvc.perform(post("/api/users/aux_bodega")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(userHandler, times(1)).registerUser(any(UserRequest.class));
    }
}
