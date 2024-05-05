package ru.dvfu.dto.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dvfu.util.FilterUtil;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Фильтрация по определенным полям")
public class MultiFilterParamsDto {

    @Schema(description = "Фильтры по игровому издателю", example = "Nintendo,Activision", nullable = true)
    private List<String> publishers = FilterUtil.DEFAULT_VALUES;

    @Schema(description = "Фильтры по игровым платформам", example = "XOne,PS3", nullable = true)
    private List<String> platforms = FilterUtil.DEFAULT_VALUES;

    @Schema(description = "Фильтры по игровым жанрам", example = "Action,Horror", nullable = true)
    private List<String> genres = FilterUtil.DEFAULT_VALUES;

    @Schema(description = "Фильтры по годам выхода игры", example = "2001,1999", nullable = true)
    private List<String> years = FilterUtil.DEFAULT_VALUES;

    @Schema(description = "Фильтры по регионам продаж", example = "JP,EU", nullable = true)
    private List<String> regions = FilterUtil.DEFAULT_VALUES;

}
