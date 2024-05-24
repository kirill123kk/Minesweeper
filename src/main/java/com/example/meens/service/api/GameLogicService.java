package com.example.meens.service.api;

import com.example.meens.dto.GameTurnRequest;
import com.example.meens.dto.NewGameRequest;
import com.example.meens.entity.GameEntity;
import com.example.meens.excepion.MinesweeperException;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Сервис игровой логики.
 */
public interface GameLogicService {
    /**
     * Метод начало новой игры.
     *
     * @param newGame запрос на создание игры.
     * @return ответ с информацией о сушности {@link GameEntity}.
     */
    GameEntity startNewGame(NewGameRequest newGame);

    /**
     * Метод игрового хода.
     *
     * @param gameTurnRequest запрос на совершение хода.
     * @param gameEntity сущность из BD.
     * @return ответ с информацией о сущности {@link GameEntity}
     * @throws JsonProcessingException
     */
    GameEntity turnGame(GameTurnRequest gameTurnRequest, GameEntity gameEntity) throws JsonProcessingException, MinesweeperException;
}
