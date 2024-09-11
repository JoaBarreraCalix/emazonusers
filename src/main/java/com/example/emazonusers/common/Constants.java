package com.example.emazonusers.common;

import io.jsonwebtoken.SignatureAlgorithm;

public class Constants {
    public static final String USER_NOT_NULL_FIRST_NAME = "El nombre no puede ser nulo";
    public static final String USER_NOT_NULL_LAST_NAME = "El apellido no puede ser nulo";
    public static final String USER_NOT_NULL_DOCUMENT_ID = "El documento de identidad no puede ser nulo";
    public static final String USER_NOT_NULL_PHONE_NUMBER = "El número de teléfono no puede ser nulo";
    public static final String USER_INVALID_PHONE_NUMBER = "El número de teléfono debe empezar con +57 y tener 10 dígitos";
    public static final String USER_NOT_NULL_BIRTHDATE = "La fecha de nacimiento no puede ser nula";
    public static final String USER_NOT_NULL_EMAIL = "El correo no puede ser nulo";
    public static final String USER_INVALID_EMAIL = "El correo es inválido";
    public static final String USER_NOT_NULL_PASSWORD = "La contraseña no puede ser nula";
    public static final String USER_MIN_PASSWORD_LENGTH = "La contraseña debe tener al menos 8 caracteres";
    public static final String USER_NOT_NULL_ROLE_ID = "El rol no puede ser nulo";
    public static final String USER_NOT_ADULT = "El usuario debe ser mayor de edad (18 años o más)";

    public static final long TOKEN_EXPIRATION_TIME_IN_MILLI = 600000;
    public static final String TOKEN_CLAIM_ROLE = "role";
    public static final String TOKEN_SECRET_KEY = "byu45h90tg7qwcphuo234t-80NUOPG34H790345YKO[]asxnuio2345g90hwefp[";
    public static final String LOGIN_USER_NOT_FOUND = "User not found in database";
    public static final String LOGIN_AUTH_HEADER = "Authorization";
    public static final String TOKEN_IS_EXPIRED = "Token is expired";
    public static final String TOKEN_IS_INVALID = "Token is invalid";
    public static final String  TOKEN_GENERAL_ERROR = "There is an error with the token";
    public static final String LOGIN_AUTH_HEADER_START = "Bearer ";
    public static final String LOGIN_ROLE_NOT_FOUND = "Role not found";
    public static final String AUX_BODEGA_RESTRAINT = "ADMIN";
    public static final String USER_DUP_EMAIL = "El correo ya está en uso";
    public static final String USER_DUP_DOCID = "El documento de identificación ya está en uso";
    public static final String USER_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String USER_NUMBER_REGEX = "^\\+57\\d{10}$";
    public static final int LOGIN_MAX_TRIES = 5;
    public static final String LOGIN_MAX_TRIES_ERROR = "Login has exceed the max number of attemps";
    public static final int LOGIN_START_TRIES = 0;
    public static final long LOGIN_LOCKED_TIME = 15;
    public static final int LOGIN_ATTEMPT_ADDEED = 1;

    //DOCUMENTATION
}
