package com.example.emazonusers.infrastructure.in.rest;

import com.example.emazonusers.application.dto.UserRequest;
import com.example.emazonusers.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;


    @Operation(summary = "Create an auxiliary warehouse user",
            description = "Creates a new user with the aux_bodega role",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Auxiliary user created successfully"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
            })
    @PostMapping("/aux_bodega")
    @ResponseStatus(HttpStatus.OK)
    public void createAuxBodega(@Valid @RequestBody UserRequest userRequest) {
        userHandler.registerUser(userRequest);
    }
}
