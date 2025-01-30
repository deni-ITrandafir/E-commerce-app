<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
    Map<String, String> userDetails = (Map<String, String>) request.getAttribute("userDetails");
%>

<% 
        List<Map<String, String>> cartProducts = (List<Map<String, String>>) request.getAttribute("cartProducts");
        if (cartProducts != null && !cartProducts.isEmpty()) { 
            double totalPrice = 0;
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finalizeaza Comanda</title>
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
	                    <a href="/ddeStore/HistoryOrdersServlet" class="text-decoration-none text-light">Istoric comenzi</a>
	                </li>
	            </ul>
	            <div class=" text-center" style="margin-top: 320px;">
	                <a href="/ddeStore/ProductFilterServlet" class="btn btn-warning w-100">Inapoi la produse</a>
	            </div>
	            <div class=" text-center" style="margin-top: 10px;">
	                <a href="/ddeStore/logout" class="btn btn-danger w-100">Logout</a>
	            </div>
	        </div>
	
	
	
	        <!-- Form edit Content -->
	        <div class="col-md-5 d-flex justify-content-center align-items-center">
	            <div class="container mt-5">
			        <h2>Finalizeaza Comanda</h2>
			        <form action="/ddeStore/EditUserDetailsServlet" method="post">
			        <div class="mb-3">
			                <label for="nume" class="form-label">Nume</label>
			                <input type="text" class="form-control" id="nume" name="nume" value="<%= userDetails.get("Nume") %>" readonly>
			            </div>
			            <div class="mb-3">
			                <label for="prenume" class="form-label">Prenume</label>
			                <input type="text" class="form-control" id="prenume" name="prenume" value="<%= userDetails.get("Prenume") %>" readonly>
			            </div>
					    <div class="mb-3">
					        <label for="telefon" class="form-label">Telefon</label>
					        <input type="text" class="form-control" id="telefon" name="telefon" value="<%= userDetails.get("Telefon") != null ? userDetails.get("Telefon") : "" %>" required>
					    </div>
					    <div class="mb-3">
					        <label for="oras" class="form-label">Oras</label>
					        <input type="text" class="form-control" id="oras" name="oras" value="<%= userDetails.get("Oras") != null ? userDetails.get("Oras") : "" %>" required>
					    </div>
					    <div class="mb-3">
					        <label for="judet" class="form-label">Judet</label>
					        <input type="text" class="form-control" id="judet" name="judet" value="<%= userDetails.get("Judet") != null ? userDetails.get("Judet") : "" %>" required>
					    </div>
					    <div class="mb-3">
					        <label for="strada" class="form-label">Strada</label>
					        <input type="text" class="form-control" id="strada" name="strada" value="<%= userDetails.get("Strada") != null ? userDetails.get("Strada") : "" %>" required>
					    </div>
					    <div class="mb-3">
					        <label for="numar" class="form-label">Numar</label>
					        <input type="text" class="form-control" id="numar" name="numar" value="<%= userDetails.get("Numar") != null ? userDetails.get("Numar") : "" %>" required>
					    </div>
					    <button type="submit" class="btn btn-success w-100 mt-5"
					    		style="position: relative; left: 600px; top: -400px; overflow: hidden;">
					    	Plaseaza Comanda
					    </button>
    
					</form>

			    </div>
	    	</div>
	    
			    
		    <div class="col-md-5 p-5">
		    <h2 class="mb-3 mt-4">Sumar comanda</h2>
		    
		        <div class="cart-list">
		            <% for (Map<String, String> product : cartProducts) {
		                double price = Double.parseDouble(product.get("price"));
		                int quantity = Integer.parseInt(product.get("quantity"));
		                totalPrice += price * quantity;
		            %>
		                <div class="cart-item" 
		                		style="border: 1px solid #ddd; border-radius: 10px; padding: 15px; margin-bottom: 10px;">
		                    <h5><%= product.get("brand") %> - <%= product.get("model") %></h5>
		                    <div style="display: flex; justify-content: space-between; width: 100%;">
							    <span><%= price %> Lei</span>
							    <span>x<%= quantity %></span>
							    <span><%= String.format("%.2f", price * quantity) %> Lei</span>
							</div>
		                </div>
		            <% } %>
		        </div>
		        <div class="cart-total" 
		        	style="margin-top: 20px; padding: 15px; border: 1px solid #ddd; 
		        			border-radius: 10px; background-color: #f8f9fa;">
		            <h4>Total: <%= String.format("%.2f", totalPrice) %> Lei</h4>
		        </div>
		    <% } else { %>
		        <p>Cosul tau este gol.</p>
		    <% } %>
		    
			</div>

	</div>
</body>
</html>
