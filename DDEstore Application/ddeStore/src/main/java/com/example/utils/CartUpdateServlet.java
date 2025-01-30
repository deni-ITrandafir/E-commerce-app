package com.example.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;


	private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID_Utilizator");
        String productId = request.getParameter("id");
        String action = request.getParameter("action");

        if (userId == null || productId == null || productId.isEmpty() || action == null || action.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false, \"message\": \"Parametrii invalizi.\"}");
            return;
        }

        try (Connection connection = getConnection()) {
            String query = "";
            PreparedStatement stmt;

            switch (action) {
                case "increase":
                    query = "UPDATE cos SET Cantitate = Cantitate + 1 WHERE ID_Utilizator = ? AND ID_Produs = ?";
                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, userId);
                    stmt.setInt(2, Integer.parseInt(productId));
                    stmt.executeUpdate();
                    break;

                case "decrease":
                    query = "UPDATE cos SET Cantitate = Cantitate - 1 WHERE ID_Utilizator = ? AND ID_Produs = ? "
                    		+ "AND Cantitate > 1";
                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, userId);
                    stmt.setInt(2, Integer.parseInt(productId));
                    stmt.executeUpdate();
                    break;

                case "remove":
                    query = "DELETE FROM cos WHERE ID_Utilizator = ? AND ID_Produs = ?";
                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, userId);
                    stmt.setInt(2, Integer.parseInt(productId));
                    stmt.executeUpdate();
                    break;

                default:
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"success\": false, \"message\": \"Acțiune necunoscută.\"}");
                    return;
            }

            response.setContentType("application/json");
            response.getWriter().write("{\"success\": true, \"message\": \"Operație efectuată cu succes.\"}");

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false, \"message\": \"Eroare la server.\"}");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID_Utilizator");

        if (userId == null) {
            response.sendRedirect("/ddeStore/login.jsp");
            return;
        }

        List<Map<String, String>> cartProducts = new ArrayList<>();
        try (Connection connection = getConnection()) {
        	//preia toate produsele din cosul de cumparaturi pentru a trece la pasul urmator 
            String query = """
                SELECT p.ID_Produs, p.Brand, p.Model, p.Pret, p.Imagine, c.Cantitate
                FROM cos c
                INNER JOIN produse p ON c.ID_Produs = p.ID_Produs
                WHERE c.ID_Utilizator = ?
            """;

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    Map<String, String> product = new HashMap<>();
                    product.put("id", resultSet.getString("ID_Produs"));
                    product.put("brand", resultSet.getString("Brand"));
                    product.put("model", resultSet.getString("Model"));
                    product.put("price", resultSet.getString("Pret"));
                    product.put("image", resultSet.getString("Imagine"));
                    product.put("quantity", resultSet.getString("Cantitate"));
                    cartProducts.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("cartProducts", cartProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/cart.jsp");
        dispatcher.forward(request, response);
    }
}


