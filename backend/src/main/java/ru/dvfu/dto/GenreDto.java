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
@Schema(description = "Игровой жанр")
public class GenreDto {

    @Schema(description = "ID жанра", example = "1")
    private Long id;

    @Schema(description = "Название", example = "Action")
    private String name;

}
