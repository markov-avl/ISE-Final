package ru.dvfu.handler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.dvfu.dto.exception.ApiErrorDto;
import ru.dvfu.dto.exception.InvalidRequestComponentDto;
import ru.dvfu.dto.exception.InvalidRequestDto;
import ru.dvfu.exception.http.ApiException;
import ru.dvfu.util.ValidationUtil;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String SERVER_ERROR = "Произошла ошибка на сервере";

    private static final String SOME_FIELDS_ARE_INVALID = "Некоторые компоненты не прошли валидацию";

    private static final String INVALID_REQUEST_BODY = "Невалидный формат тела запроса";

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorDto> handleApiException(ApiException exception) {
        if (exception.getHttpStatus().is5xxServerError()) {
            log.error("ApiException {}: {}", exception.getHttpStatus(), exception.getMessage());

            return new ResponseEntity<>(new ApiErrorDto(SERVER_ERROR), exception.getHttpStatus());
        }

        log.debug("ApiException {}: {}", exception.getHttpStatus(), exception.getMessage());

        return new ResponseEntity<>(new ApiErrorDto(exception.getMessage()), exception.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<InvalidRequestDto> handleConstraintViolationException(ConstraintViolationException exception) {
        List<InvalidRequestComponentDto> errors = exception.getConstraintViolations().stream()
                .map(ValidationUtil::toInvalidRequestComponentDto)
                .toList();
        InvalidRequestDto invalidRequestDto = new InvalidRequestDto(errors);
        invalidRequestDto.setMessage(SOME_FIELDS_ARE_INVALID);

        return ResponseEntity.badRequest().body(invalidRequestDto);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<InvalidRequestDto> handleBindException(BindException exception) {
        List<InvalidRequestComponentDto> errors = exception.getBindingResult().getAllErrors().stream()
                .map(ValidationUtil::toInvalidRequestComponentDto)
                .toList();
        InvalidRequestDto invalidRequestDto = new InvalidRequestDto(errors);
        invalidRequestDto.setMessage(SOME_FIELDS_ARE_INVALID);

        return ResponseEntity.badRequest().body(invalidRequestDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<InvalidRequestDto> handleHttpMessageNotReadableException() {
        InvalidRequestComponentDto error = new InvalidRequestComponentDto("body", INVALID_REQUEST_BODY);
        InvalidRequestDto invalidRequestDto = new InvalidRequestDto(List.of(error));
        invalidRequestDto.setMessage(SOME_FIELDS_ARE_INVALID);

        return ResponseEntity.badRequest().body(invalidRequestDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException() {
        return ResponseEntity.notFound().build();
    }

}
