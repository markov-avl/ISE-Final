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
@Schema(description = "Вышедшая игра")
public class ReleasedGameDto {

    @Schema(description = "ID продаж", example = "1")
    private Long id;

    @Schema(description = "ID игры", example = "1")
    private Long gameId;

    @Schema(description = "ID игровой платформы", example = "1")
    private Long platformId;

    @Schema(description = "Год релиза", example = "2008")
    private Integer year;

}
