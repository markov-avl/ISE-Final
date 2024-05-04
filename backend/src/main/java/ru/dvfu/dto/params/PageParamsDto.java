package ru.dvfu.dto.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dvfu.util.PageUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Параметры пагинации")
public class PageParamsDto {

    @Schema(description = "Номер страницы", example = "" + PageUtil.MIN_PAGE, nullable = true)
    @Min(value = PageUtil.MIN_PAGE, message = PageUtil.PAGE_ERROR)
    private Integer page = PageUtil.MIN_PAGE;

    @Schema(description = "Размер страницы (количество элементов)", example = "" + PageUtil.MAX_SIZE, nullable = true)
    @Min(value = PageUtil.MIN_SIZE, message = PageUtil.SIZE_ERROR)
    private Integer size = PageUtil.MAX_SIZE;

}
