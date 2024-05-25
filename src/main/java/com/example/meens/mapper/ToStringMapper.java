package com.example.meens.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Маппер для превращения из String в char[][]
 */
@Mapper
public interface ToStringMapper {
    ToStringMapper INSTANCE = Mappers.getMapper(ToStringMapper.class);

    /**
     * Метод для преревода из String в String[][]
     *
     * @param rowNumber размерность строк.
     * @param colNumber размерность столбцов.
     * @param str       строка для преобразования.
     * @return char[][]
     */
    @Named("toArrayString")
    String[][] toArrayString(int rowNumber, int colNumber, String str);

    /**
     * Метод для перевода из char[][] в String.
     *
     * @param str двумерный массив char для перевода.
     * @return String.
     */
    @Named("toString")
    String toString(char[][] str);

    /**
     * Метод для перевода из String[][] в String.
     *
     * @param str двумерный массив String для перевода.
     * @return String.
     */
    @Named("toString")
    String toString(String[][] str);
}
