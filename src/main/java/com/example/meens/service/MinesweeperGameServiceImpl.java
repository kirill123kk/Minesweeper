package com.example.meens.service;

import com.example.meens.dto.GameInfoResponse;
import com.example.meens.dto.GameTurnRequest;
import com.example.meens.dto.NewGameRequest;
import com.example.meens.entity.GameEntity;
import com.example.meens.mapper.GameEntityMapper;
import com.example.meens.repository.MinesweeperGameRepository;
import com.example.meens.service.api.GameLogicService;
import com.example.meens.service.api.MinesweeperGameService;
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
    private final GameEntityMapper gameEntityMapper;

    @Override
    public GameInfoResponse startNewGame(NewGameRequest newGame) {
        GameEntity gameEntity = gameLogicService.startNewGame(newGame);
        minesweeperGameRepository.save(gameEntity);
        return gameEntityMapper.gameEntityToGameInfoResponse(gameEntity);
    }

    @Override
    public GameInfoResponse makeTurn(GameTurnRequest gameTurnRequest) {
        GameEntity gameEntity;
        GameInfoResponse gameInfoResponse;
        gameEntity = minesweeperGameRepository.findById(gameTurnRequest.getGameId()).orElseThrow();
        gameEntity = gameLogicService.turnGame(gameTurnRequest, gameEntity);
        minesweeperGameRepository.save(gameEntity);
        gameInfoResponse = gameEntityMapper.gameEntityToGameInfoResponse(gameEntity);
        return gameInfoResponse;
    }
}
