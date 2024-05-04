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
public class SaleDto {

    @Schema(description = "ID продаж", example = "1")
    private Long id;

    @Schema(description = "ID вышедшей игры", example = "1")
    private Long releasedGameId;

    @Schema(description = "Регион продаж", example = "JP")
    private String region;

    @Schema(description = "Число продаж (в млн.)", example = "0.3")
    private Double numberOfSales;

}
