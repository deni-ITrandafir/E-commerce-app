package com.example.utils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/ProductFilterServlet")
public class ProductFilterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, List<String>> filters = new HashMap<>();
        List<Map<String, String>> products = new ArrayList<>();
        List<Map<String, String>> bestSellingProducts = new ArrayList<>();
        List<Map<String, String>> recommendedProducts = new ArrayList<>(); 

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID_Utilizator");

        if (userId == null) {
            response.sendRedirect("/ddeStore/login.jsp"); // Redirecționează utilizatorul dacă nu este logat
            return;
        }

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            // Categorii
            ResultSet rsCategories = statement.executeQuery("SELECT DISTINCT NumeCategorie FROM categorii");
            List<String> categories = new ArrayList<>();
            while (rsCategories.next()) {
                categories.add(rsCategories.getString("NumeCategorie"));
            }
            filters.put("categories", categories);

            // Branduri
            ResultSet rsBrands = statement.executeQuery("SELECT DISTINCT Brand FROM produse");
            List<String> brands = new ArrayList<>();
            while (rsBrands.next()) {
                brands.add(rsBrands.getString("Brand"));
            }
            filters.put("brands", brands);

            // Modele
            ResultSet rsModels = statement.executeQuery("SELECT DISTINCT Model FROM produse");
            List<String> models = new ArrayList<>();
            while (rsModels.next()) {
                models.add(rsModels.getString("Model"));
            }
            filters.put("models", models);

            // Culoare
            ResultSet rsColors = statement.executeQuery("SELECT DISTINCT Culoare FROM produse");
            List<String> colors = new ArrayList<>();
            while (rsColors.next()) {
                colors.add(rsColors.getString("Culoare"));
            }
            filters.put("colors", colors);

            // Memorie
            List<String> selectedCategories = Arrays.asList(Optional.ofNullable(request.getParameterValues("categories")).orElse(new String[0]));
            List<String> memories = new ArrayList<>();
            if (!selectedCategories.isEmpty()) {
                String categoryFilter = String.join(",", selectedCategories.stream().map(c -> "'" + c + "'").toArray(String[]::new));
                ResultSet rsMemories = statement.executeQuery(
                    "SELECT DISTINCT Memorie FROM produse p JOIN categorii c ON p.ID_Categorie = c.ID_Categorie WHERE c.NumeCategorie IN (" + categoryFilter + ")");
                while (rsMemories.next()) {
                    memories.add(rsMemories.getString("Memorie"));
                }
            } else {
                ResultSet rsMemories = statement.executeQuery("SELECT DISTINCT Memorie FROM produse");
                while (rsMemories.next()) {
                    memories.add(rsMemories.getString("Memorie"));
                }
            }
            filters.put("memories", memories);

            //query-ul din căutare și filtre
            String searchQuery = request.getParameter("query");
            String sqlQuery = "SELECT p.ID_Produs, p.Brand, p.Model, p.Memorie, p.Culoare, p.Pret, p.Imagine, c.NumeCategorie " +
                              "FROM produse p " +
                              "JOIN categorii c ON p.ID_Categorie = c.ID_Categorie WHERE 1=1";

            // Adaugă condiții pentru filtre
            String[] selectedBrands = request.getParameterValues("brands");
            String[] selectedModels = request.getParameterValues("models");
            String[] selectedColors = request.getParameterValues("colors");
            String[] selectedMemories = request.getParameterValues("memories");
            String priceMin = request.getParameter("priceMin");
            String priceMax = request.getParameter("priceMax");

            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                sqlQuery += " AND (p.Brand LIKE ? OR p.Model LIKE ? OR c.NumeCategorie LIKE ?)";
            }
            if (!selectedCategories.isEmpty()) {
                String categoryFilter = String.join(",", selectedCategories.stream().map(c -> "'" + c + "'").toArray(String[]::new));
                sqlQuery += " AND c.NumeCategorie IN (" + categoryFilter + ")";
            }
            if (selectedBrands != null && selectedBrands.length > 0) {
                String brandFilter = String.join(",", Arrays.stream(selectedBrands).map(b -> "'" + b + "'").toArray(String[]::new));
                sqlQuery += " AND p.Brand IN (" + brandFilter + ")";
            }
            if (selectedModels != null && selectedModels.length > 0) {
                String modelFilter = String.join(",", Arrays.stream(selectedModels).map(m -> "'" + m + "'").toArray(String[]::new));
                sqlQuery += " AND p.Model IN (" + modelFilter + ")";
            }
            if (selectedColors != null && selectedColors.length > 0) {
                String colorFilter = String.join(",", Arrays.stream(selectedColors).map(c -> "'" + c + "'").toArray(String[]::new));
                sqlQuery += " AND p.Culoare IN (" + colorFilter + ")";
            }
            if (selectedMemories != null && selectedMemories.length > 0) {
                String memoryFilter = String.join(",", Arrays.stream(selectedMemories).map(m -> "'" + m + "'").toArray(String[]::new));
                sqlQuery += " AND p.Memorie IN (" + memoryFilter + ")";
            }
            if (priceMin != null && !priceMin.isEmpty()) {
                sqlQuery += " AND p.Pret >= " + priceMin;
            }
            if (priceMax != null && !priceMax.isEmpty()) {
                sqlQuery += " AND p.Pret <= " + priceMax;
            }

            try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
                int paramIndex = 1;
                if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                    String searchParam = "%" + searchQuery.trim() + "%";
                    stmt.setString(paramIndex++, searchParam);
                    stmt.setString(paramIndex++, searchParam);
                    stmt.setString(paramIndex++, searchParam);
                }

                ResultSet rsProducts = stmt.executeQuery();
                while (rsProducts.next()) {
                    Map<String, String> product = new HashMap<>();
                    product.put("ID_Produs", rsProducts.getString("ID_Produs"));
                    product.put("Brand", rsProducts.getString("Brand"));
                    product.put("Model", rsProducts.getString("Model"));
                    product.put("Memorie", rsProducts.getString("Memorie"));
                    product.put("Culoare", rsProducts.getString("Culoare"));
                    product.put("Pret", rsProducts.getString("Pret"));
                    product.put("Imagine", rsProducts.getString("Imagine"));
                    product.put("Categorie", rsProducts.getString("NumeCategorie"));
                    products.add(product);
                }
            }
            
            
            //cumpara din nou 
            try (PreparedStatement ps = connection.prepareStatement(
            	    "SELECT p.ID_Produs, p.Brand, p.Model, p.Memorie, p.Culoare, p.Pret, p.Imagine " +
            	    "FROM produse p " +
            	    "JOIN ( " +
            	    "    SELECT p.ID_Categorie, COUNT(*) AS Frecventa " +
            	    "    FROM produse p " +
            	    "    JOIN detaliicomanda dc ON p.ID_Produs = dc.ID_Produs " +
            	    "    JOIN comenzi c ON dc.ID_Comanda = c.ID_Comanda " +
            	    "    WHERE c.ID_Utilizator = ? AND c.Status = 'Verificata' " +
            	    "    GROUP BY p.ID_Categorie " +
            	    "    ORDER BY Frecventa DESC " +
            	    "    LIMIT 1 " +
            	    ") AS TopCategorie ON p.ID_Categorie = TopCategorie.ID_Categorie " +
            	    "LEFT JOIN ( " +
            	    "    SELECT dc.ID_Produs " +
            	    "    FROM detaliicomanda dc " +
            	    "    JOIN comenzi c ON dc.ID_Comanda = c.ID_Comanda " +
            	    "    WHERE c.ID_Utilizator = ? " +
            	    ") AS Cumparate ON p.ID_Produs = Cumparate.ID_Produs " +
            	    "WHERE Cumparate.ID_Produs IS NULL " +
            	    "LIMIT 5"
            	)) {
                ps.setInt(1, userId);
                ps.setInt(2, userId);
                ResultSet rsRecommended = ps.executeQuery();
                while (rsRecommended.next()) {
                    Map<String, String> product = new HashMap<>();
                    product.put("ID_Produs", rsRecommended.getString("ID_Produs"));
                    product.put("Brand", rsRecommended.getString("Brand"));
                    product.put("Model", rsRecommended.getString("Model"));
                    product.put("Memorie", rsRecommended.getString("Memorie"));
                    product.put("Culoare", rsRecommended.getString("Culoare"));
                    product.put("Pret", rsRecommended.getString("Pret"));
                    product.put("Imagine", rsRecommended.getString("Imagine"));
                    recommendedProducts.add(product);
                }
            }

            
            
            // Bestseller-uri din ultimele 6 luni
            try (PreparedStatement ps = connection.prepareStatement(
	        		"SELECT p.ID_Produs, p.Brand, p.Model, p.Memorie, p.Culoare, p.Pret, p.Imagine, " +
				    "       COALESCE(SubVanzari.TotalVanzari, 0) AS TotalVanzari " +
				    "FROM produse p " +
				    "JOIN categorii c ON p.ID_Categorie = c.ID_Categorie " +
				    "LEFT JOIN ( " +
				    "    SELECT dc.ID_Produs, SUM(dc.Cantitate) AS TotalVanzari " +
				    "    FROM detaliicomanda dc " +
				    "    JOIN comenzi o ON dc.ID_Comanda = o.ID_Comanda " +
				    "    WHERE o.DataComanda >= NOW() - INTERVAL 6 MONTH " +
				    "      AND o.Status = 'Verificata' " +
				    "    GROUP BY dc.ID_Produs " +
				    ") AS SubVanzari ON p.ID_Produs = SubVanzari.ID_Produs " +
				    "WHERE c.NumeCategorie = 'Telefoane' " +
				    "ORDER BY TotalVanzari DESC " +
				    "LIMIT 3"
                )) {
                ResultSet rsBestsellers = ps.executeQuery();
                while (rsBestsellers.next()) {
                    Map<String, String> product = new HashMap<>();
                    product.put("ID_Produs", rsBestsellers.getString("ID_Produs"));
                    product.put("Brand", rsBestsellers.getString("Brand"));
                    product.put("Model", rsBestsellers.getString("Model"));
                    product.put("Memorie", rsBestsellers.getString("Memorie"));
                    product.put("Culoare", rsBestsellers.getString("Culoare"));
                    product.put("Pret", rsBestsellers.getString("Pret"));
                    product.put("Imagine", rsBestsellers.getString("Imagine"));
                    bestSellingProducts.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Integer> favoriteProductIds = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT ID_Produs FROM favorite WHERE ID_Utilizator = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    favoriteProductIds.add(resultSet.getInt("ID_Produs"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("filters", filters);
        request.setAttribute("products", products);
        request.setAttribute("bestSellingProducts", bestSellingProducts);
        request.setAttribute("favoriteProductIds", favoriteProductIds);
        request.setAttribute("recommendedProducts", recommendedProducts);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/index.jsp");

        dispatcher.forward(request, response);
    }

    
}
