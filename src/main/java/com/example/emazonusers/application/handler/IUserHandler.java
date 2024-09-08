//application.handler.IUserHandler
package com.example.emazonusers.application.handler;

import com.example.emazonusers.application.dto.UserRequest;


public interface IUserHandler {
    void registerUser(UserRequest userRequest);
}