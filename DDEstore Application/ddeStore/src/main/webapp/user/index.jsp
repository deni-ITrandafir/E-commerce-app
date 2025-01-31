
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %>


<%
   Map<String, List<String>> filters = (Map<String, List<String>>) request.getAttribute("filters");
   List<Map<String, String>> products = (List<Map<String, String>>) request.getAttribute("products");
%>
<% 
    List<Integer> favoriteProductIds = (List<Integer>) request.getAttribute("favoriteProductIds");
%>
<%
    List<Map<String, String>> cartProducts = (List<Map<String, String>>) request.getAttribute("cartProducts");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DDEstore</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/ddeStore/css/user.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <style>
	    .popup {
	    position: fixed;
	    bottom: 20px;
	    right: 20px;
	    background-color: #4caf50;
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
		
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand" href="/ddeStore/user/index.jsp">
                <img src="/ddeStore/assets/logo2.jpg" alt="Logo" style="width: 130px;">
            </a>
            <form class="d-flex w-50 position-relative">
			    <input class="form-control me-2" type="search" id="search" name="query" placeholder="Cauta produse..." aria-label="Search" onkeyup="searchProducts(this.value)">
			    <button class="btn buton-cauta position-absolute end-0 py-1 px-4"  type="submit" 
			    		style="height: 100%; border-radius: 0px 20px 20px 0px; margin-right: 7px; background-color: #343737; color: white;">
			        <svg xmlns="http://www.w3.org/2000/svg" style="width:24px;" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor">
			            <path strokeLinecap="round" strokeLinejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
			        </svg>
			    </button>
			</form>

			<div id="autocomplete-list" class="autocomplete-items"></div>
            <ul class="navbar-nav mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/ddeStore/CartServlet">
                        <svg xmlns="http://www.w3.org/2000/svg" style="width:30px; height:30px;" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" class="size-6">
                          <path strokeLinecap="round" strokeLinejoin="round" d="M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z" />
                        </svg>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/ddeStore/FavoritesServlet">
					    <svg xmlns="http://www.w3.org/2000/svg" style="width:30px; height:30px;" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" >
					        <path stroke-linecap="round" stroke-linejoin="round" d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
					    </svg>
					</a>

                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <svg xmlns="http://www.w3.org/2000/svg" style="width:30px; height:30px;" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" class="size-6">
                          <path strokeLinecap="round" strokeLinejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                        </svg>
                        <%= session.getAttribute("name") %>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/ddeStore/user/account.jsp">Cont</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="/ddeStore/logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container-fluid d-flex justify-content-center align-items-center" style="margin-top: 100px; ">
        <div class="row">
            <!-- Sidebar Filters -->
            <div class="col-filtre">
                <div class="bg-light p-4">
                    <h5>Filtre</h5>
                    <form id="filterForm" action="/ddeStore/ProductFilterServlet" method="get">
                        <!-- Categorie -->
                        <div class="mb-3">
                            <h6>Categorie</h6>
                            <% if (filters != null && filters.containsKey("categories")) {
                                List<String> selectedCategories = request.getParameterValues("categories") != null ? Arrays.asList(request.getParameterValues("categories")) : new ArrayList<>();
                                for (String category : filters.get("categories")) { %>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="categories" value="<%= category %>" 
                                            <% if (selectedCategories.contains(category)) { %>checked<% } %>>
                                        <label class="form-check-label"><%= category %></label>
                                    </div>
                            <% }} %>
                        </div>

                        <!-- Brand -->
                        <div class="mb-3">
                            <h6>Brand</h6>
                            <% if (filters != null && filters.containsKey("brands")) {
                                List<String> selectedBrands = request.getParameterValues("brands") != null ? Arrays.asList(request.getParameterValues("brands")) : new ArrayList<>();
                                for (String brand : filters.get("brands")) { %>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="brands" value="<%= brand %>" 
                                            <% if (selectedBrands.contains(brand)) { %>checked<% } %>>
                                        <label class="form-check-label"><%= brand %></label>
                                    </div>
                            <% }} %>
                        </div>
 						
                        <!-- Memorie -->
                        <div class="mb-3">
                            <h6>Memorie</h6>
                            <% if (filters != null && filters.containsKey("memories")) {
                                List<String> selectedMemories = request.getParameterValues("memories") != null ? Arrays.asList(request.getParameterValues("memories")) : new ArrayList<>();
                                for (String memory : filters.get("memories")) { %>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="memories" value="<%= memory %>" 
                                            <% if (selectedMemories.contains(memory)) { %>checked<% } %>>
                                        <label class="form-check-label"><%= memory %></label>
                                    </div>
                            <% }} %>
                        </div>

                        <!-- Culoare -->
                        <div class="mb-3">
                            <h6>Culoare</h6>
                            <% if (filters != null && filters.containsKey("colors")) {
                                List<String> selectedColors = request.getParameterValues("colors") != null ? Arrays.asList(request.getParameterValues("colors")) : new ArrayList<>();
                                for (String color : filters.get("colors")) { %>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="colors" value="<%= color %>" 
                                            <% if (selectedColors.contains(color)) { %>checked<% } %>>
                                        <label class="form-check-label"><%= color %></label>
                                    </div>
                            <% }} %>
                        </div>

                        <!-- Price -->
                        <div class="mb-3">
                            <label for="priceMin" class="form-label">Pret Minim</label>
                            <input type="number" id="priceMin" name="priceMin" class="form-control" value="<%= request.getParameter("priceMin") != null ? request.getParameter("priceMin") : "" %>">
                            <label for="priceMax" class="form-label">Pret Maxim</label>
                            <input type="number" id="priceMax" name="priceMax" class="form-control" value="<%= request.getParameter("priceMax") != null ? request.getParameter("priceMax") : "" %>">
                        </div>
                        <button type="submit" class="btn btn-success w-100">Aplica filtre</button>
                    </form>
                </div>
            </div>
            
            

            <!-- Products Section -->
			<div class="col-md-9" style="margin-right:20px;">
			    <div class="row">
				    <!-- Best seller din ultimele 6 luni -->
					<h4 class="text-left p-2"><b>Best seller la Telefoane din ultimele 6 luni</b></h4>
					<div class="row">
						    <% 
						        List<Map<String, String>> bestSellingProducts = (List<Map<String, String>>) request.getAttribute("bestSellingProducts");
						        if (bestSellingProducts != null && !bestSellingProducts.isEmpty()) {
						            for (Map<String, String> product : bestSellingProducts) { 
						     %>
					        <div class="col-md-4">
					            <div class="card mb-4">
					                <!-- Icon pentru favorite -->
									<svg xmlns="http://www.w3.org/2000/svg" style="width:25px; height:25px; cursor: pointer;"
									     fill="<%= favoriteProductIds.contains(Integer.parseInt(product.get("ID_Produs"))) ? "red" : "none" %>"
									     viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="favorite-icon"
									     onclick="toggleFavorite('<%= product.get("ID_Produs") %>', this)">
									    <path stroke-linecap="round" stroke-linejoin="round"
									          d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
									</svg>
					
					                <!-- Imaginea produsului -->
					                <img src="<%= product.get("Imagine") %>" class="card-img-top" alt="<%= product.get("Brand") %>">
					
					                <div class="card-body">
					                    <!-- Titlul produsului -->
					                    <h5 class="card-title"><%= product.get("Brand") %> - <%= product.get("Model") %></h5>
					
					                    <!-- Informații suplimentare -->
					                    <p class="card-text"><%= product.get("Memorie") %>, <%= product.get("Culoare") %></p>
					
					                    <!-- Prețul produsului -->
					                    <p class="card-price">
					                        <%= product.get("Pret") %> Lei
					                    </p>
					
					                    <!-- Buton pentru adăugare în coș -->
										<button class="cart-btn" 
												onclick="addToCart('<%= product.get("ID_Produs") %>')">
										    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" class="size-6">
										        <path strokeLinecap="round" strokeLinejoin="round" d="M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z" />
										    </svg>
										</button>
					                </div>
					            </div>
					        </div>
					    <% 
					            }
					        } else { 
					    %>
					        <p class="text-center">Nu exista produse vandute in categoria Telefoane in ultimele 6 luni.</p>
					    <% } %>
					</div>

			
			    	<!-- Toate produsele -->
			    	<h4 class="text-left p-2"><b>Toate produsele noastre</b></h4>
			        <% if (products != null && !products.isEmpty()) {
			            for (Map<String, String> product : products) { %>
			                <div class="col-md-4">
			                    <div class="card mb-4">
			                        <!-- Icon pentru favorite -->
									<svg xmlns="http://www.w3.org/2000/svg" style="width:25px; height:25px; cursor: pointer;"
									     fill="<%= favoriteProductIds.contains(Integer.parseInt(product.get("ID_Produs"))) ? "red" : "none" %>"
									     viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="favorite-icon"
									     onclick="toggleFavorite('<%= product.get("ID_Produs") %>', this)">
									    <path stroke-linecap="round" stroke-linejoin="round"
									          d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
									</svg>
			                        <!-- Imaginea produsului -->
			                        <img src="<%= product.get("Imagine") %>" class="card-img-top" alt="<%= product.get("Brand") %>">
			
			                        <div class="card-body">
			                            <!-- Titlul produsului -->
			                            <h5 class="card-title"><%= product.get("Brand") %> - <%= product.get("Model") %></h5>
			
			                            <!-- Informații suplimentare -->
			                            <p class="card-text"><%= product.get("Memorie") %>, <%= product.get("Culoare") %></p>
			
			                            <!-- Prețul produsului -->
			                            <p class="card-price">
			                                <%= product.get("Pret") %><sup></sup> Lei
			                            </p>
			
			                            <!-- Buton pentru adăugare în coș -->
										<button class="cart-btn" 
												onclick="addToCart('<%= product.get("ID_Produs") %>')">
										    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" class="size-6">
										        <path strokeLinecap="round" strokeLinejoin="round" d="M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z" />
										    </svg>
										</button>




			                        </div>
			                    </div>
			                </div>
			        <% }} else { %>
			            <p class="text-center">Nu exista produse pentru filtrele selectate.</p>
			        <% } %>
			        
			        
			        
			        
<!-- Recomandări produse -->
<h4 class="text-left p-2"><b>Cumpara din nou</b></h4>
<div class="row">
    <% 
        List<Map<String, String>> recommendedProducts = (List<Map<String, String>>) request.getAttribute("recommendedProducts");
        if (recommendedProducts != null && !recommendedProducts.isEmpty()) {
            for (Map<String, String> product : recommendedProducts) { 
     %>
    <div class="col-md-4">
        <div class="card mb-4">
            <!-- Icon pentru favorite -->
            <svg xmlns="http://www.w3.org/2000/svg" style="width:25px; height:25px; cursor: pointer;"
                 fill="<%= favoriteProductIds.contains(Integer.parseInt(product.get("ID_Produs"))) ? "red" : "none" %>"
                 viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="favorite-icon"
                 onclick="toggleFavorite('<%= product.get("ID_Produs") %>', this)">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
            </svg>

            <!-- Imaginea produsului -->
            <img src="<%= product.get("Imagine") %>" class="card-img-top" alt="<%= product.get("Brand") %>">

            <div class="card-body">
                <!-- Titlul produsului -->
                <h5 class="card-title"><%= product.get("Brand") %> - <%= product.get("Model") %></h5>

                <!-- Informații suplimentare -->
                <p class="card-text"><%= product.get("Memorie") %>, <%= product.get("Culoare") %></p>

                <!-- Prețul produsului -->
                <p class="card-price">
                    <%= product.get("Pret") %> Lei
                </p>

                <!-- Buton pentru adăugare în coș -->
                <button class="cart-btn" 
                        onclick="addToCart('<%= product.get("ID_Produs") %>')">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" class="size-6">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z" />
                    </svg>
                </button>
            </div>
        </div>
    </div>
    <% 
            }
        } else { 
    %>
    <p class="text-center">Nu există produse recomandate bazate pe achizițiile tale.</p>
    <% } %>
</div>
			        
			        
			        
			        
			        
			    </div>
			</div>

        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/ddeStore/js/user.js"></script>
    <script>
	    function toggleFavorite(productId, iconElement) {
	        // Determină acțiunea pe baza atributului `fill`
	        const currentFill = window.getComputedStyle(iconElement).getPropertyValue("fill");
	        const action = currentFill === "rgb(255, 0, 0)" ? "remove" : "add"; // Verifică dacă este roșu
	
	        // Creează corpul cererii pentru fetch
	        const formData = new URLSearchParams();
	        formData.append("action", action);
	        formData.append("id", productId);
	
	        // Trimite cererea către servlet
	        fetch('/ddeStore/FavoritesServlet', {
	            method: 'POST',
	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	            body: formData.toString(),
	        })
	        .then(response => response.json())
	        .then(data => {
	            if (data.success) {
	                // Actualizează culoarea inimii pe baza acțiunii
	                if (action === "add") {
	                    iconElement.style.fill = "red"; // Setează culoarea ca roșu
	                } else if (action === "remove") {
	                    iconElement.style.fill = "none"; // Setează culoarea ca transparent
	                }
	                showPopup(data.message || (action === "add" ? "Produs adăugat la favorite!" : "Produs eliminat din favorite!"));
	            } else {
	                showPopup(data.message || "Eroare la procesare.");
	            }
	        })
	        .catch(error => {
	            console.error("Eroare:", error);
	            showPopup("A apărut o eroare la server. Încercați din nou.");
	        });
	    }
	
	    function addToCart(productId) {
	        const formData = new URLSearchParams();
	        formData.append("id", productId);
	
	        fetch('/ddeStore/CartAddServlet', {
	            method: 'POST',
	            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	            body: formData.toString(),
	        })
	        .then(response => response.json())
	        .then(data => {
	            if (data.success) {
	                showPopup(data.message || "Produs adăugat în coș!");
	            } else {
	                showPopup(data.message || "Eroare la adăugarea în coș.");
	            }
	        })
	        .catch(error => {
	            console.error("Eroare:", error);
	            showPopup("A apărut o eroare la server. Încercați din nou.");
	        });
	    }
	
	    function showPopup(message) {
	        const popup = document.createElement('div');
	        popup.className = 'popup';
	        popup.textContent = message;
	
	        document.body.appendChild(popup);
	
	        setTimeout(() => {
	            popup.remove();
	        }, 3000); // Popup-ul dispare după 3 secunde
	    }
	
	    
	    
	    
	</script>

</body>
</html>
