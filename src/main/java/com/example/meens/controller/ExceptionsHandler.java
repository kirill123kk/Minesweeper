package com.example.meens.controller;

import com.example.meens.dto.ErrorResponse;
import com.example.meens.excepion.MinesweeperException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Контроллер обработки ошибок.
 */
@RestControllerAdvice
public class ExceptionsHandler {
    /**
     * Метод обработки {@link MinesweeperException} ошибок.
     *
     * @param e ошибка.
     * @return ответ с информацией об ошибке {@link  ErrorResponse}.
     */
    @ExceptionHandler(MinesweeperException.class)
    public ResponseEntity<ErrorResponse> handleException(MinesweeperException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
