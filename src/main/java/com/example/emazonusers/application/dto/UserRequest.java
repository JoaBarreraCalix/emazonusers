//application.dto.UserRequest
package com.example.emazonusers.application.dto;

import com.example.emazonusers.common.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Schema(description = "First name of the user", example = "John")
    @NotEmpty(message = Constants.USER_NOT_NULL_FIRST_NAME)
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    @NotEmpty(message = Constants.USER_NOT_NULL_LAST_NAME)
    private String lastName;

    @Schema(description = "Document ID of the user", example = "123456789")
    @NotNull(message = Constants.USER_NOT_NULL_DOCUMENT_ID)
    private Long documentId;

    @Schema(description = "Phone number of the user", example = "+571234567890")
    @NotEmpty(message = Constants.USER_NOT_NULL_PHONE_NUMBER)
    @Pattern(regexp = "^\\+57\\d{10}$", message = Constants.USER_INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @Schema(description = "Birthdate of the user", example = "1990-01-01")
    @NotNull(message = Constants.USER_NOT_NULL_BIRTHDATE)
    private LocalDate birthDate;

    @Schema(description = "Email of the user", example = "john.doe@example.com")
    @NotEmpty(message = Constants.USER_NOT_NULL_EMAIL)
    @Email(message = Constants.USER_INVALID_EMAIL)
    private String email;

    @Schema(description = "Password of the user", example = "password123")
    @NotEmpty(message = Constants.USER_NOT_NULL_PASSWORD)
    @Size(min = 8, message = Constants.USER_MIN_PASSWORD_LENGTH)
    private String password;

    @Schema(description = "Role ID of the user", example = "2")
    @NotNull(message = Constants.USER_NOT_NULL_ROLE_ID)
    private Long roleId;
}