<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    // Verifică dacă utilizatorul este autentificat
	if (session.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Map<String, String>> orders = (List<Map<String, String>>) request.getAttribute("orders");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders</title>
    <link href="/ddeStore/css/index.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
	    .search-bar {
	        display: flex;
	        align-items: center;
	        justify-content: flex-end;
	        margin-bottom: 20px;
	    }
	
	    .search-bar input[type="text"] {
	        border-radius: 20px;
	        padding: 10px 15px;
	        border: 1px solid #ddd;
	        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	        width: 250px;
	        transition: all 0.3s ease;
	    }
	
	    .search-bar input[type="text"]:focus {
	        border-color: #007bff;
	        outline: none;
	        box-shadow: 0 4px 10px rgba(0, 123, 255, 0.2);
	    }
	
	    .search-bar button {
	        color: white;
	        border: none;
	        border-radius: 0 20px 20px 0;
	        padding: 10px 20px;
	        margin-left: 10px;
	        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	        transition: all 0.3s ease;
	        transform: translateX(-70%);
	    }
	
	    .search-bar button:hover {
	        box-shadow: 0 4px 10px rgba(0, 123, 255, 0.3);
	    }
	</style>
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
                
                <div class="d-flex justify-content-between align-items-center mb-3">
				    <h2>Comenzi</h2>
				    <!-- Bara de cautare -->
				   <div class="search-bar">
					    <form action="/ddeStore/OrderServlet" method="get" class="d-flex">
					        <input type="text" name="searchId" class="form-control me-2" placeholder="Cauta numar Comanda" aria-label="Search" required>
					        <button type="submit" class="btn btn-success">Cauta</button>
					    </form>
					</div>
				</div>
                

                <!-- Tabel cu comenzile -->
                <table class="table table-striped">
				    <thead>
				        <tr>
				            <th>ID</th>
				            <th>Data</th>
				            <th>Status</th>
				            <th>Total</th>
				            <th>Client</th>
				            <th>Actiuni</th>
				        </tr>
				    </thead>
					    <tbody>
					    <% if (orders != null && !orders.isEmpty()) { %>
					        <% for (Map<String, String> order : orders) { %>
					            <tr>
					                <td><%= order.get("ID_Comanda") != null ? order.get("ID_Comanda") : "N/A" %></td>
					                <td><%= order.get("DataComanda") != null ? order.get("DataComanda") : "N/A" %></td>
					                <td><%= order.get("Status") != null ? order.get("Status") : "N/A" %></td>
					                <td><%= order.get("Total") != null ? order.get("Total") : "0.00" %></td>
					                <td><%= order.get("ClientName") != null ? order.get("ClientName") : "N/A" %></td>
					                <td>
									    <!-- Buton Verifică -->
									    <form action="/ddeStore/OrderServlet" method="post" style="display:inline-block;">
									        <input type="hidden" name="action" value="verifica">
									        <input type="hidden" name="orderId" value="<%= order.get("ID_Comanda") %>">
									        <button type="submit" class="btn btn-success btn-sm"
									            <% if ("anulata".equals(order.get("Status"))) { %>disabled<% } %>>
									            <svg xmlns="http://www.w3.org/2000/svg" style="width:20px; heigh:20px" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-2 h-2">
												  <path strokeLinecap="round" strokeLinejoin="round" d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
												</svg>
									            Verifica
									        </button>
									    </form>
									
									    <!-- Buton Anulează -->
									    <form action="/ddeStore/OrderServlet" method="post" style="display:inline-block;">
									        <input type="hidden" name="action" value="anuleaza">
									        <input type="hidden" name="orderId" value="<%= order.get("ID_Comanda") %>">
									        <button type="submit" class="btn btn-danger btn-sm"
									            <% if ("anulata".equals(order.get("Status"))) { %>disabled<% } %>>
									            <svg xmlns="http://www.w3.org/2000/svg" style="width:20px; heigh:20px" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
												  <path strokeLinecap="round" strokeLinejoin="round" d="m9.75 9.75 4.5 4.5m0-4.5-4.5 4.5M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
												</svg>
									            
									            Anuleaza
									        </button>
									    </form>
									    <!-- Buton Detalii Comanda -->
									    <form action="/ddeStore/OrderDetailsServlet" method="get" style="display:inline-block;">
									        <input type="hidden" name="orderId" value="<%= order.get("ID_Comanda") %>">
									        <button type="submit" class="btn btn-warning btn-sm">
									        	<svg xmlns="http://www.w3.org/2000/svg" style="width:20px; heigh:20px" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
												  <path strokeLinecap="round" strokeLinejoin="round" d="M19.5 14.25v-2.625a3.375 3.375 0 0 0-3.375-3.375h-1.5A1.125 1.125 0 0 1 13.5 7.125v-1.5a3.375 3.375 0 0 0-3.375-3.375H8.25m5.231 13.481L15 17.25m-4.5-15H5.625c-.621 0-1.125.504-1.125 1.125v16.5c0 .621.504 1.125 1.125 1.125h12.75c.621 0 1.125-.504 1.125-1.125V11.25a9 9 0 0 0-9-9Zm3.75 11.625a2.625 2.625 0 1 1-5.25 0 2.625 2.625 0 0 1 5.25 0Z" />
												</svg>
									        	Detalii Comanda
									        </button>
									    </form>
									    
									</td>
					            </tr>
					        <% } %>
					    <% } else { %>
					        <tr>
					            <td colspan="6" class="text-center">Comanda nu este disponibila.</td>
					        </tr>
					    <% } %>
					</tbody>
				</table>
            </div>
        </div>
    </div>
    
    
	
	
	    
</body>


</html>
