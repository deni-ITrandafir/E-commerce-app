<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    List<Map<String, String>> favoriteProducts = (List<Map<String, String>>) request.getAttribute("favoriteProducts");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista Favorite</title>
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
		.popup {
		    position: fixed;
		    bottom: 20px;
		    right: 20px;
		    background-color: #4caf50; /* Verde pentru succes */
		    color: white;
		    padding: 10px 20px;
		    border-radius: 5px;
		    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
		    z-index: 1000;
		    animation: fadeInOut 3s ease-in-out;
		}
		
		.popup.error {
		    background-color: #f44336; /* Roșu pentru erori */
		}
		
		@keyframes fadeInOut {
		    0% { opacity: 0; transform: translateY(20px); }
		    10% { opacity: 1; transform: translateY(0); }
		    90% { opacity: 1; transform: translateY(0); }
		    100% { opacity: 0; transform: translateY(20px); }
		}
		button{
			margin-left:220px;
			transform: translateY(-150%);

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
	
	        <!-- Main Content -->
	        <div class="col-md-9 ">
	            <div class="container mt-5">
			        <h2 class=" mb-4">Lista Favorite</h2>
			        <div class="row">
			            <% if (favoriteProducts != null && !favoriteProducts.isEmpty()) { %>
			                <% for (Map<String, String> product : favoriteProducts) { %>
			                    <div class="col-md-4" data-product-id="<%= product.get("id") %>">
								    <div class="card mb-4">
								        <img src="<%= product.get("image") %>" class="card-img-top" alt="Produs">
								        <div class="card-body">
								            <h5 class="card-title"><%= product.get("brand") %> - <%= product.get("model") %></h5>
								            <p class="card-text">Pret: <%= product.get("price") %> Lei</p>
								            <button class="btn btn-danger d-flex justify-content-center align-items-center p-2 rounded-circle" 
								            		onclick="removeFavorite('<%= product.get("id") %>')"
								            		style="float: left;" >
								            	<svg xmlns="http://www.w3.org/2000/svg" style="width:20px; heigh:20px;" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
												  <path strokeLinecap="round" strokeLinejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
												</svg>
								            </button>
								        </div>
								    </div>
								</div>

			                <% } %>
			            <% } else { %>
			                <p class="text-center">Nu exista produse in lista de favorite.</p>
			            <% } %>
			        </div>
			    </div>
	        </div>
	    </div>
	</div>

	
	<script>
		function removeFavorite(productId) {
		    const formData = new URLSearchParams();
		    formData.append("action", "remove"); // Adaugă acțiunea de eliminare
		    formData.append("id", productId);
		
		    fetch('/ddeStore/FavoritesServlet', {
		        method: 'POST',
		        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		        body: formData.toString(),
		    })
		    .then(response => response.json())
		    .then(data => {
		        if (data.success) {
		            // Remove the card from the UI
		            const productCard = document.querySelector(`[data-product-id='${productId}']`);
		            if (productCard) {
		                productCard.remove();
		            }
		            showPopup(data.message || "Produs eliminat din favorite.", "success");
		        } else {
		            showPopup(data.message || "Eroare la eliminarea produsului.", "error");
		        }
		    })
		    .catch(error => {
		        console.error("Eroare:", error);
		        showPopup("A apărut o eroare la server. Încercați din nou.", "error");
		    });
		}
		
		function showPopup(message, type) {
		    const popup = document.createElement('div');
		    popup.className = 'popup';
		    if (type === "error") {
		        popup.classList.add("error");
		    }
		    popup.textContent = message;
		
		    document.body.appendChild(popup);
		
		    setTimeout(() => {
		        popup.remove();
		    }, 3000); // Popup-ul dispare după 3 secunde
		}
	
	
	</script>

</body>
</html>
