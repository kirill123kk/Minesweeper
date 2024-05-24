package com.example.meens.mapper;

import com.example.meens.dto.GameInfoResponse;
import com.example.meens.entity.GameEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Маппер для создания {@link GameInfoResponse}.
 */
public class GameInfoResponseMapper {

    /**
     * Метод превращения из {@link  GameEntity} в {@link GameInfoResponse}.
     *
     * @param gameEntity
     * @return {@link  GameInfoResponse}
     * @throws JsonProcessingException
     */
    public GameInfoResponse gameEntityToGameInfoResponse(GameEntity gameEntity) throws JsonProcessingException {

        GameInfoResponse gameInfoResponse = new GameInfoResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        gameInfoResponse.setGameId(gameEntity.getGameId());
        gameInfoResponse.setCompleted(gameEntity.getCompleted());
        gameInfoResponse.setField(
                ToStringMapper.INSTANCE.toArrayString(
                        gameEntity.getHeight(), gameEntity.getWidth(), gameEntity.getGameFieldView()));
        gameInfoResponse.setWidth(gameEntity.getWidth());
        gameInfoResponse.setHeight(gameEntity.getHeight());
        gameInfoResponse.setMines(gameEntity.getMinesCount());
        return gameInfoResponse;
    }
}
