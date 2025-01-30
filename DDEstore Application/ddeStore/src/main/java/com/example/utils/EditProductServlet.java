package com.example.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/EditProductServlet")
@MultipartConfig
public class EditProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    private Map<String, String> getProductDetails(int productId) throws SQLException {
        String query = "SELECT * FROM produse WHERE ID_Produs = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, String> product = new HashMap<>();
                    product.put("ID_Produs", rs.getString("ID_Produs"));
                    product.put("ID_Categorie", rs.getString("ID_Categorie"));
                    product.put("Brand", rs.getString("Brand"));
                    product.put("Model", rs.getString("Model"));
                    product.put("Memorie", rs.getString("Memorie"));
                    product.put("Culoare", rs.getString("Culoare"));
                    product.put("Pret", rs.getString("Pret"));
                    product.put("Stoc", rs.getString("Stoc"));
                    product.put("Imagine", rs.getString("Imagine"));
                    return product;
                }
            }
        }
        return null;
    }

    private List<Map<String, String>> getCategoriesData() throws SQLException {
        List<Map<String, String>> categories = new ArrayList<>();
        String query = "SELECT ID_Categorie, NumeCategorie FROM categorii";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Map<String, String> category = new HashMap<>();
                category.put("ID_Categorie", rs.getString("ID_Categorie"));
                category.put("NumeCategorie", rs.getString("NumeCategorie"));
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        try {
            // Fetch product details and categories
            Map<String, String> product = getProductDetails(productId);
            List<Map<String, String>> categories = getCategoriesData();

            request.setAttribute("product", product);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/admin/editProduct.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la preluarea detaliilor produsului.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("idProdus"));
        String idCategorie = request.getParameter("idCategorie");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String memorie = request.getParameter("memorie");
        String culoare = request.getParameter("culoare");
        String pret = request.getParameter("pret");
        String stoc = request.getParameter("stoc");

        try (Connection connection = getConnection()) {
            // editare detalii produs
            String query = "UPDATE produse SET ID_Categorie = ?, Brand = ?, Model = ?, Memorie = ?, Culoare = ?, Pret = ?, Stoc = ? WHERE ID_Produs = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, Integer.parseInt(idCategorie));
                stmt.setString(2, brand);
                stmt.setString(3, model);
                stmt.setString(4, memorie);
                stmt.setString(5, culoare);
                stmt.setBigDecimal(6, new java.math.BigDecimal(pret));
                stmt.setInt(7, Integer.parseInt(stoc));
                stmt.setInt(8, productId);
                stmt.executeUpdate();
            }

            // Handle image updates
            String uploadPath = getServletContext().getRealPath("") + "uploads" + File.separator;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            for (Part part : request.getParts()) {
                if (part.getName().equals("imagini") && part.getSize() > 0) {
                    String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();
                    part.write(uploadPath + fileName);

                    // Update the image in the database
                    String imageQuery = "UPDATE produse SET Imagine = ? WHERE ID_Produs = ?";
                    try (PreparedStatement imageStmt = connection.prepareStatement(imageQuery)) {
                        imageStmt.setString(1, fileName);
                        imageStmt.setInt(2, productId);
                        imageStmt.executeUpdate();
                    }
                }
            }

            // Set a success message
            request.setAttribute("success", "Produs actualizat cu succes!");
            request.setAttribute("product", getProductDetails(productId));
            request.setAttribute("categories", getCategoriesData());
            request.getRequestDispatcher("ProductServlet").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Eroare la actualizarea produsului.");
            request.getRequestDispatcher("/admin/editProduct.jsp").forward(request, response);
        }

        //response.sendRedirect("/ddeStore/ProductServlet?success=Produs actualizat cu succes!");

    }
}
