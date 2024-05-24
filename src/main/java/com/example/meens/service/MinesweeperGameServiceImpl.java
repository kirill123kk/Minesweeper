package com.example.meens.service;

import com.example.meens.dto.GameInfoResponse;
import com.example.meens.dto.GameTurnRequest;
import com.example.meens.dto.NewGameRequest;
import com.example.meens.entity.GameEntity;
import com.example.meens.excepion.MinesweeperException;
import com.example.meens.mapper.GameInfoResponseMapper;
import com.example.meens.repository.MinesweeperGameRepository;
import com.example.meens.service.api.GameLogicService;
import com.example.meens.service.api.MinesweeperGameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Реализация {@link MinesweeperGameService}.
 */
@Service
@RequiredArgsConstructor
public class MinesweeperGameServiceImpl implements MinesweeperGameService {
    private final MinesweeperGameRepository minesweeperGameRepository;
    private final GameLogicService gameLogicService;


    @Override
    public GameInfoResponse startNewGame(NewGameRequest newGame) {
        GameInfoResponse gameInfoResponse = new GameInfoResponse();
        GameInfoResponseMapper gameInfoResponseMapper = new GameInfoResponseMapper();
        GameEntity gameEntity = gameLogicService.startNewGame(newGame);
        minesweeperGameRepository.save(gameEntity);
        try {
            return gameInfoResponseMapper.gameEntityToGameInfoResponse(gameEntity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public GameInfoResponse makeTurn(GameTurnRequest gameTurnRequest) throws JsonProcessingException {
        GameEntity gameEntity = new GameEntity();
        GameInfoResponse gameInfoResponse = new GameInfoResponse();
        GameInfoResponseMapper gameInfoResponseMapper = new GameInfoResponseMapper();
        gameEntity = minesweeperGameRepository.findById(gameTurnRequest.getGameId()).orElseThrow();
        try {
            gameEntity = gameLogicService.turnGame(gameTurnRequest, gameEntity);
            minesweeperGameRepository.save(gameEntity);
            gameInfoResponse = gameInfoResponseMapper.gameEntityToGameInfoResponse(gameEntity);
            return gameInfoResponse;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
