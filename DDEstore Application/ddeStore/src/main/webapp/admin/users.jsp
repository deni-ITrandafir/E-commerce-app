<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    List<Map<String, String>> users = (List<Map<String, String>>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utilizatori</title>
    <link href="/ddeStore/css/index.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="d-flex">
        <!-- Sidebar -->
        <div class="sidebar bg-dark text-white">
            <div class="sidebar-header text-center py-4">
                <img src="/ddeStore/assets/logo2.jpg" alt="Logo" class="img-fluid rounded-circle mb-2" style="width: 80px;">
                <h5>Admin Panel</h5>
            </div>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link text-white" href="/ddeStore/DashboardServlet">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/ddeStore/ProductServlet">Produse</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/ddeStore/CategoryServlet">Categorii</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/ddeStore/OrderServlet">Comenzi</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/ddeStore/UserServlet">Utilizatori</a>
                </li>
                <li class="nav-item mt-4">
                    <form action="/ddeStore/logout" method="get" style="text-align: center;">
                        <button type="submit" class="btn btn-danger btn-logout">Logout</button>
                    </form>
                </li>
            </ul>
        </div>

        <!-- Content -->
        <div class="content flex-grow-1 p-4">
            <div class="container">
                <h2>Utilizatori</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <!-- <th>ID</th> -->
                            <th>Nume</th>
                            <th>Prenume</th>
                            <th>Telefon</th>
                            <th>Email</th>
                            <th>Este Admin</th>
                            <th>Actiuni</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (users != null && !users.isEmpty()) { %>
                            <% for (Map<String, String> user : users) { %>
                                <tr>
                                    <!-- <td><%= user.get("ID_Utilizator") %></td>-->
                                    <td><%= user.get("Nume") %></td>
                                    <td><%= user.get("Prenume") %></td>
                                    <td><%= user.get("Telefon") %></td>
                                    <td><%= user.get("Email") %></td>
                                    <td><%= user.get("IsAdmin") %></td>
                                    <td>
					                     <!-- Buton Fa Admin / Sterge Admin -->
					                    <form action="/ddeStore/UserServlet" method="post" style="display:inline-block;">
					                        <input type="hidden" name="action" value="<%= user.get("IsAdmin").equals("DA") ? "removeAdmin" : "makeAdmin" %>">
					                        <input type="hidden" name="userId" value="<%= user.get("ID_Utilizator") %>">
					                        <button type="submit" class="btn <%= user.get("IsAdmin").equals("DA") ? "btn-warning" : "btn-success" %> btn-sm">
					                        	
					                        	<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" class="size-6" style="width: 20px; height: 20px;">
										            <% if (user.get("IsAdmin").equals("DA")) { %>
										                <!-- Icon pentru Șterge Admin -->
										                <path strokeLinecap="round" strokeLinejoin="round" d="M22 10.5h-6m-2.25-4.125a3.375 3.375 0 1 1-6.75 0 3.375 3.375 0 0 1 6.75 0ZM4 19.235v-.11a6.375 6.375 0 0 1 12.75 0v.109A12.318 12.318 0 0 1 10.374 21c-2.331 0-4.512-.645-6.374-1.766Z" />
										            <% } else { %>
										                <!-- Icon pentru Adaugă Admin -->
										                <path strokeLinecap="round" strokeLinejoin="round" d="M18 7.5v3m0 0v3m0-3h3m-3 0h-3m-2.25-4.125a3.375 3.375 0 1 1-6.75 0 3.375 3.375 0 0 1 6.75 0ZM3 19.235v-.11a6.375 6.375 0 0 1 12.75 0v.109A12.318 12.318 0 0 1 9.374 21c-2.331 0-4.512-.645-6.374-1.766Z" />
										            <% } %>
										        </svg>
					                        	
					                            <%= user.get("IsAdmin").equals("DA") ? "Sterge Admin" : "Adauga Admin" %>
					                            
					                        </button>
					                    </form>
					
					                    <!-- Buton Delete -->
					                    <form action="/ddeStore/UserServlet" method="post" style="display:inline-block;">
					                        <input type="hidden" name="action" value="delete">
					                        <input type="hidden" name="userId" value="<%= user.get("ID_Utilizator") %>">
					                        <button type="submit" class="btn btn-danger btn-sm">
					                        	<svg xmlns="http://www.w3.org/2000/svg" style="width:20px;" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
												  <path strokeLinecap="round" strokeLinejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
												</svg>			                        	
					                            Sterge utilizator
					                        </button>
					                    </form>
                                    </td>
                                </tr>
                            <% } %>
                        <% } else { %>
                            <tr>
                                <td colspan="7" class="text-center">Nu exista utilizatori disponibili.</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
