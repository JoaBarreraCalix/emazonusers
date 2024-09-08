package com.example.emazonusers.infrastructure.in.rest;

import com.example.emazonusers.application.dto.UserRequest;
import com.example.emazonusers.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping("/aux_bodega")
    public void createAuxBodega(@RequestBody UserRequest userRequest) {
        userHandler.registerUser(userRequest);
    }
}
