package com.example.meens.mapper;

import com.example.meens.dto.GameInfoResponse;
import com.example.meens.dto.NewGameRequest;
import com.example.meens.entity.GameEntity;
import org.mapstruct.Mapper;

/**
 * Маппер для создания {@link GameEntity}.
 */
@Mapper
public class GameEntityMapper {
    /**
     * Метод для составления сущности {@link GameEntity}
     *
     * @param newGameRequest запрос на создание игры.
     * @param fieldView клиентское поле.
     * @param field сгенерированное поле.
     * @param completed статус игры.
     * @return сущность {@link GameEntity}
     */
    public GameEntity  toEntity( NewGameRequest newGameRequest ,String fieldView ,String field ,Boolean completed){
        GameEntity gameEntity = new GameEntity();
        gameEntity.setWidth(newGameRequest.getWidth());
        gameEntity.setHeight(newGameRequest.getHeight());
        gameEntity.setGameFieldView(fieldView);
        gameEntity.setMinesCount(newGameRequest.getMinesCount());
        gameEntity.setGameField(field);
        gameEntity.setCompleted(completed);
        return gameEntity;
    }
}
