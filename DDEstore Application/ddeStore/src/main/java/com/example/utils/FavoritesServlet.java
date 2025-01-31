package com.example.utils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;

@WebServlet("/FavoritesServlet")
public class FavoritesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

   
    private Connection getConnection() throws SQLException {
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
            response.sendRedirect("/ddeStore/login.jsp"); // Redirect dacă utilizatorul nu este autentificat
            return;
        }
        
        
        List<Map<String, String>> favoriteProducts = new ArrayList<>();
        try (Connection connection = getConnection()) {
        	//obtine lista de favorite a utilizatorului conectat
            String query = """
                SELECT p.ID_Produs, p.Brand, p.Model, p.Pret, p.Imagine
                FROM produse p
                INNER JOIN favorite f ON p.ID_Produs = f.ID_Produs
                WHERE f.ID_Utilizator = ?
            """;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Map<String, String> product = new HashMap<>();
                    product.put("id", resultSet.getString("ID_Produs"));
                    product.put("brand", resultSet.getString("Brand"));
                    product.put("model", resultSet.getString("Model"));
                    product.put("price", resultSet.getString("Pret"));
                    product.put("image", resultSet.getString("Imagine"));
                    favoriteProducts.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        request.setAttribute("favoriteProducts", favoriteProducts);
        request.getRequestDispatcher("/user/favorites.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID_Utilizator");

        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"success\": false, \"message\": \"Utilizatorul nu este logat.\"}");
            return;
        }

        String action = request.getParameter("action");
        String productId = request.getParameter("id");

        System.out.println("Parametri primiți:");
        System.out.println("Action: " + action);
        System.out.println("Product ID: " + productId);
        System.out.println("User ID: " + userId);

        if (action == null || action.isEmpty() || productId == null || productId.isEmpty()) {
            System.out.println("Parametri invalizi primiți!");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false, \"message\": \"Parametri invalizi primiți!\"}");
            return;
        }

        boolean success = false;
        String message = "";

        try (Connection connection = getConnection()) {
            if ("add".equals(action)) {
            	//adauga produs la favorite
                try (PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO favorite (ID_Utilizator, ID_Produs) VALUES (?, ?)")) {
                    stmt.setInt(1, userId);
                    stmt.setInt(2, Integer.parseInt(productId));
                    int rowsAffected = stmt.executeUpdate();
                    success = rowsAffected > 0;
                    message = "Produs adăugat la favorite!";
                }
            } else if ("remove".equals(action)) {
                // Verifică dacă produsul există înainte de ștergere
                try (PreparedStatement checkStmt = connection.prepareStatement(
                        "SELECT COUNT(*) FROM favorite WHERE ID_Utilizator = ? AND ID_Produs = ?")) {
                    checkStmt.setInt(1, userId);
                    checkStmt.setInt(2, Integer.parseInt(productId));
                    ResultSet rs = checkStmt.executeQuery();
                    if (rs.next() && rs.getInt(1) == 0) {
                        message = "Produsul nu există în lista de favorite!";
                        response.setContentType("application/json");
                        response.getWriter().write(String.format("{\"success\": false, \"message\": \"%s\"}", message));
                        return;
                    }
                }

                // sterge produsul de la favorite
                try (PreparedStatement stmt = connection.prepareStatement(
                        "DELETE FROM favorite WHERE ID_Utilizator = ? AND ID_Produs = ?")) {
                    stmt.setInt(1, userId);
                    stmt.setInt(2, Integer.parseInt(productId));
                    int rowsAffected = stmt.executeUpdate();
                    success = rowsAffected > 0;
                    message = "Produs eliminat din favorite!";
                }
            }
        } catch (SQLException e) {
            System.out.println("Eroare SQL: " + e.getMessage());
            e.printStackTrace();
            message = "Eroare la procesarea cererii.";
        }


        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"success\": %b, \"message\": \"%s\"}", success, message));
    }



}
