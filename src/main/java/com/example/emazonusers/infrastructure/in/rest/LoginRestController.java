package com.example.emazonusers.infrastructure.in.rest;

import com.example.emazonusers.common.Constants;
import com.example.emazonusers.infrastructure.security.JwtUtil;
import com.example.emazonusers.infrastructure.security.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for authentication")
public class LoginRestController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Operation(summary = "Login user",
            description = "Authenticates a user by email and password and returns a JWT token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully authenticated",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - invalid credentials", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
            })
    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch (Exception e) {
            userDetailsServiceImpl.onLoginFailure(email);
            throw e;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(Constants.LOGIN_ROLE_NOT_FOUND))
                .getAuthority();

        userDetailsServiceImpl.onLoginSuccess(email);
        return jwtUtil.generateToken(userDetails.getUsername(), role);
    }
}
