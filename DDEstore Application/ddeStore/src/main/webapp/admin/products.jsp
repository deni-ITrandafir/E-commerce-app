<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    // Verifică dacă utilizatorul este autentificat
    if (session.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Obține lista de produse transmisă de servlet
    List<Map<String, String>> products = (List<Map<String, String>>) request.getAttribute("products");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produse</title>
    <link href="/ddeStore/css/index.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <div class="container">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h2>Produse</h2>
                    <a href="/ddeStore/AddProductServlet" class="btn btn-primary">
                    	<svg xmlns="http://www.w3.org/2000/svg" style="width:20px; heigh:20px;" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
						  <path strokeLinecap="round" strokeLinejoin="round" d="M12 9v6m3-3H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
						</svg>
                    	Adauga Produs Nou
                    </a>
                </div>

                <!-- Tabel cu produsele -->
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <!--  <th>ID</th>-->
                            <th>Categorie</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Memorie</th>
                            <th>Culoare</th>
                            <th>Pret</th>
                            <th>Stoc</th>
                            <th>Actiuni</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        if (products == null) {
                    %>
                        <tr>
                            <td colspan="9" class="text-center">Eroare: Lista produselor este NULL.</td>
                        </tr>
                    <%
                        } else if (products.isEmpty()) {
                    %>
                        <tr>
                            <td colspan="9" class="text-center">Nu exista produse disponibile.</td>
                        </tr>
                    <%
                        } else {
                            for (Map<String, String> product : products) {
                    %>
                        <tr>
                            <!-- <td><%= product.get("ID_Produs") %></td> -->
                            <td><%= product.get("NumeCategorie") %></td>
                            <td><%= product.get("Brand") %></td>
                            <td><%= product.get("Model") %></td>
                            <td><%= product.get("Memorie") %></td>
                            <td><%= product.get("Culoare") %></td>
                            <td><%= product.get("Pret") %></td>
                            <td><%= product.get("Stoc") %></td>
                            <td>
                                <a href="/ddeStore/EditProductServlet?view=edit&id=<%= product.get("ID_Produs") %>" class="btn btn-warning btn-sm">
                                	<svg xmlns="http://www.w3.org/2000/svg" style="width:20px; heigh:20px" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
									  <path strokeLinecap="round" strokeLinejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
									</svg>
                                	Edit
                                </a>
                            
                                <form action="/ddeStore/ProductServlet" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="idProdus" value="<%= product.get("ID_Produs") %>">
                                    <button type="submit" class="btn btn-danger btn-sm">
                                    	<svg xmlns="http://www.w3.org/2000/svg" style="width:20px; heigh:20px" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
										  <path strokeLinecap="round" strokeLinejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
										</svg>
                                    	Sterge
                                    </button>
                                </form>
                                
                            </td>
                        </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    

    
    
</body>
</html>
