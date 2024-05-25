package com.example.meens.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO с информацией об игре.
 */
@Data
public class GameInfoResponse {
    @JsonProperty("game_id")
    @Schema(description = "индентификатор игры", example = "01234567-89AB-CDEF-0123-456789ABCDEF")
    @NotNull
    private String gameId;

    @NotNull
    @Schema(description = "Ширина игрового поля", example = "10")
    private Integer width;

    @NotNull
    @Schema(description = "Завершена ли игра", example = "false")
    private Boolean completed;

    @NotNull
    @Schema(description = "Высота игрового поля", example = "10")
    private Integer height;

    @Schema(description = "Строки минного поля (количество равно высоте height)", example = "10")
    private String[][] field;

    @NotNull
    @Schema(description = "Количество мин на поле", example = "10")
    private Integer mines;
}