//PAGINA DE FINALIZEAZA COMANDA (INAINTE DE PLASEAZA COMANDA)

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/EditUserDetailsServlet")
public class EditUserDetailsServlet extends HttpServlet {
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
            response.sendRedirect("/ddeStore/login.jsp");
            return;
        }

        // Preluare total din parametru și salvare în sesiune
        String totalPriceParam = request.getParameter("totalPrice");
        if (totalPriceParam != null && !totalPriceParam.isEmpty()) {
            try {
                double totalPrice = Double.parseDouble(totalPriceParam);
                session.setAttribute("orderTotal", totalPrice);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        try (Connection connection = getConnection()) {
            // Preluare detalii utilizator
            String userQuery = "SELECT * FROM utilizatori WHERE ID_Utilizator = ?";
            try (PreparedStatement userStmt = connection.prepareStatement(userQuery)) {
                userStmt.setInt(1, userId);
                ResultSet userRs = userStmt.executeQuery();
                if (userRs.next()) {
                    request.setAttribute("userDetails", Map.of(
                        "Nume", userRs.getString("Nume"),
                        "Prenume", userRs.getString("Prenume"),
                        "Telefon", userRs.getString("Telefon"),
                        "Oras", userRs.getString("Oras"),
                        "Judet", userRs.getString("Judet"),
                        "Strada", userRs.getString("Strada"),
                        "Numar", userRs.getString("Numar")
                    ));
                }
            }

            // Preluare produse din coș
            String cartQuery = "SELECT p.Brand, p.Model, p.Pret AS price, c.Cantitate AS quantity " +
                               "FROM produse p " +
                               "JOIN cos c ON p.ID_Produs = c.ID_Produs " +
                               "WHERE c.ID_Utilizator = ?";
            List<Map<String, String>> cartProducts = new ArrayList<>();
            try (PreparedStatement cartStmt = connection.prepareStatement(cartQuery)) {
                cartStmt.setInt(1, userId);
                ResultSet cartRs = cartStmt.executeQuery();
                while (cartRs.next()) {
                    Map<String, String> product = new HashMap<>();
                    product.put("brand", cartRs.getString("Brand"));
                    product.put("model", cartRs.getString("Model"));
                    product.put("price", cartRs.getString("price"));
                    product.put("quantity", cartRs.getString("quantity"));
                    cartProducts.add(product);
                }
            }
            request.setAttribute("cartProducts", cartProducts);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la server.");
            return;
        }

        // Forward către pagina JSP
        request.getRequestDispatcher("/user/editUserDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID_Utilizator");

        if (userId == null) {
            response.sendRedirect("/ddeStore/login.jsp");
            return;
        }

        // Obținem datele din formular
        String telefon = request.getParameter("telefon");
        String oras = request.getParameter("oras");
        String judet = request.getParameter("judet");
        String strada = request.getParameter("strada");
        String numar = request.getParameter("numar");

        try (Connection connection = getConnection()) {
            // Actualizare detalii utilizator
            String updateQuery = "UPDATE utilizatori SET Telefon = ?, Oras = ?, Judet = ?, Strada = ?, Numar = ? "
            		+ "WHERE ID_Utilizator = ?";
            try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
                stmt.setString(1, telefon);
                stmt.setString(2, oras);
                stmt.setString(3, judet);
                stmt.setString(4, strada);
                stmt.setString(5, numar);
                stmt.setInt(6, userId);
                stmt.executeUpdate();
            }

            // Obținere total comandă
            double totalPrice = (double) session.getAttribute("orderTotal");

            // Inserare comandă în baza de date
            String insertOrderQuery = "INSERT INTO comenzi (ID_utilizator, DataComanda, Status, Total) "
            		+ "VALUES (?, NOW(), 'In curs', ?)";
            int orderId;
            try (PreparedStatement stmt = connection.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, userId);
                stmt.setDouble(2, totalPrice);
                stmt.executeUpdate();

                // Obținem ID-ul comenzii
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                } else {
                    throw new SQLException("Nu s-a putut obține ID-ul comenzii.");
                }
            }

            // Inserare detalii comandă
            String selectCartQuery = "SELECT c.ID_Produs, c.Cantitate, p.Pret FROM cos c JOIN produse p ON c.ID_Produs = p.ID_Produs WHERE c.ID_Utilizator = ?";
            try (PreparedStatement stmt = connection.prepareStatement(selectCartQuery)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                //inserare detalii despre comanda 
                String insertDetailsQuery = "INSERT INTO detaliicomanda (ID_Comanda, ID_Produs, Cantitate, Pret) VALUES (?, ?, ?, ?)";
                try (PreparedStatement detailStmt = connection.prepareStatement(insertDetailsQuery)) {
                    while (rs.next()) {
                        int productId = rs.getInt("ID_Produs");
                        int quantity = rs.getInt("Cantitate");
                        double price = rs.getDouble("Pret");
                        detailStmt.setInt(1, orderId);
                        detailStmt.setInt(2, productId);
                        detailStmt.setInt(3, quantity);
                        detailStmt.setDouble(4, price);
                        detailStmt.addBatch();
                    }
                    detailStmt.executeBatch();
                }
            }

            // Ștergem coșul utilizatorului
            String deleteCartQuery = "DELETE FROM cos WHERE ID_Utilizator = ?";
            try (PreparedStatement stmt = connection.prepareStatement(deleteCartQuery)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            // Redirecționare către confirmarea comenzii
            response.sendRedirect("/ddeStore/user/orderConfirmation.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la server: " + e.getMessage());
        }
    }
}
