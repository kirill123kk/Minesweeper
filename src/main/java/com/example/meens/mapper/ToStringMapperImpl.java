package com.example.meens.mapper;

import liquibase.pro.packaged.S;

/**
 * Реализация {@link ToStringMapper}
 */
public class ToStringMapperImpl implements ToStringMapper {
    @Override
    public String[][] toArrayString(int rowNumber, int colNumber, String str) {
        String[][] arrayChar = new String[rowNumber][colNumber];
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                arrayChar[i][j] = String.valueOf(str.charAt(i * colNumber + j));
            }
        }
        return arrayChar;
    }

    @Override
    public String toString(char[][] str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : str) {
            for (char c : row) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString(String[][] str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] row : str) {
            for (String c : row) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }


}
