package com.example.meens.service;

import com.example.meens.dto.ErrorResponse;
import com.example.meens.dto.GameTurnRequest;
import com.example.meens.dto.NewGameRequest;
import com.example.meens.entity.GameEntity;
import com.example.meens.enums.FieldValue;
import com.example.meens.excepion.MinesweeperException;
import com.example.meens.mapper.GameEntityMapper;
import com.example.meens.mapper.ToStringMapper;
import com.example.meens.service.api.GameLogicService;
import com.example.meens.servlet.MinesweeperServlet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Реализация {@link GameLogicService}
 */
@Service
public class GameLogicServiceImpl implements GameLogicService {


    @Override
    public GameEntity startNewGame(NewGameRequest newGame) {
        if (checkAll(newGame)) {

            GameEntity gameEntity = new GameEntity();
            GameEntityMapper gameEntityMapper = new GameEntityMapper();
            gameEntity = gameEntityMapper.toEntity(newGame, voidField(newGame), generateField(newGame), false);
            return gameEntity;

        }
        return null;
    }

    /**
     * Метод создания пустого поля.
     *
     * @param newGame запрос на создание новой игры.
     * @return сгенерированное поле.
     */
    private String voidField(NewGameRequest newGame) {
        String str = new String();
        for (int i = 0; i < newGame.getHeight(); i++) {
            for (int j = 0; j < newGame.getWidth(); j++) {
                str = str + FieldValue.EMPTY.getValue();
            }
        }
        return str;
    }

    /**
     * Метод генерации поля.
     *
     * @param newGame запрос на создание игры.
     * @return сгененрированное поле.
     */
    private String generateField(NewGameRequest newGame) {
        Random random = new Random();

        char[][] field = new char[newGame.getHeight()][newGame.getWidth()];
        // Заполняем поле пустыми ячейками
        for (int i = 0; i < newGame.getHeight(); i++) {
            for (int j = 0; j < newGame.getWidth(); j++) {
                field[i][j] = FieldValue.EMPTY.getValue();
            }
        }

        // Расставляем мины
        for (int i = 0; i < newGame.getMinesCount(); i++) {
            int x, y;
            do {
                x = random.nextInt(newGame.getHeight());
                y = random.nextInt(newGame.getWidth());
            } while (field[x][y] == FieldValue.MINE_ACTIVE.getValue());
            field[x][y] = FieldValue.MINE_ACTIVE.getValue();
        }

        // Расставляем числа
        for (int i = 0; i < newGame.getHeight(); i++) {
            for (int j = 0; j < newGame.getWidth(); j++) {
                if (field[i][j] != FieldValue.MINE_ACTIVE.getValue()) {
                    field[i][j] = countAdjacentMines(i, j, newGame, field);
                }
            }
        }
        return ToStringMapper.INSTANCE.toSting(field);
    }

