package com.example.meens.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GameTurnRequest {
    @JsonProperty("game_id")
    @Schema(description = "индентификатор игры",example = "01234567-89AB-CDEF-0123-456789ABCDEF")
    private String gameId;

    @NotNull
    private Integer col;

    @NotNull
    private Integer row;
}
