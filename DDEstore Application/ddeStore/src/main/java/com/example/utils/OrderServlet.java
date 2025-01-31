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

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }
    
    
    private List<Map<String, String>> searchOrderById(int orderId) throws SQLException {
        List<Map<String, String>> orders = new ArrayList<>();
      //cautare dupa id (in cazul magazinului, dupa numarul de comanda) 
        String query = "SELECT c.ID_Comanda, c.DataComanda, c.Status, c.Total, u.Nume, u.Prenume " +
                       "FROM comenzi c " +
                       "JOIN utilizatori u ON c.ID_Utilizator = u.ID_Utilizator " +
                       "WHERE c.ID_Comanda = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, String> order = new HashMap<>();
                    order.put("ID_Comanda", rs.getString("ID_Comanda"));
                    order.put("DataComanda", rs.getString("DataComanda"));
                    order.put("Status", rs.getString("Status"));
                    order.put("Total", rs.getString("Total"));
                    order.put("ClientName", rs.getString("Nume") + " " + rs.getString("Prenume"));
                    orders.add(order);
                }
            }
        }
        return orders;
    }
    
    
   
    private List<Map<String, String>> getOrdersData() throws SQLException {
        List<Map<String, String>> ordersData = new ArrayList<>();
        //afisarea listei de comenzi disponibile de la toti utiliatorii existenti cu optiuni de editare
        String query = "SELECT c.ID_Comanda, c.DataComanda, c.Status, c.Total, u.Nume, u.Prenume " +
                       "FROM comenzi c " +
                       "JOIN utilizatori u ON c.ID_Utilizator = u.ID_Utilizator " +
                       "ORDER BY c.DataComanda DESC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Map<String, String> order = new HashMap<>();
                order.put("ID_Comanda", rs.getString("ID_Comanda"));
                order.put("DataComanda", rs.getString("DataComanda"));
                order.put("Status", rs.getString("Status"));
                order.put("Total", rs.getString("Total")); // Asigură-te că această coloană există
                order.put("ClientName", rs.getString("Nume") + " " + rs.getString("Prenume"));
                ordersData.add(order);
            }
        }
        return ordersData;
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchId = request.getParameter("searchId"); // ID-ul comenzii pentru căutare
        try {
            List<Map<String, String>> ordersData;
            if (searchId != null && !searchId.isEmpty()) {
                ordersData = searchOrderById(Integer.parseInt(searchId));
            } else {
                ordersData = getOrdersData();
            }
            request.setAttribute("orders", ordersData);
            request.getRequestDispatcher("/admin/orders.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Eroare la preluarea comenzilor.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String orderId = request.getParameter("orderId");

        try (Connection connection = getConnection()) {
            if ("verifica".equals(action)) {
                verifyOrder(orderId, connection);
            } else if ("anuleaza".equals(action)) {
                cancelOrder(orderId, connection);
            }
            response.sendRedirect("OrderServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "A apărut o eroare la procesarea comenzii.");
        }
    }


    private void verifyOrder(String orderId, Connection connection) throws SQLException {
    	//verificare comanda
        String query = "UPDATE comenzi SET Status = 'verificata' WHERE ID_Comanda = ? AND Status = 'in curs'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(orderId));
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Comanda nu a fost găsită sau nu era în starea 'in curs'.");
            }
        }
    }


    private void cancelOrder(String orderId, Connection connection) throws SQLException {
    	//anulare comanda 
        String query = "UPDATE comenzi SET Status = 'anulata' WHERE ID_Comanda = ? AND Status != 'anulata'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(orderId));
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Comanda nu a putut fi anulată. Verificați starea acesteia.");
            }
        }
    }


}
