package com.example.meens.controller;

import com.example.meens.dto.GameInfoResponse;
import com.example.meens.dto.NewGameRequest;
import com.example.meens.dto.GameTurnRequest;
import com.example.meens.service.api.MinesweeperGameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import liquibase.pro.packaged.G;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер игровой логики
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://minesweeper-test.studiotg.ru")
@RequestMapping("/api")
public class MinesweeperController {

    private final MinesweeperGameService minesweeperGameService;

    /**
     * Метод отработки запроса.
     *
     * @param newGame запрос на создание игры.
     * @return ответ с информацией об игре {@link GameInfoResponse}
     */
    @Operation(description = "Начало новой игры")
    @PostMapping("/new")
    public GameInfoResponse startNewGame(@RequestBody NewGameRequest newGame) {
        log.info("Received POST request ", newGame);
        return minesweeperGameService.startNewGame(newGame);
    }

    /**
     * Метод отработки запроса.
     *
     * @param GameTurnRequest запрос на совершение хода.
     * @return ответ с информацией об игре {@link GameInfoResponse}
     */
    @Operation(description = "Ход пользователя")
    @PostMapping("/turn")
    public GameInfoResponse makeTurn(@RequestBody GameTurnRequest GameTurnRequest) {
        log.info("Received POST request ", GameTurnRequest);
        return minesweeperGameService.makeTurn(GameTurnRequest);
    }
}
