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

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    private List<Map<String, String>> getCategoriesData() throws SQLException {
        List<Map<String, String>> categoriesData = new ArrayList<>();
        String query = "SELECT ID_Categorie, NumeCategorie FROM categorii";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getString(i));
                }
                categoriesData.add(row);
            }
        }
        return categoriesData;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Map<String, String>> categoriesData = getCategoriesData();
            request.setAttribute("categories", categoriesData);
            request.getRequestDispatcher("/admin/categories.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la preluarea categoriilor.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection connection = getConnection()) {
            if ("add".equals(action)) {
                addCategory(request, connection);
            } else if ("update".equals(action)) {
                updateCategory(request, connection);
            } else if ("delete".equals(action)) {
                deleteCategory(request, connection);
            }
            response.sendRedirect("CategoryServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "A apărut o eroare.");
        }
    }

    private void addCategory(HttpServletRequest request, Connection connection) throws SQLException {
        String categoryName = request.getParameter("categoryName");
        //adauga categorie noua
        String query = "INSERT INTO categorii (NumeCategorie) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categoryName);
            statement.executeUpdate();
        }
    }

    private void updateCategory(HttpServletRequest request, Connection connection) throws SQLException {
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        //editeaza categorie existenta
        String query = "UPDATE categorii SET NumeCategorie = ? WHERE ID_Categorie = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categoryName);
            statement.setInt(2, Integer.parseInt(categoryId));
            statement.executeUpdate();
        }
    }

    private void deleteCategory(HttpServletRequest request, Connection connection) throws SQLException {
        String categoryId = request.getParameter("categoryId");

        //daca o categorie e stearsa, stergem produsele din categoria respectiva
        String deleteProductsQuery = "DELETE FROM produse WHERE ID_Categorie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteProductsQuery)) {
            stmt.setInt(1, Integer.parseInt(categoryId));
            stmt.executeUpdate();
        }

        //daca o categorie e stearsa, stergem produsele din toate cosurile de cumparaturi
        String deleteCartQuery = "DELETE FROM cos WHERE ID_Produs IN (SELECT ID_Produs FROM produse "
        		+ "WHERE ID_Categorie = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(deleteCartQuery)) {
            stmt.setInt(1, Integer.parseInt(categoryId));
            stmt.executeUpdate();
        }

        //daca o categorie e stearsa, stergem produsele din lista de favorite
        String deleteFavoritesQuery = "DELETE FROM favorite WHERE ID_Produs IN (SELECT ID_Produs FROM produse "
        		+ "WHERE ID_Categorie = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(deleteFavoritesQuery)) {
            stmt.setInt(1, Integer.parseInt(categoryId));
            stmt.executeUpdate();
        }

        // Ștergem categoria
        String deleteCategoryQuery = "DELETE FROM categorii WHERE ID_Categorie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteCategoryQuery)) {
            stmt.setInt(1, Integer.parseInt(categoryId));
            stmt.executeUpdate();
        }
    }
}
