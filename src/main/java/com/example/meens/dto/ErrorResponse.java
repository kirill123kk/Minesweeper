package com.example.meens.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ErrorResponse {
    @NotNull
    String error;
}
