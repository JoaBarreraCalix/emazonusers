//domain.usecase.UserUseCase
package com.example.emazonusers.domain.usecase;

import com.example.emazonusers.domain.api.IUserServicePort;
import com.example.emazonusers.domain.model.User;
import com.example.emazonusers.domain.spi.IUserPersistencePort;

import java.util.Optional;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void registerUser(User user) {
        validateUser(user);

        Optional<User> existingUserByEmail = userPersistencePort.findUserByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new IllegalArgumentException("El correo ya está en uso");
        }

        Optional<User> existingUserByDocument = userPersistencePort.findUserByDocumentId(user.getDocumentId());
        if (existingUserByDocument.isPresent()) {
            throw new IllegalArgumentException("El documento de identificación ya está en uso");
        }

        userPersistencePort.registerUser(user);
    }

    private void validateUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo");
        }
        if (user.getEmail() == null || !isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("El correo es inválido o nulo");
        }
        if (user.getPhoneNumber() == null || !isValidPhoneNumber(user.getPhoneNumber())) {
            throw new IllegalArgumentException("El número de teléfono debe empezar con +57 y tener 10 dígitos");
        }
        if (user.getDocumentId() == null) {
            throw new IllegalArgumentException("El ID del documento no puede ser nulo");
        }
        if (user.getBirthDate() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phonePattern = "^\\+57\\d{10}$";
        return phoneNumber.matches(phonePattern);
    }
}