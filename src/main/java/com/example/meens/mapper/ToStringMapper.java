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
     * @param firstSize  размерность строк.
     * @param secondSize размерность столбцов.
     * @param str        строка для преобразования.
     * @return char[][]
     */
    @Mapping(source = "inch", target = "centimeter", qualifiedByName = "stringToChar")
    @Named("stringToChar")
    String[][] toArrayString(int firstSize, int secondSize, String str);

    /**
     * Метод для перевода из char[][] в String.
     *
     * @param str двумерный массив char для перевода.
     * @return String.
     */
    @Mapping(source = "inch", target = "centimeter", qualifiedByName = "stringToChar")
    @Named("stringToChar")
    String toSting(char[][] str);

    /**
     * Метод для перевода из String[][] в String.
     *
     * @param str двумерный массив String для перевода.
     * @return String.
     */
    @Mapping(source = "inch", target = "centimeter", qualifiedByName = "stringToChar")
    @Named("stringToChar")
    String toSting(String[][] str);
}
