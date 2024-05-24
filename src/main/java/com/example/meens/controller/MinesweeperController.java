package com.example.meens.controller;

import com.example.meens.dto.ErrorResponse;
import com.example.meens.dto.GameInfoResponse;
import com.example.meens.dto.NewGameRequest;
import com.example.meens.dto.GameTurnRequest;
import com.example.meens.excepion.MinesweeperException;
import com.example.meens.service.api.MinesweeperGameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер REST API
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://minesweeper-test.studiotg.ru")
@RequestMapping("/api")
public class MinesweeperController {

    private final MinesweeperGameService minesweeperGameService;

    @Operation(description = "Начало новой игры")
    @PostMapping("/new")
    public ResponseEntity<?> startNewGame(@RequestBody NewGameRequest newGame) {

        log.info("Received POST request ", newGame);
        try {
            return new ResponseEntity<>(minesweeperGameService.startNewGame(newGame),HttpStatus.CREATED);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError(e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(description = "Ход пользователя")
    @PostMapping("/turn")
    public  ResponseEntity<?> makeTurn(@RequestBody GameTurnRequest turn) throws JsonProcessingException {

        log.info("Received POST request ", turn);
        try {
            return new ResponseEntity<>(minesweeperGameService.makeTurn(turn),HttpStatus.OK);
        }catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError(e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }

    }
}
