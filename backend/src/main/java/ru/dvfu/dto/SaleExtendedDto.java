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
@Schema(description = "Продажи")
public class SaleExtendedDto {

    @Schema(description = "ID продаж", example = "1")
    private Long id;

    @Schema(description = "Название игры", example = "Dark Souls")
    private String name;

    @Schema(description = "Год издания игры на соответствующей игровой платформе", example = "2010")
    private Integer year;

    @Schema(description = "Название игровой платформы", example = "PS3")
    private String platform;

    @Schema(description = "Название игрового жанра", example = "Action")
    private String genre;

    @Schema(description = "Название игрового издателя", example = "Nintendo")
    private String publisher;

    @Schema(description = "Регион продаж", example = "JP")
    private String region;

    @Schema(description = "Число продаж (в млн.)", example = "0.3")
    private Double numberOfSales;

}
