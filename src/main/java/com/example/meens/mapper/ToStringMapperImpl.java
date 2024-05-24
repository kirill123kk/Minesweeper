package com.example.meens.mapper;

import liquibase.pro.packaged.S;

/**
 * Реализация {@link ToStringMapper}
 */
public class ToStringMapperImpl implements ToStringMapper {
    @Override
    public String[][] toArrayString(int firstSize, int secondSize, String str) {
        String [][] arrayChar = new String[firstSize][secondSize];
        for (int i = 0; i < firstSize; i++) {
            for (int j = 0; j < secondSize; j++) {
                arrayChar[i][j] = String.valueOf(str.charAt(i * firstSize + j));
            }
        }
        return arrayChar;
    }

    @Override
    public String toSting(char[][] str) {
        StringBuilder stringBuilder =new StringBuilder();
        for (char[] row : str) {
            for (char c : row) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
    @Override
    public String toSting(String[][] str) {
        StringBuilder stringBuilder =new StringBuilder();
        for (String[] row : str) {
            for (String c : row) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }


}
