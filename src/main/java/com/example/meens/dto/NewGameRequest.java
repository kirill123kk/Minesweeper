package com.example.meens.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO c информацией о новой игре.
 */
@Data
public class NewGameRequest {
    @NotNull
    @Max(value = 30)
    @Schema(description = "Ширина игрового поля", example = "10")
    private Integer width;

    @NotNull
    @Max(value = 30)
    @Schema(description = "Высота игрового поля", example = "10")
    private Integer height;

    @JsonProperty("mines_count")
    @NotNull
    @Schema(description = "Количество мин на поле", example = "10")
    private Integer minesCount;
}
