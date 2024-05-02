package ru.dvfu.exception.http;

import org.springframework.http.HttpStatus;

public class UnprocessableEntity extends ApiException {

    public UnprocessableEntity(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

    public UnprocessableEntity(String message, Throwable cause) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message, cause);
    }

}
