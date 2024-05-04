package ru.dvfu.dto.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import ru.dvfu.util.SortUtil;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сортировка")
public class SortParamsDto {

    @Schema(description = "Направление сортировки", example = "ASC", nullable = true)
    private Sort.Direction sortDirection = SortUtil.DEFAULT_DIRECTION;

    @Schema(description = "Поле, по которому сортируются данные", example = SortUtil.DEFAULT_SORT_BY, nullable = true)
    private String sortBy = SortUtil.DEFAULT_SORT_BY;

}
