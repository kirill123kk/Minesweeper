package com.example.meens.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class NewGameRequest {
    @NotNull
    private Integer width;

    @NotNull
    private Integer height;

    @JsonProperty("mines_count")
    @NotNull
    private  Integer minesCount;
}
