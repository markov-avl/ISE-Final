package ru.dvfu.dto.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.SortedMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Многоуровневая ортировка")
public class MultiLevelSortParamsDto {

    @Schema(description = "Поле и направление сортировки", example = "genre=asc", nullable = true)
    SortedMap<String, Sort.Direction> sort = Collections.emptySortedMap();

}
