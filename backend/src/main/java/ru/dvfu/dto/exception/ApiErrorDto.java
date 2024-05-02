package ru.dvfu.dto.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сообщение об ошибке во время обработки запроса")
public class ApiErrorDto {

    @Schema(description = "Описание ошибки")
    private String message;

}
