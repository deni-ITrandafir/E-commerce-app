<%
    // Verifică dacă utilizatorul este autentificat
    if (session.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String categoryId = request.getParameter("id");
    String categoryName = request.getParameter("name");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editeaza Categorie</title>
    <link href="/ddeStore/css/index.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    
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
                    <a class="nav-link text-white" href="/ddeStore/products.jsp">Produse</a>
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
                <h2>Editeaza Categorie</h2>
                <form action="/ddeStore/CategoryServlet" method="post" class="mt-4">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="categoryId" value="<%= categoryId %>">
                    <div class="mb-3">
                        <label for="categoryName" class="form-label">Nume Categorie</label>
                        <input type="text" class="form-control" id="categoryName" name="categoryName" value="<%= categoryName %>" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Salveaza</button>
                    <a href="/ddeStore/CategoryServlet" class="btn btn-secondary">Anuleaza</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
