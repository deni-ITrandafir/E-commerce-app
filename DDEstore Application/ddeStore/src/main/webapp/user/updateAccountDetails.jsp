<%@ page import="java.util.Map" %>
<%
    Map<String, String> userDetails = (Map<String, String>) request.getAttribute("userDetails");
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
    	.container {
            max-width: 600px;
            margin: 50px auto;
        }
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
		
		.notification {
		    position: fixed;
		    bottom: 20px;
		    right: 20px;
		    background-color: #28a745; /* Verde */
		    color: white;
		    padding: 15px 20px;
		    border-radius: 5px;
		    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
		    font-size: 14px;
		    z-index: 1000;
		    display: none; /* Ascuns implicit */
		    animation: fadeIn 0.5s, fadeOut 0.5s 2.5s;
		}
		
		/* Animații pentru popup */
		@keyframes fadeIn {
		    from {
		        opacity: 0;
		        transform: translateY(10px);
		    }
		    to {
		        opacity: 1;
		        transform: translateY(0);
		    }
		}
		
		@keyframes fadeOut {
		    from {
		        opacity: 1;
		        transform: translateY(0);
		    }
		    to {
		        opacity: 0;
		        transform: translateY(10px);
		    }
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
	            <div class="container mt-5">
			    <h2 class="mb-4">Editeaza Detaliile Contului</h2>
			    <form action="/ddeStore/AccountDetailsServlet" method="post">
			        <!-- Nume și Prenume -->
			        <div class="row mb-3">
			            <div class="col-md-6">
			                <label for="nume" class="form-label">Nume</label>
			                <input type="text" class="form-control" id="nume" name="nume" value="<%= userDetails.get("Nume") %>" required>
			            </div>
			            <div class="col-md-6">
			                <label for="prenume" class="form-label">Prenume</label>
			                <input type="text" class="form-control" id="prenume" name="prenume" value="<%= userDetails.get("Prenume") %>" required>
			            </div>
			        </div>
			        
			        <!-- Email -->
			        <div class="row mb-3">
			            <div class="col-md-12">
			                <label for="email" class="form-label">Email</label>
			                <input type="email" class="form-control" id="email" name="email" value="<%= userDetails.get("Email") %>" required>
			            </div>
			        </div>
			        
			        <!-- Parola veche și telefon -->
			        <div class="row mb-3">
			            <div class="col-md-6">
			                <label for="parolaVeche" class="form-label">Parola Veche</label>
			                <input type="password" class="form-control" id="parolaVeche" name="parolaVeche" value="<%= userDetails.get("Parola") %>" readonly>
			            </div>
			            <div class="col-md-6">
			                <label for="telefon" class="form-label">Telefon</label>
			                <input type="text" class="form-control" id="telefon" name="telefon" value="<%= userDetails.get("Telefon") %>" required>
			            </div>
			        </div>
			        <!-- parola noua -->
			        <div class="row mb-3">
			            <div class="col-md-12">
			                <label for="parolaNoua" class="form-label">Parola Noua</label>
			                <input type="password" class="form-control" id="parolaNoua" name="parolaNoua" placeholder="Introduceti parola noua (doar daca doriti sa o schimbati)">
			            </div>
			        </div>
			        
			        <!-- Oraș și Județ -->
			        <div class="row mb-3">
			            <div class="col-md-6">
			                <label for="oras" class="form-label">Oras</label>
			                <input type="text" class="form-control" id="oras" name="oras" value="<%= userDetails.get("Oras") %>" required>
			            </div>
			            <div class="col-md-6">
			                <label for="judet" class="form-label">Judet</label>
			                <input type="text" class="form-control" id="judet" name="judet" value="<%= userDetails.get("Judet") %>" required>
			            </div>
			        </div>
			        
			        <!-- Strada și Număr -->
			        <div class="row mb-3">
			            <div class="col-md-6">
			                <label for="strada" class="form-label">Strada</label>
			                <input type="text" class="form-control" id="strada" name="strada" value="<%= userDetails.get("Strada") %>" required>
			            </div>
			            <div class="col-md-6">
			                <label for="numar" class="form-label">Numar</label>
			                <input type="text" class="form-control" id="numar" name="numar" value="<%= userDetails.get("Numar") %>" required>
			            </div>
			        </div>
			        
			        <!-- Submit Button -->
			        <div class="row">
			            <div class="col-md-12 text-center">
			                <button type="submit" class="btn btn-success w-100">Salveaza</button>
			            </div>
			        </div>
			    </form>
			</div>


	        </div>
	    </div>
	</div>
	
	<div class="notification" id="successNotification">
	    Modificarile au fost salvate cu succes!
	</div>
	


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
	    document.querySelector("form").addEventListener("submit", function (event) {
	        event.preventDefault(); // Previne submit-ul implicit al formularului
	
	        // Afișează notificarea
	        const notification = document.getElementById("successNotification");
	        notification.style.display = "block";
	
	        // După 3 secunde, ascunde notificarea și redirecționează
	        setTimeout(() => {
	            notification.style.display = "none";
	            window.location.href = "/ddeStore/user/account.jsp";
	        }, 3000);
	    });
	</script>

    
</body>
</html>
