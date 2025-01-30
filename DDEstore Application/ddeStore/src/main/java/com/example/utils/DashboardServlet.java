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

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "19012004";
        return DriverManager.getConnection(url, username, password);
    }

    private Map<String, Integer> getSummaryData() throws SQLException {
        Map<String, Integer> summaryData = new HashMap<>();
        String[] queries = {
                "SELECT COUNT(*) AS totalUtilizatori FROM utilizatori",
                "SELECT COUNT(*) AS totalProduse FROM produse",
                "SELECT COUNT(*) AS totalCategorii FROM categorii",
                "SELECT COUNT(*) AS totalComenziAn FROM comenzi WHERE YEAR(DataComanda) = YEAR(CURDATE())",
                "SELECT COUNT(*) AS totalComenziAzi FROM comenzi WHERE DATE(DataComanda) = CURDATE()"
        };
        String[] keys = {
                "totalUtilizatori",
                "totalProduse",
                "totalCategorii",
                "totalComenziAn",
                "totalComenziAzi"
        };

        try (Connection conn = getConnection()) {
            for (int i = 0; i < queries.length; i++) {
                try (PreparedStatement stmt = conn.prepareStatement(queries[i]);
                     ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        summaryData.put(keys[i], rs.getInt(1));
                    }
                }
            }
        }
        return summaryData;
    }
    
    //top 3 cele mai vandute categorii de produse din luna trecuta
    private List<Map<String, String>> getTopCategoriesLastMonth() throws SQLException {
        List<Map<String, String>> categoriesData = new ArrayList<>();
        String query = """
                WITH VanzariCategorie AS (
                    SELECT 
                        p.ID_Categorie, 
                        SUM(dc.Cantitate * dc.Pret) AS TotalVandut
                    FROM produse p
                    JOIN detaliicomanda dc ON p.ID_Produs = dc.ID_Produs
                    JOIN comenzi c ON dc.ID_Comanda = c.ID_Comanda
                    WHERE c.Status = 'Verificata'
                      AND c.DataComanda >= LAST_DAY(CURDATE() - INTERVAL 2 MONTH) + INTERVAL 1 DAY
                      AND c.DataComanda < LAST_DAY(CURDATE() - INTERVAL 1 MONTH) + INTERVAL 1 DAY
                    GROUP BY p.ID_Categorie
                ), CategoriiCuRang AS (
                    SELECT 
                        cat.NumeCategorie, 
                        IFNULL(vc.TotalVandut, 0) AS TotalVandut,
                        ROW_NUMBER() OVER (PARTITION BY NULL ORDER BY IFNULL(vc.TotalVandut, 0) DESC) AS RANKING
                    FROM categorii cat
                    LEFT JOIN VanzariCategorie vc ON cat.ID_Categorie = vc.ID_Categorie
                )
                SELECT NumeCategorie, TotalVandut
                FROM CategoriiCuRang
                WHERE RANKING <= 3;
            """;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, String> category = new HashMap<>();
                category.put("NumeCategorie", rs.getString("NumeCategorie"));
                category.put("TotalVandut", rs.getString("TotalVandut"));
                categoriesData.add(category);
            }
        }
        return categoriesData;
    }
    
    //cea mai profitabila luna (cea mai mare suma din cele mai putine comenzi) 
    private Map<String, String> getMostProfitableMonth() throws SQLException {
        Map<String, String> monthData = new HashMap<>();
        String query = "SELECT Luna, TotalVanzari, TotalComenzi " +
	        	       "FROM ( " +
	        	       "    SELECT DATE_FORMAT(DataComanda, '%Y-%m') AS Luna, " +
	        	       "           SUM(Total) AS TotalVanzari, " +
	        	       "           COUNT(ID_Comanda) AS TotalComenzi " +
	        	       "    FROM comenzi " +
	        	       "    WHERE Status = 'Verificata' " +
	        	       "    GROUP BY DATE_FORMAT(DataComanda, '%Y-%m') " +
	        	       ") AS SubLuni " +
	        	       "ORDER BY TotalVanzari DESC, TotalComenzi ASC " +
	        	       "LIMIT 1";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                monthData.put("Luna", rs.getString("Luna"));
                monthData.put("TotalVanzari", rs.getString("TotalVanzari"));
                monthData.put("TotalComenzi", rs.getString("TotalComenzi"));
            }
        }
        return monthData;
    }
    
    //top 3 utilizatori cu cele mai multe comenzi pe luna trecuta 
    private List<Map<String, String>> getTopUsersDecember() throws SQLException {
        List<Map<String, String>> usersData = new ArrayList<>();
        String query = """
                WITH ComenziUtilizatori AS (
                    SELECT 
                        c.ID_Utilizator,
                        COUNT(c.ID_Comanda) AS TotalComenzi,
                        RANK() OVER (ORDER BY COUNT(c.ID_Comanda) DESC) AS Clasament
                    FROM comenzi c
                    WHERE c.Status = 'Verificata'
                      AND c.DataComanda >= LAST_DAY(CURDATE() - INTERVAL 2 MONTH) + INTERVAL 1 DAY
                      AND c.DataComanda < LAST_DAY(CURDATE() - INTERVAL 1 MONTH) + INTERVAL 1 DAY
                    GROUP BY c.ID_Utilizator
                )
                SELECT 
                    CONCAT(u.Nume, ' ', u.Prenume) AS NumeComplet,
                    cu.TotalComenzi
                FROM utilizatori u
                JOIN ComenziUtilizatori cu ON u.ID_Utilizator = cu.ID_Utilizator
                WHERE cu.Clasament <= 3;
            """;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("NumeComplet", rs.getString("NumeComplet"));
                user.put("TotalComenzi", rs.getString("TotalComenzi"));
                usersData.add(user);
            }
        }
        System.out.println("Top Users December: " + usersData); // Debugging
        return usersData;
    }





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obține datele sumarizate (total utilizatori, produse, categorii, comenzi)
            Map<String, Integer> summaryData = getSummaryData();
            if (summaryData != null) {
                for (Map.Entry<String, Integer> entry : summaryData.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
            } else {
                System.err.println("Summary data is null or empty.");
            }

            // Obține topul categoriilor pentru luna trecută
            List<Map<String, String>> topCategoriesLastMonth = getTopCategoriesLastMonth();
            if (topCategoriesLastMonth != null && !topCategoriesLastMonth.isEmpty()) {
                request.setAttribute("topCategoriesLastMonth", topCategoriesLastMonth);
            } else {
                System.err.println("No top categories found for last month.");
            }

            // Obține cea mai profitabilă lună
            Map<String, String> mostProfitableMonth = getMostProfitableMonth();
            if (mostProfitableMonth != null && !mostProfitableMonth.isEmpty()) {
                request.setAttribute("mostProfitableMonth", mostProfitableMonth);
            } else {
                System.err.println("No data for the most profitable month.");
            }
            
            // Obține top utilizatori pentru decembrie
            List<Map<String, String>> topUsersDecember = getTopUsersDecember();
            request.setAttribute("topUsersDecember", topUsersDecember);
            
           
            // Direcționează către JSP pentru afișare
            request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            // Logarea erorilor SQL
            System.err.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();

            // Setează un atribut pentru mesajul de eroare
            request.setAttribute("error", "Eroare la preluarea datelor: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            // Logarea oricărei alte erori neașteptate
            System.err.println("Unexpected exception occurred: " + e.getMessage());
            e.printStackTrace();

            request.setAttribute("error", "A apărut o eroare neașteptată.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


    
    
}
