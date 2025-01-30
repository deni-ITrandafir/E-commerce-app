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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AddProductServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddProductServlet extends HttpServlet {

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

            while (rs.next()) {
                Map<String, String> category = new HashMap<>();
                category.put("ID_Categorie", rs.getString("ID_Categorie"));
                category.put("NumeCategorie", rs.getString("NumeCategorie"));
                categoriesData.add(category);
            }

            // Adaugă un log pentru a verifica categoriile preluate
            System.out.println("Categorii preluate: " + categoriesData);
        }

        return categoriesData;
    }


    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Map<String, String>> categoriesData = getCategoriesData();
            System.out.println("Categorii transmise către JSP: " + categoriesData); // Debug

            request.setAttribute("categories", categoriesData);
            request.getRequestDispatcher("/admin/addProduct.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la preluarea categoriilor.");
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCategorie = request.getParameter("idCategorie");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String memorie = request.getParameter("memorie");
        String culoare = request.getParameter("culoare");
        String pret = request.getParameter("pret");
        String stoc = request.getParameter("stoc");

        System.out.println("Received data: ");
        System.out.println("Categorie: " + idCategorie);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Memorie: " + memorie);
        System.out.println("Culoare: " + culoare);
        System.out.println("Pret: " + pret);
        System.out.println("Stoc: " + stoc);

        try (Connection connection = getConnection()) {
           
        	StringBuilder imagePaths = new StringBuilder(); 
        	 //inseram un produs nou
            String query = "INSERT INTO produse (ID_Categorie, Brand, Model, Memorie, Culoare, Pret, Stoc, Imagine) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(idCategorie));
            stmt.setString(2, brand);
            stmt.setString(3, model);
            stmt.setString(4, memorie);
            stmt.setString(5, culoare);
            stmt.setBigDecimal(6, new java.math.BigDecimal(pret));
            stmt.setInt(7, Integer.parseInt(stoc));
            stmt.setString(8, imagePaths.toString()); // Salvăm căile imaginilor ca text
            stmt.executeUpdate();

            // Obținem ID-ul produsului inserat
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int productId = 0;
            if (generatedKeys.next()) {
                productId = generatedKeys.getInt(1);
                System.out.println("Product ID: " + productId);
            } else {
                throw new SQLException("Failed to retrieve product ID.");
            }
            
            
            
         // Salvează imaginile pe server
            String uploadPath = getServletContext().getRealPath("") + "uploads" + File.separator;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            

            for (Part part : request.getParts()) {
                if (part.getName().equals("imagini") && part.getSize() > 0) {
                    String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();
                    part.write(uploadPath + fileName);

                    // Adaugă calea imaginii în baza de date (concatenăm dacă sunt mai multe imagini)
                    if (imagePaths.length() > 0) {
                        imagePaths.append(","); // Separator pentru imagini multiple
                    }
                    imagePaths.append(fileName);
                    System.out.println("Saved image: " + fileName);
                }
            }

            // Setăm mesaj de succes
            request.setAttribute("success", "Produs adaugat cu succes!");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Eroare la adaugarea produsului.");
        }

        // Redirecționare către ProductServlet
        response.sendRedirect("/ddeStore/ProductServlet");
    }

}
