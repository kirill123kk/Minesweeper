package com.example.meens.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GameInfoResponse {
    @JsonProperty("game_id")
    @Schema(description = "индентификатор игры", example = "01234567-89AB-CDEF-0123-456789ABCDEF")
    @NotNull
    private String gameId;

    @NotNull
    private Integer width;


    @NotNull
    private Boolean completed;

    @NotNull
    private Integer height;

    private String[][] field;

    @NotNull
    private Integer mines;
}