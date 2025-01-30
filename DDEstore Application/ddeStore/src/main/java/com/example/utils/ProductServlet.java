package com.example.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    private List<Map<String, String>> getProductsData() throws SQLException {
        List<Map<String, String>> productsData = new ArrayList<>();
        String query = "SELECT p.ID_Produs, p.ID_Categorie, c.NumeCategorie, p.Brand, p.Model, p.Memorie, p.Culoare, p.Pret, p.Stoc, p.Imagine " +
                       "FROM produse p JOIN categorii c ON p.ID_Categorie = c.ID_Categorie";

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
                productsData.add(row);
            }
        }
        return productsData;
    }
    
    private List<Map<String, String>> getCategoriesData() throws SQLException {
        List<Map<String, String>> categoriesData = new ArrayList<>();
        String query = "SELECT ID_Categorie, NumeCategorie FROM categorii";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Map<String, String> category = new HashMap<>();
                category.put("ID_Categorie", rs.getString("ID_Categorie"));
                category.put("NumeCategorie", rs.getString("NumeCategorie"));
                categoriesData.add(category);
            }
        }
        return categoriesData;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        String idProdus = request.getParameter("id");

        try {
            // Obține lista de categorii
            List<Map<String, String>> categoriesData = getCategoriesData();
            request.setAttribute("categories", categoriesData);

            if ("edit".equals(view) && idProdus != null) {
                // Preluăm detaliile produsului pentru editare
                try (Connection conn = getConnection()) {
                    String query = "SELECT * FROM produse WHERE ID_Produs = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setInt(1, Integer.parseInt(idProdus));
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                request.setAttribute("idProdus", rs.getString("ID_Produs"));
                                request.setAttribute("idCategorie", rs.getString("ID_Categorie"));
                                request.setAttribute("brand", rs.getString("Brand"));
                                request.setAttribute("model", rs.getString("Model"));
                                request.setAttribute("memorie", rs.getString("Memorie"));
                                request.setAttribute("culoare", rs.getString("Culoare"));
                                request.setAttribute("pret", rs.getString("Pret"));
                                request.setAttribute("stoc", rs.getString("Stoc"));
                                request.setAttribute("imagine", rs.getString("Imagine"));
                            }
                        }
                    }
                }
                request.getRequestDispatcher("/admin/editProduct.jsp").forward(request, response);
            } else if ("add".equals(view)) {
                // Redirecționăm către pagina de adăugare
                request.getRequestDispatcher("/admin/addProduct.jsp").forward(request, response);
            } else {
                // Afișăm lista principală de produse
                List<Map<String, String>> productsData = getProductsData();
                request.setAttribute("products", productsData);
                request.getRequestDispatcher("/admin/products.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la preluarea produselor sau categoriilor.");
        }
    }





    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection connection = getConnection()) {
            if ("update".equals(action)) {
                updateProduct(request, connection);
            } else if ("add".equals(action)) {
                addProduct(request, connection);
            } else if ("delete".equals(action)) {
                deleteProduct(request, connection);
            }
            response.sendRedirect("ProductServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "A apărut o eroare.");
        }
    }



    private void addProduct(HttpServletRequest request, Connection connection) throws SQLException {
        String idCategorie = request.getParameter("idCategorie");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String memorie = request.getParameter("memorie");
        String culoare = request.getParameter("culoare");
        String pret = request.getParameter("pret");
        String stoc = request.getParameter("stoc");
        String imagine = request.getParameter("imagine");

        //adauga produs nou
        String query = "INSERT INTO produse (ID_Categorie, Brand, Model, Memorie, Culoare, Pret, Stoc, Imagine) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(idCategorie));
            statement.setString(2, brand);
            statement.setString(3, model);
            statement.setString(4, memorie);
            statement.setString(5, culoare);
            statement.setBigDecimal(6, new BigDecimal(pret));
            statement.setInt(7, Integer.parseInt(stoc));
            statement.setString(8, imagine);
            statement.executeUpdate();
        }
    }

    private void updateProduct(HttpServletRequest request, Connection connection) throws SQLException, IOException, ServletException {
        String idProdus = request.getParameter("idProdus");
        String idCategorie = request.getParameter("idCategorie");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String memorie = request.getParameter("memorie");
        String culoare = request.getParameter("culoare");
        String pret = request.getParameter("pret");
        String stoc = request.getParameter("stoc");

        Part filePart = request.getPart("imagine");
        String imagine = filePart != null ? filePart.getSubmittedFileName() : request.getParameter("currentImagine");

        String uploadPath = getServletContext().getRealPath("") + "uploads" + File.separator;
        if (imagine != null && !imagine.isEmpty() && filePart != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            filePart.write(uploadPath + imagine);
        }

        System.out.println("Updating product with details:");
        System.out.println("ID Produs: " + idProdus);
        System.out.println("ID Categorie: " + idCategorie);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Memorie: " + memorie);
        System.out.println("Culoare: " + culoare);
        System.out.println("Pret: " + pret);
        System.out.println("Stoc: " + stoc);
        System.out.println("Imagine: " + imagine);

        //editare prous existent
        String query = "UPDATE produse SET ID_Categorie = ?, Brand = ?, Model = ?, Memorie = ?, Culoare = ?, "
        		+ "Pret = ?, Stoc = ?, Imagine = ? WHERE ID_Produs = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(idCategorie));
            stmt.setString(2, brand);
            stmt.setString(3, model);
            stmt.setString(4, memorie);
            stmt.setString(5, culoare);
            stmt.setBigDecimal(6, new BigDecimal(pret));
            stmt.setInt(7, Integer.parseInt(stoc));
            stmt.setString(8, imagine);
            stmt.setInt(9, Integer.parseInt(idProdus));
            stmt.executeUpdate();
        }
    }


    private void deleteProduct(HttpServletRequest request, Connection connection) throws SQLException {
        String idProdus = request.getParameter("idProdus");

        // Ștergem produsul din coș
        String deleteFromCart = "DELETE FROM cos WHERE ID_Produs = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteFromCart)) {
            stmt.setInt(1, Integer.parseInt(idProdus));
            stmt.executeUpdate();
        }

        // Ștergem produsul din lista de favorite
        String deleteFromFavorites = "DELETE FROM favorite WHERE ID_Produs = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteFromFavorites)) {
            stmt.setInt(1, Integer.parseInt(idProdus));
            stmt.executeUpdate();
        }

        // Ștergem produsul din tabela produse
        String deleteFromProducts = "DELETE FROM produse WHERE ID_Produs = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteFromProducts)) {
            stmt.setInt(1, Integer.parseInt(idProdus));
            stmt.executeUpdate();
        }
    }
    
    private void reduceStock(int productId, int quantity, Connection connection) throws SQLException {
        String query = "UPDATE produse SET Stoc = Stoc - ? WHERE ID_Produs = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }
    


}
