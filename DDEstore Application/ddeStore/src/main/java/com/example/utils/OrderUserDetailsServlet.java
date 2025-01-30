package com.example.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/OrderUserDetailsServlet")
public class OrderUserDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    private Map<String, String> getOrderDetails(String orderId) throws SQLException {
        Map<String, String> orderDetails = new HashMap<>();
        //obtine detaliile despre comanda 
        String query = "SELECT c.ID_Comanda, c.DataComanda, c.Status, c.Total, " +
                       "u.Nume, u.Prenume, u.Telefon, u.Email, u.Oras, u.Judet, u.Strada, u.Numar " +
                       "FROM comenzi c " +
                       "JOIN utilizatori u ON c.ID_Utilizator = u.ID_Utilizator " +
                       "WHERE c.ID_Comanda = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(orderId));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    orderDetails.put("ID_Comanda", rs.getString("ID_Comanda"));
                    orderDetails.put("DataComanda", rs.getString("DataComanda"));
                    orderDetails.put("Status", rs.getString("Status"));
                    orderDetails.put("Total", rs.getString("Total"));
                    orderDetails.put("NumePrenume", rs.getString("Nume") + " " + rs.getString("Prenume"));
                    orderDetails.put("Telefon", rs.getString("Telefon"));
                    orderDetails.put("Email", rs.getString("Email"));
                    orderDetails.put("Adresa", rs.getString("Strada") + " " + rs.getString("Numar") +
                        ", " + rs.getString("Oras") + ", " + rs.getString("Judet"));
                }
            }
        }
        return orderDetails;
    }

    private List<Map<String, String>> getProductDetails(String orderId) throws SQLException {
        List<Map<String, String>> productDetails = new ArrayList<>();
        String query = "SELECT CONCAT(p.Brand, ' ', p.Model) AS DenumireProdus, d.Cantitate, d.Pret AS PretUnitar, " +
                       "(d.Cantitate * d.Pret) AS TotalProdus " +
                       "FROM detaliicomanda d " +
                       "JOIN produse p ON d.ID_Produs = p.ID_Produs " +
                       "WHERE d.ID_Comanda = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(orderId));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, String> product = new HashMap<>();
                    product.put("DenumireProdus", rs.getString("DenumireProdus"));
                    product.put("Cantitate", rs.getString("Cantitate"));
                    product.put("PretUnitar", rs.getString("PretUnitar"));
                    product.put("TotalProdus", rs.getString("TotalProdus"));
                    productDetails.add(product);
                }
            }
        }
        return productDetails;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        try {
            if (orderId == null || orderId.isEmpty()) {
                throw new IllegalArgumentException("ID-ul comenzii este invalid.");
            }

            Map<String, String> orderDetails = getOrderDetails(orderId);
            List<Map<String, String>> productDetails = getProductDetails(orderId);

            request.setAttribute("orderDetails", orderDetails);
            request.setAttribute("productDetails", productDetails);
            request.getRequestDispatcher("/user/orderDetailsUser.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la preluarea detaliilor comenzii: " + e.getMessage());
        }
    }
}
