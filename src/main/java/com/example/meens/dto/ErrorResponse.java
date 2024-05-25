package com.example.meens.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO ошибочных ситуаций.
 */
@Data
public class ErrorResponse {
    @NotNull
    @Schema(description = "Описание ошибки", example = "Произошла непредвиденная ошибка")
    private String error;
}
