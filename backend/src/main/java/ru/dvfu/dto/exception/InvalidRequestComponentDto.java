package ru.dvfu.dto.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Компонент запроса, не прошедший валидацию")
public class InvalidRequestComponentDto {

    @Schema(description = "Название компонента", example = "username")
    private String component;

    @Schema(description = "Информация об ошибке", example = "Имя пользователя обязательно")
    private String message;

}
