//domain.usecase.UserUseCase
package com.example.emazonusers.domain.usecase;

import com.example.emazonusers.common.Constants;
import com.example.emazonusers.domain.api.IUserServicePort;
import com.example.emazonusers.domain.model.User;
import com.example.emazonusers.domain.spi.IUserPersistencePort;
import org.apache.tomcat.util.bcel.Const;

import java.time.LocalDate;
import java.time.Period;
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
            throw new IllegalArgumentException(Constants.USER_DUP_EMAIL);
        }

        Optional<User> existingUserByDocument = userPersistencePort.findUserByDocumentId(user.getDocumentId());
        if (existingUserByDocument.isPresent()) {
            throw new IllegalArgumentException(Constants.USER_DUP_DOCID);
        }

        userPersistencePort.registerUser(user);
    }

    private void validateUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException(Constants.USER_NOT_NULL_FIRST_NAME);
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException(Constants.USER_NOT_NULL_LAST_NAME);
        }
        if (user.getEmail() == null || !isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException(Constants.USER_NOT_NULL_EMAIL);
        }
        if (user.getPhoneNumber() == null || !isValidPhoneNumber(user.getPhoneNumber())) {
            throw new IllegalArgumentException(Constants.USER_NOT_NULL_PHONE_NUMBER);
        }
        if (user.getDocumentId() == null) {
            throw new IllegalArgumentException(Constants.USER_NOT_NULL_DOCUMENT_ID);
        }
        if (user.getBirthDate() == null) {
            throw new IllegalArgumentException(Constants.USER_NOT_NULL_BIRTHDATE);
        }
        if (!isAdult(user.getBirthDate())) {
            throw new IllegalArgumentException(Constants.USER_NOT_ADULT);
        }
    }

    private boolean isAdult(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        return age >= 18;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = Constants.USER_EMAIL_REGEX;
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phonePattern = Constants.USER_NUMBER_REGEX;
        return phoneNumber.matches(phonePattern);
    }
}