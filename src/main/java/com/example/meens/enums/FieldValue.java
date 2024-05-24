package com.example.meens.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Перечисление всех состояний поля.
 */
@Getter
@RequiredArgsConstructor
public enum FieldValue {
    EMPTY(' '),
    MINE_DISABLED('M'),
    MINE_ACTIVE('X'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    ZERO('0');


    private final char value;
}
