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



@WebServlet("/allTables")
public class allTablesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Metodă pentru obținerea conexiunii la baza de date
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    // Metodă pentru preluarea datelor dintr-un tabel
    private List<Map<String, String>> getTableData(String tableName) throws SQLException {
        List<Map<String, String>> tableData = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

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
                tableData.add(row);
            }
        }
        return tableData;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Specifică tabelele din baza de date
            String[] tables = {"Utilizatori", "Produse", "Comenzi", "Cos", "DetaliiComanda", "Favorite"};
            Map<String, List<Map<String, String>>> allTablesData = new HashMap<>();

            for (String table : tables) {
                List<Map<String, String>> tableData = getTableData(table);
                allTablesData.put(table, tableData);
                System.out.println("Date preluate pentru tabelul: " + table); // Log pentru depanare
            }

            // Setează datele ca atribut pentru a fi utilizate în JSP
            request.setAttribute("allTablesData", allTablesData);
            request.getRequestDispatcher("allTables.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "A apărut o eroare la preluarea datelor: " + e.getMessage());
            
        }
    }
}
