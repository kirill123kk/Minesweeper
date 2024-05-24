package com.example.meens.excepion;

/**
 * Класс реализации ошибок.
 */
public class MinesweeperException extends RuntimeException{
    public MinesweeperException(String message) {
        super(message);
    }
}