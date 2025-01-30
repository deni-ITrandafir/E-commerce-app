<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    Map<String, String> orderDetails = (Map<String, String>) request.getAttribute("orderDetails");
    List<Map<String, String>> productDetails = (List<Map<String, String>>) request.getAttribute("productDetails");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalii Comanda</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/ddeStore/css/index.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
        .invoice-container {
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 10px;
            background: #f9f9f9;
        }
        .invoice-header {
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        .table-products {
            margin-top: 20px;
        }
        .table-products th, .table-products td {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="d-flex">
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

	        <!-- Invoice Container -->
		    <div class="col-md-10 p-4">
		        <div class="container">
		            <div class="invoice-container">
		                <div class="invoice-header text-left">
		                    <h2>Factura Comanda</h2>
		                    <% if (orderDetails != null && !orderDetails.isEmpty()) { %>
		                        <p><strong>ID Comanda:</strong> <%= orderDetails.get("ID_Comanda") %></p>
		                        <p><strong>Data Comanda:</strong> <%= orderDetails.get("DataComanda") %></p>
		                        <p><strong>Status:</strong> <%= orderDetails.get("Status") %></p>
		                        <p><strong>Total:</strong> <%= orderDetails.get("Total") %> RON</p>
		                    <% } %>
		                </div>
		
		                <h4>Informatii Client</h4>
		                <% if (orderDetails != null && !orderDetails.isEmpty()) { %>
		                    <p><strong>Nume:</strong> <%= orderDetails.get("NumePrenume") %></p>
		                    <p><strong>Telefon:</strong> <%= orderDetails.get("Telefon") %></p>
		                    <p><strong>Email:</strong> <%= orderDetails.get("Email") %></p>
		                    <p><strong>Adresa:</strong> <%= orderDetails.get("Adresa") %></p>
		                <% } else { %>
		                    <p>Nu s-au gasit detalii despre client.</p>
		                <% } %>
		
		                <h4>Produse Achizitionate</h4>
		                <table class="table table-bordered table-products">
		                    <thead class="table-dark">
		                        <tr>
		                            <th>Denumire Produs</th>
		                            <th>Cantitate</th>
		                            <th>Pret Unitar (RON)</th>
		                            <th>Total (RON)</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <% if (productDetails != null && !productDetails.isEmpty()) { %>
		                            <% for (Map<String, String> product : productDetails) { %>
		                                <tr>
		                                    <td><%= product.get("DenumireProdus") %></td>
		                                    <td><%= product.get("Cantitate") %></td>
		                                    <td><%= product.get("PretUnitar") %></td>
		                                    <td><%= product.get("TotalProdus") %></td>
		                                </tr>
		                            <% } %>
		                        <% } else { %>
		                            <tr>
		                                <td colspan="4" class="text-center">Nu exista produse achizitionate.</td>
		                            </tr>
		                        <% } %>
		                    </tbody>
		                </table>
	                </div>
	            </div>
	        </div>
    </div>
</body>
</html>
