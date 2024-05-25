package com.example.meens.mapper;

import com.example.meens.dto.GameInfoResponse;
import com.example.meens.dto.NewGameRequest;
import com.example.meens.entity.GameEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

/**
 * Маппер для создания {@link GameEntity}.
 */
@Service
public class GameEntityMapper {
    /**
     * Метод для составления сущности {@link GameEntity}
     *
     * @param newGameRequest запрос на создание игры.
     * @param fieldView      клиентское поле.
     * @param field          сгенерированное поле.
     * @param completed      статус игры.
     * @return сущность {@link GameEntity}
     */
    public GameEntity toEntity(NewGameRequest newGameRequest, String fieldView, String field, Boolean completed) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setWidth(newGameRequest.getWidth());
        gameEntity.setHeight(newGameRequest.getHeight());
        gameEntity.setGameFieldView(fieldView);
        gameEntity.setMinesCount(newGameRequest.getMinesCount());
        gameEntity.setGameField(field);
        gameEntity.setCompleted(completed);
        return gameEntity;
    }

    /**
     * Метод превращения из {@link  GameEntity} в {@link GameInfoResponse}.
     *
     * @param gameEntity
     * @return {@link  GameInfoResponse}
     */
    public GameInfoResponse gameEntityToGameInfoResponse(@NotNull GameEntity gameEntity) {
        GameInfoResponse gameInfoResponse = new GameInfoResponse();
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
