package com.example.meens.service.api;

import com.example.meens.dto.GameInfoResponse;
import com.example.meens.dto.GameTurnRequest;
import com.example.meens.dto.NewGameRequest;
import com.example.meens.entity.GameEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Сервис логики запросов.
 */
public interface MinesweeperGameService {

    /**
     * Метод начало новой игры.
     *
     * @param newGame запрос на создание игры.
     * @return ответ с информацией об игре {@link GameInfoResponse}
     */
    GameInfoResponse startNewGame(NewGameRequest newGame);

    /**
     * Метод игового хода.
     *
     * @param gameTurnRequest запрос на совершение хода.
     * @return ответ с информацией об игре {@link GameInfoResponse}
     */
    GameInfoResponse makeTurn(GameTurnRequest gameTurnRequest);

}