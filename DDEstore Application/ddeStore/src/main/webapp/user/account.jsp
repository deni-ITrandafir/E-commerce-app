<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

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
	        <div class="col-md-9 d-flex justify-content-center align-items-center">
	            <div class="shadow p-5 rounded bg-light" style="width: 80%;">
	                <h2 class="text-center mb-4" style="color: #343a40;">HELLO, <%= session.getAttribute("name") %></h2>
	                <h4 class="text-center mb-3" style="color: #495057;">Bine ai venit pe pagina contului tau!</h4>
	                <p class="text-center" style="color: #6c757d; font-size: 1.1rem;">Alege una dintre optiunile din meniu pentru a gestiona contul tau.</p>
	            </div>
	        </div>


	    </div>
	</div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
