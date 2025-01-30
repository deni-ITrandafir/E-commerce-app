<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
    List<Map<String, String>> cartProducts = (List<Map<String, String>>) request.getAttribute("cartProducts");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cos de cumparaturi</title>
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
		.btn-remove-circle {
		    display: inline-flex;
		    justify-content: center;
		    align-items: center;
		    width: 40px;
		    height: 40px;
		    background-color: #dc3545;  
		    color: #fff;  
		    border-radius: 50%; 
		    cursor: pointer;
		    transition: background-color 0.3s;
		    padding: 10px;
		}
		
		.btn-remove-circle:hover {
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
        .cart-item {
		    display: flex;
		    align-items: center;
		    justify-content: space-between;
		    border: none;  
		    background-color: #f8f9fa;  
		    border-radius: 10px;  
		    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);  
		    padding: 15px;
		    margin-bottom: 10px;  
		}
        .cart-item:last-child {
            border-bottom: none;
        }

        .cart-item img {
            max-width: 100px;
            margin-right: 20px;
        }

        .cart-item-details {
            flex: 2;
        }

        .cart-item-quantity {
            display: flex;
            align-items: center;
            gap: 10px;
            flex: 1;
        }

        .quantity-btn {
            background-color: #f0f0f0;
            border: 1px solid #ddd;
            padding: 5px 10px;
            cursor: pointer;
        }

        .quantity-btn:hover {
            background-color: #ddd;
        }

        .cart-item-actions {
            display: flex;
            align-items: center;
            flex: 1;
            justify-content: center;
        }

        .btn-remove {
            color: red;
            cursor: pointer;
            text-align: center;
        }

        .btn-remove:hover {
            text-decoration: underline;
        }
        .card {
		    margin-top: 60px;
		    gap: 20px; 
		}
    </style>
</head>
<body>

    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-2 bg-dark text-light p-3" style="height: 100vh;">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/ddeStore/ProductFilterServlet">
                        <img src="/ddeStore/assets/logo2.jpg" alt="Logo" style="width: 150px; height:auto; margin-bottom:25px;">
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
                <div class="text-center" style="margin-top: 320px;">
                    <a href="/ddeStore/ProductFilterServlet" class="btn btn-warning w-100">Inapoi la produse</a>
                </div>
                <div class="text-center" style="margin-top: 10px;">
                    <a href="/ddeStore/logout" class="btn btn-danger w-100">Logout</a>
                </div>
            </div>

            <!-- Main Content -->
            <div class="col-md-7 p-5">
                <h2 class="mb-4">Cosul tau de cumparaturi</h2>
	            <% if (cartProducts != null && !cartProducts.isEmpty()) { %>
	                <div class="cart-list">
	                    <% for (Map<String, String> product : cartProducts) { %>
	                        <div class="cart-item">
	                            <img src="<%= product.get("image") %>" alt="Produs">
	                            <div class="cart-item-details">
	                                <h5><%= product.get("brand") %> - <%= product.get("model") %></h5>
	                                <p><span class="cart-item-price"><%= product.get("price") %> Lei</span></p>
	                            </div>
	                            <div class="cart-item-quantity">
							        <button class="quantity-btn" onclick="updateQuantity('<%= product.get("id") %>', 'decrease')">-</button>
							        <span><%= product.get("quantity") %></span>
							        <button class="quantity-btn" onclick="updateQuantity('<%= product.get("id") %>', 'increase')">+</button>
							    </div>
							    <div class="cart-item-actions">
							        <span class="btn-remove-circle" onclick="removeFromCart('<%= product.get("id") %>')">
									    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
									        <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
									    </svg>
									</span>
							    </div>
	                        </div>
	                    <% } %>
	                </div>
	            <% } else { %>
	                <p>Cosul tau este gol.</p>
	            <% } %>
            </div>
            
            <div class="col-md-3 p-5">
			    <div class="card">
			        <div class="card-body">
			            <h4 class="card-title">Sumar comanda</h4>
			            <hr>
			            <% 
			                double totalPrice = 0;
			                if (cartProducts != null) {
			                    for (Map<String, String> product : cartProducts) {
			                        totalPrice += Double.parseDouble(product.get("price")) * Integer.parseInt(product.get("quantity"));
			                    }
			                }
			            %>
			            <p class="card-text"><strong>Total:</strong> <span><%= totalPrice %> Lei</span></p>
			            <hr>
			            <a href="/ddeStore/EditUserDetailsServlet?totalPrice=<%= totalPrice %>" class="btn btn-success w-100">Finalizeaza Comanda</a>


			        </div>
			    </div>
			</div>

            
            
        </div>
    </div>

    <script>
    function updateQuantity(productId, action) {
        const formData = new URLSearchParams();
        formData.append("id", productId);
        formData.append("action", action);

        fetch('/ddeStore/CartUpdateServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData.toString(),
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    location.reload(); // Reîncarcă pagina pentru actualizarea cantităților
                } else {
                    alert(data.message || "Eroare la actualizarea cantitătii.");
                }
            })
            .catch(error => {
                console.error("Eroare:", error);
                alert("A aparut o eroare la server. Incercati din nou.");
            });
    }

    function removeFromCart(productId) {
        const formData = new URLSearchParams();
        formData.append("id", productId);
        formData.append("action", "remove");

        fetch('/ddeStore/CartUpdateServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData.toString(),
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    location.reload(); // Reîncarcă pagina pentru eliminarea produsului
                } else {
                    alert(data.message || "Eroare la eliminarea produsului din cos.");
                }
            })
            .catch(error => {
                console.error("Eroare:", error);
                alert("A aparut o eroare la server. Incercati din nou.");
            });
    }

    </script>

</body>
</html>
