package ru.dvfu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "Пагинация")
public class PageDto<T> {

    @Schema(description = "Номер текущей страницы", example = "3")
    private Integer page;

    @Schema(description = "Количество элементов", example = "40")
    private Integer size;

    @Schema(description = "Количество страниц всего", example = "15")
    private Integer totalPages;

    @Schema(description = "Количество элементов всего", example = "203")
    private Long totalElements;

    @Schema(description = "Данные страницы")
    private List<T> data = Collections.emptyList();

}
