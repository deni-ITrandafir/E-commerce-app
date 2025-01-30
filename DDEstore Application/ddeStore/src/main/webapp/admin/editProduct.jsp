<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
    Map<String, String> product = (Map<String, String>) request.getAttribute("product");
    List<Map<String, String>> categories = (List<Map<String, String>>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editare Produs</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/ddeStore/css/index.css" rel="stylesheet">
    <style>
        .preview-img {
            width: 50px;
            height: 50px;
            object-fit: cover;
            margin-right: 10px;
        }
        .remove-btn {
            background-color: red;
            color: white;
            border: none;
            border-radius: 3px;
            padding: 2px 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
	<div class="d-flex">
	<!-- Sidebar -->
        <div class="sidebar bg-dark text-white">
            <div class="sidebar-header text-center py-4 ">
                <img src="/ddeStore/assets/logo2.jpg" alt="Logo" class="img-fluid rounded-circle  mb-2" style="width: 80px;">
                <h5>Admin Panel</h5>
            </div>
            <ul class="nav flex-column ">
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
	    <div class="container mt-5">
	        <h2>Editare Produs</h2>
	        <form action="/ddeStore/EditProductServlet" method="post" enctype="multipart/form-data">
	            <input type="hidden" name="idProdus" value="<%= product.get("ID_Produs") %>">
	
	            <!-- Categorie -->
	            <div class="mb-3">
	                <label for="idCategorie" class="form-label">Categorie</label>
	                <select class="form-select" id="idCategorie" name="idCategorie" required>
	                    <% 
	                        for (Map<String, String> category : categories) {
	                    %>
	                    <option value="<%= category.get("ID_Categorie") %>" 
	                        <%= product.get("ID_Categorie").equals(category.get("ID_Categorie")) ? "selected" : "" %>>
	                        <%= category.get("NumeCategorie") %>
	                    </option>
	                    <% } %>
	                </select>
	            </div>
	
	            <!-- Brand -->
	            <div class="mb-3">
	                <label for="brand" class="form-label">Brand</label>
	                <input type="text" class="form-control" id="brand" name="brand" value="<%= product.get("Brand") %>" required>
	            </div>
	
	            <!-- Model -->
	            <div class="mb-3">
	                <label for="model" class="form-label">Model</label>
	                <input type="text" class="form-control" id="model" name="model" value="<%= product.get("Model") %>" required>
	            </div>
	
	            <!-- Memorie -->
	            <div class="mb-3">
	                <label for="memorie" class="form-label">Memorie</label>
	                <input type="text" class="form-control" id="memorie" name="memorie" value="<%= product.get("Memorie") %>" required>
	            </div>
	
	            <!-- Culoare -->
	            <div class="mb-3">
	                <label for="culoare" class="form-label">Culoare</label>
	                <input type="text" class="form-control" id="culoare" name="culoare" value="<%= product.get("Culoare") %>" required>
	            </div>
	
	            <!-- Pret -->
	            <div class="mb-3">
	                <label for="pret" class="form-label">Preț</label>
	                <input type="number" step="0.01" class="form-control" id="pret" name="pret" value="<%= product.get("Pret") %>" required>
	            </div>
	
	            <!-- Stoc -->
	            <div class="mb-3">
	                <label for="stoc" class="form-label">Stoc</label>
	                <input type="number" class="form-control" id="stoc" name="stoc" value="<%= product.get("Stoc") %>" required>
	            </div>
	
	            <!-- Imagini -->
	            <div class="mb-3">
	                <label class="form-label">Imagini</label>
	                <div>
	                    <% if (product.get("Imagine") != null && !product.get("Imagine").isEmpty()) { 
	                        String[] images = product.get("Imagine").split(",");
	                        for (String image : images) {
	                    %>
	                    <div class="d-flex align-items-center mb-2">
	                        <img src="/ddeStore/uploads/<%= image %>" class="preview-img" alt="<%= image %>">
	                        <button type="button" class="remove-btn">Sterge</button>
	                    </div>
	                    <% } } %>
	                </div>
	                <input type="file" class="form-control" id="imagini" name="imagini" multiple>
	            </div>
	
	            <button type="submit" class="btn btn-primary">Salveaza</button>
	            <a href="/ddeStore/ProductServlet" class="btn btn-secondary">Anuleaza</a>
	        </form>
	    </div>
	    
	    <%
		    String success = (String) request.getAttribute("success");
		    if (success != null) {
		%>
		    <div class="alert alert-success alert-dismissible show" role="alert" id="success-alert" 
		        style="position: fixed; bottom: 20px; right: 20px; z-index: 1050;">
		        <%= success %>
		    </div>
		    <script>
		        setTimeout(() => {
		            const alert = document.getElementById('success-alert');
		            if (alert) {
		                alert.classList.remove('show');
		                alert.classList.add('fade');
		                setTimeout(() => alert.remove(), 300); // Elimină elementul din DOM
		            }
		        }, 3000); // Mesajul dispare după 3 secunde
		
		        // Redirecționează după 3 secunde
		        setTimeout(() => {
		            window.location.href = "/ddeStore/ProductServlet";
		        }, 3000);
		    </script>
		<%
		    }
		%>
	</div>
</body>
</html>
