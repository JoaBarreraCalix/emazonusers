package com.example.emazonusers.domain.spi;

import com.example.emazonusers.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {

    void saveUser(User user);
    Optional<User> findUserByDocumentId(Long documentId);
    Optional<User> findUserByEmail(String email);

}
