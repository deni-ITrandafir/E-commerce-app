<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%
    String userId = request.getParameter("userId");

    // Cod pentru a obține utilizatorul din baza de date (simulat aici pentru exemplu)
    Map<String, String> user = new HashMap<>();
    user.put("Nume", "Popescu");
    user.put("Prenume", "Ion");
    user.put("Telefon", "0721234567");
    user.put("Email", "ion.popescu@example.com");
    user.put("IsAdmin", "DA");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editare Utilizator</title>
    <link href="/ddeStore/css/index.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="d-flex">
        <!-- Sidebar -->
        <div class="sidebar bg-dark text-white">
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
            <div class="container mt-5">
                <h2>Editare Utilizator</h2>
                <form action="/ddeStore/UserServlet" method="post">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="userId" value="<%= userId %>">
                    <div class="mb-3">
                        <label for="nume" class="form-label">Nume</label>
                        <input type="text" class="form-control" id="nume" name="nume" value="<%= user.get("Nume") %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="prenume" class="form-label">Prenume</label>
                        <input type="text" class="form-control" id="prenume" name="prenume" value="<%= user.get("Prenume") %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="telefon" class="form-label">Telefon</label>
                        <input type="text" class="form-control" id="telefon" name="telefon" value="<%= user.get("Telefon") %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="<%= user.get("Email") %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="isAdmin" class="form-label">Este Admin</label>
                        <select class="form-select" id="isAdmin" name="isAdmin">
                            <option value="DA" <%= "DA".equals(user.get("IsAdmin")) ? "selected" : "" %>>DA</option>
                            <option value="NU" <%= "NU".equals(user.get("IsAdmin")) ? "selected" : "" %>>NU</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Salvează</button>
                    <a href="/ddeStore/UserServlet" class="btn btn-secondary">Anulează</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
