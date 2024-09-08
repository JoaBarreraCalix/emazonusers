//application.handler.UserHandler
package com.example.emazonusers.application.handler;

import com.example.emazonusers.application.dto.UserRequest;
import com.example.emazonusers.application.mapper.UserRequestMapper;
import com.example.emazonusers.domain.api.IUserServicePort;
import com.example.emazonusers.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRequest userRequest) {
        User user = userRequestMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userServicePort.registerUser(user);
    }
}
