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

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    private List<Map<String, String>> getUsersData() throws SQLException {
        List<Map<String, String>> usersData = new ArrayList<>();
        String query = "SELECT ID_Utilizator, Nume, Prenume, Telefon, Email, IsAdmin FROM utilizatori";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("ID_Utilizator", rs.getString("ID_Utilizator"));
                user.put("Nume", rs.getString("Nume"));
                user.put("Prenume", rs.getString("Prenume"));
                user.put("Telefon", rs.getString("Telefon"));
                user.put("Email", rs.getString("Email"));
                user.put("IsAdmin", rs.getInt("IsAdmin") == 1 ? "DA" : "NU");
                usersData.add(user);
            }
        }
        return usersData;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Map<String, String>> usersData = getUsersData();
            request.setAttribute("users", usersData);
            request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la preluarea utilizatorilor.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userId = request.getParameter("userId");

        System.out.println("Action: " + action);
        System.out.println("User ID: " + userId); // Log pentru debug

        if (userId == null || userId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID-ul utilizatorului este invalid sau lipsește.");
            return;
        }

        try (Connection connection = getConnection()) {
            if ("delete".equals(action)) {
                deleteUser(request, connection);
            } else if ("makeAdmin".equals(action)) {
                changeAdminStatus(request, connection, true);
            } else if ("removeAdmin".equals(action)) {
                changeAdminStatus(request, connection, false);
            }
            response.sendRedirect("UserServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare SQL la gestionarea utilizatorului: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "A apărut o eroare la gestionarea utilizatorului.");
        }
    }


    private void changeAdminStatus(HttpServletRequest request, Connection connection, boolean makeAdmin) throws SQLException {
        String userId = request.getParameter("userId");
        //editeaza e admin sau nu
        String query = "UPDATE utilizatori SET IsAdmin = ? WHERE ID_Utilizator = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, makeAdmin ? 1 : 0);
            stmt.setInt(2, Integer.parseInt(userId));
            stmt.executeUpdate();
        }
    }

    private void deleteUser(HttpServletRequest request, Connection connection) throws SQLException {
        String userId = request.getParameter("userId");

        //sterge lista de favorite odata cu stergerea utilizatorului 
        String deleteFavoritesQuery = "DELETE FROM favorite WHERE ID_Utilizator = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteFavoritesQuery)) {
            stmt.setInt(1, Integer.parseInt(userId));
            stmt.executeUpdate();
        }

        //sterge cod daca itilizatorul este sters 
        String deleteCartQuery = "DELETE FROM cos WHERE ID_Utilizator = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteCartQuery)) {
            stmt.setInt(1, Integer.parseInt(userId));
            stmt.executeUpdate();
        }

        //trandforma comanda in anulata daca utilizatorul nu mai exista
        String updateOrdersQuery = "UPDATE comenzi SET Status = 'Anulată' WHERE ID_utilizator = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateOrdersQuery)) {
            stmt.setInt(1, Integer.parseInt(userId));
            stmt.executeUpdate();
        }

        // Ștergere utilizator din `utilizatori`
        String deleteUserQuery = "DELETE FROM utilizatori WHERE ID_Utilizator = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteUserQuery)) {
            stmt.setInt(1, Integer.parseInt(userId));
            stmt.executeUpdate();
        }
    }




}
