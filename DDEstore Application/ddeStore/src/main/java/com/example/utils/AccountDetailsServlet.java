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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AccountDetailsServlet")
public class AccountDetailsServlet extends HttpServlet {
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

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM utilizatori WHERE ID_Utilizator = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Map<String, String> userDetails = new HashMap<>();
                    userDetails.put("Email", rs.getString("Email"));
                    userDetails.put("Nume", rs.getString("Nume"));
                    userDetails.put("Prenume", rs.getString("Prenume"));
                    userDetails.put("Telefon", rs.getString("Telefon"));
                    userDetails.put("Oras", rs.getString("Oras"));
                    userDetails.put("Judet", rs.getString("Judet"));
                    userDetails.put("Strada", rs.getString("Strada"));
                    userDetails.put("Numar", rs.getString("Numar"));
                    userDetails.put("Parola", rs.getString("Parola")); // Adăugăm parola
                    request.setAttribute("userDetails", userDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la server.");
            return;
        }

        request.getRequestDispatcher("/user/updateAccountDetails.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID_Utilizator");

        if (userId == null) {
            response.sendRedirect("/ddeStore/login.jsp");
            return;
        }

        String email = request.getParameter("email");
        String parolaNoua = request.getParameter("parolaNoua");
        String parolaVeche = request.getParameter("parolaVeche"); // Parola veche precompletată
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String telefon = request.getParameter("telefon");
        String oras = request.getParameter("oras");
        String judet = request.getParameter("judet");
        String strada = request.getParameter("strada");
        String numar = request.getParameter("numar");

        try (Connection connection = getConnection()) {
            String query;

            if (parolaNoua == null || parolaNoua.isEmpty()) {
                // Dacă parola nouă nu a fost completată, păstrăm parola veche
                query = "UPDATE utilizatori SET Email = ?, Parola = ?, Nume = ?, Prenume = ?, Telefon = ?, Oras = ?, "
                		+ "Judet = ?, Strada = ?,  Numar = ? WHERE ID_Utilizator = ?";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setString(1, email);
                    stmt.setString(2, parolaVeche); // Parola veche
                    stmt.setString(3, nume);
                    stmt.setString(4, prenume);
                    stmt.setString(5, telefon);
                    stmt.setString(6, oras);
                    stmt.setString(7, judet);
                    stmt.setString(8, strada);
                    stmt.setString(9, numar);
                    stmt.setInt(10, userId);
                    stmt.executeUpdate();
                }
            } else {
                // Dacă parola nouă a fost completată, o actualizăm
                query = "UPDATE utilizatori SET Email = ?, Parola = ?, Nume = ?, Prenume = ?, Telefon = ?, Oras = ?, Judet = ?, "
                		+ "Strada = ?, Numar = ? WHERE ID_Utilizator = ?";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setString(1, email);
                    stmt.setString(2, parolaNoua); // Parola nouă
                    stmt.setString(3, nume);
                    stmt.setString(4, prenume);
                    stmt.setString(5, telefon);
                    stmt.setString(6, oras);
                    stmt.setString(7, judet);
                    stmt.setString(8, strada);
                    stmt.setString(9, numar);
                    stmt.setInt(10, userId);
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la server.");
            return;
        }

        response.sendRedirect("/ddeStore/AccountDetailsServlet");
    }

}
