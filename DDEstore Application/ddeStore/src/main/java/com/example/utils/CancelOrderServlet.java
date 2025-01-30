package com.example.utils;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/CancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");

        if (orderId == null || orderId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID comanda invalid.");
            return;
        }

        try (Connection connection = getConnection()) {
        	//anulare comanda din panoul de user
            String updateQuery = "UPDATE comenzi SET Status = 'Anulata' WHERE ID_Comanda = ?";
            try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
                stmt.setInt(1, Integer.parseInt(orderId));
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la server.");
            return;
        }

        response.sendRedirect("/ddeStore/OrderHistoryServlet");
    }
}