    /**
     * Метод назначения полю определенного занчиния из списка {@link FieldValue}.
     *
     * @param x координата столбца.
     * @param y координата строки.
     * @param newGame запрос на создание игры.
     * @param field сгенерированное поле.
     * @return значение из списка из списка {@link FieldValue}
     */
    private char countAdjacentMines(int x, int y, NewGameRequest newGame, char[][] field) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                if (newX >= 0 && newX < newGame.getHeight() && newY >= 0 && newY < newGame.getWidth() && field[newX][newY] == FieldValue.MINE_ACTIVE.getValue()) {
                    count++;
                }
            }
        }
        switch (count) {
            case 0:
                return FieldValue.ZERO.getValue();
            case 1:
                return FieldValue.ONE.getValue();
            case 2:
                return FieldValue.TWO.getValue();
            case 3:
                return FieldValue.THREE.getValue();
            case 4:
                return FieldValue.FOUR.getValue();
            case 5:
                return FieldValue.FIVE.getValue();
            case 6:
                return FieldValue.SIX.getValue();
            case 7:
                return FieldValue.SEVEN.getValue();
            case 8:
                return FieldValue.EIGHT.getValue();
            default:
                return ' ';
        }

    }

    /**
     * Метод проверки введенных параметров.
     *
     * @param newGame запрос на создание игры.
     * @return ответ Boolean правильны ли данне.
     */
    private Boolean checkAll(NewGameRequest newGame) {
        if (checkDimension(newGame.getHeight()) && checkDimension(newGame.getWidth()) && checkMinesCount(newGame))
            return true;
        return false;
    }

    /**
     * Метод проверки колонок.
     *
     * @param dimension значение колонок.
     * @return ответ Boolean правильны ли данне.
     */
    private Boolean checkDimension(Integer dimension) {
        if (dimension < 30) return true;
        return false;
    }

    /**
     * Метод проверки количества мин.
     *
     * @param newGame запрос на создание игры.
     * @return ответ Boolean правильны ли данне.
     */
    private Boolean checkMinesCount(NewGameRequest newGame) {
        if (newGame.getHeight() * newGame.getWidth() - 1 > newGame.getMinesCount()) return true;
        return false;
    }

    @Override
    public GameEntity turnGame(GameTurnRequest gameTurnRequest, GameEntity gameEntity) throws JsonProcessingException, MinesweeperException {
        ObjectMapper objectMapper = new ObjectMapper();

        String[][] field = ToStringMapper.INSTANCE.toArrayString(gameEntity.getHeight(), gameEntity.getWidth(), gameEntity.getGameFieldView());
        int col = gameTurnRequest.getCol();
        int row = gameTurnRequest.getRow();
        try {
            if (gameEntity.getCompleted()){
                throw new MinesweeperException("Эта игра иже закончена");
            }else
            if (gameEntity.getGameFieldView().charAt(row * gameEntity.getHeight() + col) != FieldValue.EMPTY.getValue()) {
                throw new MinesweeperException("Это поле уже активировано");
            } else if (gameEntity.getGameField().charAt(row * gameEntity.getHeight() + col) == FieldValue.MINE_ACTIVE.getValue()) {
                gameEntity.setCompleted(true);
                gameEntity.setGameFieldView(gameEntity.getGameField());
            } else if (gameEntity.getGameField().charAt(row * gameEntity.getHeight() + col) == FieldValue.ZERO.getValue()) {
                field = openAdjacentCells(row, col, gameEntity, field);
                gameEntity.setGameFieldView(ToStringMapper.INSTANCE.toSting(field));
                gameEntity.setCompleted(checkedLastTurn(gameEntity));
                if (gameEntity.getCompleted())
                    field = revealMines(gameEntity, field);
                gameEntity.setGameFieldView(ToStringMapper.INSTANCE.toSting(field));
            } else {
                field[row][col] = String.valueOf(gameEntity.getGameField().charAt(row * gameEntity.getHeight() + col));
                gameEntity.setGameFieldView(ToStringMapper.INSTANCE.toSting(field));
                gameEntity.setCompleted(checkedLastTurn(gameEntity));
                if (gameEntity.getCompleted())
                    revealMines(gameEntity, field);
                gameEntity.setGameFieldView(ToStringMapper.INSTANCE.toSting(field));
            }
        }catch (MinesweeperException e){
            throw new RuntimeException(e);
        }
        return gameEntity;
    }

    /**
     * Метод проверки является ли ход последним.
     *
     * @param gameEntity сушность из BD.
     * @return возвращает состояние игры.
     */
    private boolean checkedLastTurn(GameEntity gameEntity) {
        for (int i = 0; i < gameEntity.getHeight(); i++) {
            for (int j = 0; j < gameEntity.getWidth(); j++) {
                if (gameEntity.getGameField().charAt(i * gameEntity.getHeight() + j) != FieldValue.MINE_ACTIVE.getValue()
                        && gameEntity.getGameFieldView().charAt(i * gameEntity.getHeight() + j) == FieldValue.EMPTY.getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Метод открытия соседних клеток.
     *
     * @param x координата столбца.
     * @param y координата строки.
     * @param gameEntity сущность из BD.
     * @param field поле для взаимодействия.
     * @return пеле для взаимодействия.
     */
    private String[][] openAdjacentCells(int x, int y, GameEntity gameEntity, String[][] field) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            x = cell[0];
            y = cell[1];

            if (x < 0 || x >= gameEntity.getHeight() || y < 0 || y >= gameEntity.getWidth()) {
                continue;
            }
            if (field[x][y].charAt(0) != FieldValue.EMPTY.getValue()) { // проверяем, была ли ячейка уже открыта
                continue;
            }

            if (gameEntity.getGameField().charAt(x * gameEntity.getHeight() + y) == FieldValue.ZERO.getValue()) {
                field[x][y] = String.valueOf(gameEntity.getGameField().charAt(x * gameEntity.getHeight() + y));
                queue.add(new int[]{x - 1, y});
                queue.add(new int[]{x + 1, y});
                queue.add(new int[]{x, y - 1});
                queue.add(new int[]{x, y + 1});
            } else {
                field[x][y] = String.valueOf(gameEntity.getGameField().charAt(x * gameEntity.getHeight() + y));
            }
        }
        return field;
    }

    /**
     *  Метод миняющий мины из включенного (X) состояния в выключенное (M).
     * @param gameEntity сущность из BD.
     * @param field пеле для взаимодействия.
     * @return пеле для взаимодействия.
     */
    private String[][] revealMines(GameEntity gameEntity, String[][] field) {
        for (int i = 0; i < gameEntity.getHeight(); i++) {
            for (int j = 0; j < gameEntity.getWidth(); j++) {
                if (gameEntity.getGameField().charAt(i * gameEntity.getHeight() + j) == FieldValue.MINE_ACTIVE.getValue()) {
                    field[i][j] = String.valueOf(FieldValue.MINE_DISABLED.getValue());
                }
            }
        }
        return field;
    }
}