package com.example.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID_Utilizator");

        if (userId == null) {
            response.sendRedirect("/ddeStore/login.jsp");
            return;
        }

        List<Map<String, Object>> orders = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM comenzi WHERE ID_utilizator = ? ORDER BY DataComanda DESC";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Map<String, Object> order = new HashMap<>();
                    order.put("ID_Comanda", rs.getInt("ID_Comanda"));
                    order.put("DataComanda", rs.getTimestamp("DataComanda"));
                    order.put("Status", rs.getString("Status"));
                    order.put("Total", rs.getDouble("Total"));
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la server.");
            return;
        }

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/user/orderHistory.jsp").forward(request, response);
    }
}
