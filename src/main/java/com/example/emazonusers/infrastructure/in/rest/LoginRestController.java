package com.example.emazonusers.infrastructure.in.rest;

import com.example.emazonusers.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginRestController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password) {
        // Autenticar al usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        // Cargar los detalles del usuario
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Extraer el rol del usuario
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found"))
                .getAuthority().replace("ROLE_", ""); // Eliminar el prefijo "ROLE_"

        // Generar el token con el email y el rol
        return jwtUtil.generateToken(userDetails.getUsername(), role);
    }
}
