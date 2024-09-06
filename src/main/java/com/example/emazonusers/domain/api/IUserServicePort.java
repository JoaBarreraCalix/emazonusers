//domain.api.IUserServicePort
package com.example.emazonusers.domain.api;

import com.example.emazonusers.domain.model.User;

public interface IUserServicePort {
    void registerUser(User user);
}
