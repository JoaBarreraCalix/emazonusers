package com.example.emazonusers.domain.usecase;

import com.example.emazonusers.domain.api.IUserServicePort;
import com.example.emazonusers.domain.model.User;
import com.example.emazonusers.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.util.Optional;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public void registerUser(User user) {
        validateUser(user);

        Optional<User>  existingUserByEmail = userPersistencePort.findUserByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        Optional<User> existingUserByDocument = userPersistencePort.findUserByDocumentId(user.getDocumentId());
        if (existingUserByDocument.isPresent()) {
            throw new IllegalArgumentException("User with id " + user.getDocumentId() + " already exists");
        }
    }

    public void validateUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name must not be empty");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name must not be empty");
        }
        if (user.getEmail() == null || !user.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getPhoneNumber() == null || !user.getPhoneNumber().matches("\\\\+?[0-9]{10,13}")) {
            throw new IllegalArgumentException("Phone number must not be null");
        }
        if (user.getDocumentId() == null) {
            throw new IllegalArgumentException("Identity document must not be null");
        }
        if (user.getBirthDate() == null || user.getBirthDate().plusYears(18).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
    }
}
