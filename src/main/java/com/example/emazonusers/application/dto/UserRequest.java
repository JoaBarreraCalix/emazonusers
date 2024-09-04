package com.example.emazonusers.application.dto;

import com.example.emazonusers.common.Constants;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.bcel.Const;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {
    @NotEmpty(message = Constants.USER_NAME_NOT_EMPTY)
    private String firstName;
    @NotEmpty(message = Constants.USER_LAST_NOT_EMPTY)
    private String lastName;
    @NotNull(message = Constants.USER_DOC_NOT_EMPTY)
    private Long documentId;
    @Pattern(regexp = Constants.USER_PHONE_NUMBER_REGEX, message = Constants.USER_PHONE_NOT_VALID)
    private String phoneNumber;
    @NotNull(message = Constants.USER_DATE_NOT_EMPTY)
    private LocalDate birthDate;
    @Email(message = Constants.USER_EMAIL_NOT_VALID)
    private String email;
    @NotEmpty(message = Constants.USER_PASS_NOT_EMPTY)
    @Size(min = Constants.USER_MIN_PASS_SIZE, message = Constants.USER_PASS_NOT_VALID)
    private String password;

}
