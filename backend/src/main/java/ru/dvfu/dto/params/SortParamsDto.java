package ru.dvfu.dto.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Многоуровневая сортировка")
public class SortParamsDto {

    @Schema(description = "Направление сортировки и поле", example = "asc-genre,desc-publisher", nullable = true)
    private List<String> sort = Collections.emptyList();

}
