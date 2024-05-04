package ru.dvfu.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.dvfu.dto.exception.InvalidRequestComponentDto;

import java.util.Map;

@UtilityClass
public class ValidationUtil {

    private final Map<String, String> ERROR_MESSAGE_TRANSLATIONS = Map.of(
            "Failed to convert property value of type", "Ошибка конвертации типов"
    );

    public InvalidRequestComponentDto toInvalidRequestComponentDto(ConstraintViolation<?> violation) {
        return new InvalidRequestComponentDto(extractFieldName(violation), finalizeErrorMessage(violation.getMessage()));
    }

    public InvalidRequestComponentDto toInvalidRequestComponentDto(ObjectError error) {
        return new InvalidRequestComponentDto(extractFieldName(error), finalizeErrorMessage(error.getDefaultMessage()));
    }

    public String extractFieldName(ConstraintViolation<?> violation) {
        String fieldName = violation.getPropertyPath().toString();
        for (Path.Node propertyPath : violation.getPropertyPath()) {
            fieldName = propertyPath.getName();
        }

        return fieldName;
    }

    public String extractFieldName(ObjectError error) {
        return ((FieldError) error).getField();
    }

    public String finalizeErrorMessage(String errorMessage) {
        String finalizedErrorMessage = ERROR_MESSAGE_TRANSLATIONS.entrySet().stream()
                .filter(e -> errorMessage.startsWith(e.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(errorMessage);

        return StringUtils.capitalize(finalizedErrorMessage);
    }

}
