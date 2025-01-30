package com.uniquedeveloper.registration;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("username");
        String parola = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root", "19012004");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM Utilizatori WHERE Email = ? AND Parola = ?");
            pst.setString(1, email);
            pst.setString(2, parola);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // Stochează informațiile utilizatorului în sesiune
                session.setAttribute("name", rs.getString("Nume"));
                session.setAttribute("prenume", rs.getString("Prenume"));
                session.setAttribute("email", rs.getString("Email"));
                session.setAttribute("ID_Utilizator", rs.getInt("ID_Utilizator"));

                // Verifică dacă utilizatorul este admin
                boolean isAdmin = rs.getInt("IsAdmin") == 1;
                if (isAdmin) {
                    response.sendRedirect("/ddeStore/DashboardServlet"); // Redirecționare către panelul admin
                } else {
                    response.sendRedirect("/ddeStore/ProductFilterServlet"); // Redirecționare către partea de user
                }
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
