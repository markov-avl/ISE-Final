package ru.dvfu.dto.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ошибки, произошедшие при валидации полей")
public class InvalidRequestDto extends ApiErrorDto {

    @Schema(description = "Список ошибок с описанием")
    private List<InvalidRequestComponentDto> errors;

}
