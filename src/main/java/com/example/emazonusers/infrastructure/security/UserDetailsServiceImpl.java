package com.example.emazonusers.infrastructure.security;

import com.example.emazonusers.common.Constants;
import com.example.emazonusers.infrastructure.out.jpa.entity.LoginAttemptEntity;
import com.example.emazonusers.infrastructure.out.jpa.entity.UserEntity;
import com.example.emazonusers.infrastructure.out.jpa.repository.LoginAttemptRepository;
import com.example.emazonusers.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final LoginAttemptRepository loginAttemptRepository;
    private final JwtUtil jwtUtil;
    private final int MAX_ATTEMPTS= Constants.LOGIN_MAX_TRIES;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<LoginAttemptEntity> loginAttemptOpt = loginAttemptRepository.findByEmail(email);
        if (loginAttemptOpt.isPresent()) {

            LoginAttemptEntity loginAttempt = loginAttemptOpt.get();

            if(loginAttempt.getAttempts() >= MAX_ATTEMPTS) {
                if(loginAttempt.getLockedUntil() == null || loginAttempt.getLockedUntil().isAfter(LocalDateTime.now())) {
                    throw new LockedException(Constants.LOGIN_MAX_TRIES_ERROR);
                }else {
                    loginAttempt.setAttempts(Constants.LOGIN_START_TRIES);
                    loginAttempt.setLockedUntil(null);
                    loginAttemptRepository.save(loginAttempt);
                }
            }

        }
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.LOGIN_USER_NOT_FOUND));

        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userEntity.getRole().getName()));

        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(authorities)
                .build();
    }

    public void onLoginFailure(String email){
        Optional<LoginAttemptEntity> loginAttemptOpt = loginAttemptRepository.findByEmail(email);
        if (loginAttemptOpt.isPresent()) {
            LoginAttemptEntity loginAttempt = loginAttemptOpt.get();
            loginAttempt.setAttempts(loginAttempt.getAttempts() + Constants.LOGIN_ATTEMPT_ADDEED);
            loginAttempt.setLastAttempt(LocalDateTime.now());

            if(loginAttempt.getAttempts() >= MAX_ATTEMPTS) {
                loginAttempt.setLockedUntil(LocalDateTime.now().plusMinutes(Constants.LOGIN_LOCKED_TIME));
            }
            loginAttemptRepository.save(loginAttempt);
        }else {
            LoginAttemptEntity newLoginAttempt = new LoginAttemptEntity();
            newLoginAttempt.setEmail(email);
            newLoginAttempt.setAttempts(Constants.LOGIN_ATTEMPT_ADDEED);
            newLoginAttempt.setLastAttempt(LocalDateTime.now());
            loginAttemptRepository.save(newLoginAttempt);
        }
    }

    public void onLoginSuccess(String email){

        loginAttemptRepository.findByEmail(email).ifPresent(loginAttempt -> {
           loginAttempt.setAttempts(Constants.LOGIN_START_TRIES);
           loginAttempt.setLockedUntil(null);
           loginAttemptRepository.save(loginAttempt);

        });
    }


}
