package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/register")
public class RegistrationSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		//test pentru a verifica daca merge crerea contului 
			//PrintWriter out = response.getWriter();
			//out.print("Working");
		
		String Email = request.getParameter("email"); 
		String Parola = request.getParameter("pass"); 
		String Nume = request.getParameter("first-name"); 
		String Prenume = request.getParameter("last-name"); 
		String Telefon = request.getParameter("contact"); 
		
		RequestDispatcher dispatcher = null;   
		
		Connection con = null; 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root", "19012004");
			PreparedStatement pst = con.prepareStatement("insert into Utilizatori (Email, Parola, Nume, Prenume, Telefon) values(?,?,?,?,?)");
			pst.setString(1, Email);
			pst.setString(2, Parola);
			pst.setString(3, Nume);
			pst.setString(4, Prenume);
			pst.setString(5, Telefon);
			
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if (rowCount > 0) {
				request.setAttribute("status", "success");
			}else {
				request.setAttribute("status", "failed");
			}
			
			dispatcher.forward(request, response); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
;
		
	}

}
