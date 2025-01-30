<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
    List<Map<String, Object>> orders = (List<Map<String, Object>>) request.getAttribute("orders");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contul meu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/ddeStore/css/user.css">
    <style>
		.list-group-item:hover {
		    background-color: #495057; 
		    color: #f8f9fa;
		}
		.btn-warning:hover {
		    background-color: #e0a800;
		}
		.btn-danger:hover {
		    background-color: #bd2130;
		}
		.border-top-white {
		    border-top: 1px solid white !important;
		    border-bottom: 0; 
		    border-right: 0;
		    border-left: 0;
		    padding: 15px;
		}
		.border-bottom-white {
		    border-bottom: 1px solid white ; 
		    border-right: 0;
		    border-left: 0;
		}
    </style>
</head>
<body>

    <!-- Main Container -->
	<div class="container-fluid">
	    <div class="row">
	        <!-- Sidebar -->
	        <div class="col-md-2 bg-dark text-light p-3" style="height: 100vh;">
	        	<div class="container-fluid">
		            <a class="navbar-brand" href="/ddeStore/ProductFilterServlet">
		                <img src="/ddeStore/assets/logo2.jpg" alt="Logo" style="width: 150px; heigh:auto; margin-bottom:25px;">
		            </a>
		        </div>
	            
	            <ul class="list-group bg-dark">
	            	
	                <li class="list-group-item bg-dark text-light border-top-white">
	                    <a href="/ddeStore/CartServlet" class="text-decoration-none text-light">Cos de cumparaturi</a>
	                </li>
	                <li class="list-group-item bg-dark text-light border-top-white">
	                    <a href="/ddeStore/FavoritesServlet" class="text-decoration-none text-light">Lista favorite</a>
	                </li>
	                <li class="list-group-item bg-dark text-light border-top-white">
	                    <a href="/ddeStore/AccountDetailsServlet" class="text-decoration-none text-light">Detalii cont</a>
	                </li>
	                <li class="list-group-item bg-dark text-light border-top-white border-bottom-white">
	                    <a href="/ddeStore/OrderHistoryServlet" class="text-decoration-none text-light">Istoric comenzi</a>
	                </li>
	            </ul>
	            <div class=" text-center" style="margin-top: 320px;">
	                <a href="/ddeStore/ProductFilterServlet" class="btn btn-warning w-100">Inapoi la produse</a>
	            </div>
	            <div class=" text-center" style="margin-top: 10px;">
	                <a href="/ddeStore/logout" class="btn btn-danger w-100">Logout</a>
	            </div>
	        </div>
	
	        <!-- Main Content -->
	        <div class="col-md-9 d-flex justify-content-center">
			    <div class="container mt-5">
			        <h2>Istoric Comenzi</h2>
			        <table class="table table-striped table-hover table-bordered mt-4">
			            <thead class="table-dark">
			                <tr>
			                    <th>ID</th>
			                    <th>Data</th>
			                    <th>Status</th>
			                    <th>Total</th>
			                    <th>Acțiuni</th>
			                </tr>
			            </thead>
			            <tbody>
			                <% if (orders != null && !orders.isEmpty()) { 
			                    for (Map<String, Object> order : orders) { %>
			                    <tr>
			                        <td><%= order.get("ID_Comanda") %></td>
			                        <td><%= order.get("DataComanda") %></td>
			                        <td class="fw-bold text-<%= "Anulata".equals(order.get("Status")) ? "danger" : "success" %>">
			                            <%= order.get("Status") %>
			                        </td>
			                        <td><%= order.get("Total") %> RON</td>
			                        <td>
			                            <form action="/ddeStore/CancelOrderServlet" method="post" style="display:inline;">
			                                <input type="hidden" name="orderId" value="<%= order.get("ID_Comanda") %>">
			                                <button class="btn btn-danger btn-sm">
			                                    <svg xmlns="http://www.w3.org/2000/svg" style="width:16px; height:16px;" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
			                                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12" />
			                                    </svg>
			                                    Anulează
			                                </button>
			                            </form>
			                            <a href="/ddeStore/OrderUserDetailsServlet?orderId=<%= order.get("ID_Comanda") %>" class="btn btn-warning btn-sm">
			                                <svg xmlns="http://www.w3.org/2000/svg" style="width:16px; height:16px;" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
			                                    <path stroke-linecap="round" stroke-linejoin="round" d="M10.125 2.25h-4.5c-.621 0-1.125.504-1.125 1.125v17.25c0 .621.504 1.125 1.125 1.125h12.75c.621 0 1.125-.504 1.125-1.125v-9M10.125 2.25h.375a9 9 0 0 1 9 9v.375M10.125 2.25A3.375 3.375 0 0 1 13.5 5.625v1.5c0 .621.504 1.125 1.125 1.125h1.5a3.375 3.375 0 0 1 3.375 3.375M9 15l2.25 2.25L15 12" />
			                                </svg>
			                                Vezi Factura
			                            </a>
			                        </td>
			                    </tr>
			                <% } } else { %>
			                    <tr>
			                        <td colspan="5" class="text-center">Nu există comenzi plasate.</td>
			                    </tr>
			                <% } %>
			            </tbody>
			        </table>
			    </div>
			</div>



	    </div>
	</div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
