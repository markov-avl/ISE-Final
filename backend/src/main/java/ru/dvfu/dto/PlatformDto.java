package ru.dvfu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "Игровая платформа")
public class PlatformDto {

    @Schema(description = "ID платформы", example = "1")
    private Long id;

    @Schema(description = "Название", example = "PS3")
    private String name;

}
