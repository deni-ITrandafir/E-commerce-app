<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
    // Verifică dacă utilizatorul este autentificat
    if (session.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adaugă Produs</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/ddeStore/css/index.css" rel="stylesheet">
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

        @keyframes fadeInOut {
            0% { opacity: 0; transform: translateY(20px); }
            10% { opacity: 1; transform: translateY(0); }
            90% { opacity: 1; transform: translateY(0); }
            100% { opacity: 0; transform: translateY(20px); }
        }
    </style>
</head>
<body>
    <div class="d-flex">
        <!-- Sidebar -->
        <div class="sidebar bg-dark text-white p-3">
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
                <h2>Adauga un produs nou</h2>
                <form action="/ddeStore/AddProductServlet" method="post" enctype="multipart/form-data" class="mt-4">
                
                    <!-- Categorie -->
                    <div class="mb-3">
                        <label for="idCategorie" class="form-label">Categorie</label>
                        <select class="form-select" id="idCategorie" name="idCategorie" required>
                            <% 
                                List<Map<String, String>> categories = (List<Map<String, String>>) request.getAttribute("categories");
                                if (categories != null && !categories.isEmpty()) {
                                    for (Map<String, String> category : categories) {
                            %>
                            <option value="<%= category.get("ID_Categorie") %>"><%= category.get("NumeCategorie") %></option>
                            <% 
                                    }
                                } else { 
                            %>
                            <option value="">Nu exista categorii</option>
                            <% } %>
                        </select>
                    </div>

                    <!-- Brand -->
                    <div class="mb-3">
                        <label for="brand" class="form-label">Brand</label>
                        <input type="text" class="form-control" id="brand" name="brand" required>
                    </div>

                    <!-- Model -->
                    <div class="mb-3">
                        <label for="model" class="form-label">Model</label>
                        <input type="text" class="form-control" id="model" name="model" required>
                    </div>

                    <!-- Memorie -->
                    <div class="mb-3">
                        <label for="memorie" class="form-label">Memorie</label>
                        <input type="text" class="form-control" id="memorie" name="memorie" required>
                    </div>

                    <!-- Culoare -->
                    <div class="mb-3">
                        <label for="culoare" class="form-label">Culoare</label>
                        <input type="text" class="form-control" id="culoare" name="culoare" required>
                    </div>

                    <!-- Pret -->
                    <div class="mb-3">
                        <label for="pret" class="form-label">Pret</label>
                        <input type="number" step="0.01" class="form-control" id="pret" name="pret" required>
                    </div>

                    <!-- Stoc -->
                    <div class="mb-3">
                        <label for="stoc" class="form-label">Stoc</label>
                        <input type="number" class="form-control" id="stoc" name="stoc" required>
                    </div>
                    
                    <!-- Imagini -->
					<div class="mb-3">
					    <label for="imagini" class="form-label">Imagini</label>
					    <input type="file" class="form-control" id="imagini" name="imagini" multiple accept="image/*">
					    <small class="form-text text-muted">Poti selecta mai multe imagini.</small>
					</div>

                    <!-- Save Button -->
                    <button type="submit" class="btn btn-primary">Salveaza</button>
                    <a href="/ddeStore/ProductServlet" class="btn btn-secondary">Anuleaza</a>
                </form>
            </div>
        </div>
    </div>
    
    
    <!-- Popup -->
    <% String success = (String) request.getAttribute("success"); %>
    <% if (success != null) { %>
        <div class="popup"><%= success %></div>
        <script>
            setTimeout(() => {
                window.location.href = "/ddeStore/ProductServlet";
            }, 3000); // Redirectează după 3 secunde
        </script>
    <% } %>
</body>
</html>
