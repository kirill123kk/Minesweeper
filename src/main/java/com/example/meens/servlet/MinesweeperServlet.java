package com.example.meens.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MinesweeperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Установка HTTP-статуса 200 (OK)
        response.setStatus(HttpServletResponse.SC_OK);
        // Установка MIME-типа ответа
        response.setContentType("text/plain");
        // Отправка текстового ответа
        response.getWriter().write("Привет, мир!");
    }
}
