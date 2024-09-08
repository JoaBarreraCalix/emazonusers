package com.example.emazonusers.infrastructure.in.rest;

import com.example.emazonusers.common.Constants;
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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));


        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(Constants.LOGIN_ROLE_NOT_FOUND))
                .getAuthority();

        return jwtUtil.generateToken(userDetails.getUsername(), role);
    }
}
