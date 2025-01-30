<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    Map<String, String> orderDetails = (Map<String, String>) request.getAttribute("orderDetails");
    List<Map<String, String>> productDetails = (List<Map<String, String>>) request.getAttribute("productDetails");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalii Comanda</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/ddeStore/css/index.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .invoice-container {
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 10px;
            background: #f9f9f9;
        }
        .invoice-header {
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        .table-products {
            margin-top: 20px;
        }
        .table-products th, .table-products td {
            text-align: center;
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
                <div class="invoice-container">
                    <div class="invoice-header text-left">
                        <h2>Factura Comanda</h2>
                        <% if (orderDetails != null && !orderDetails.isEmpty()) { %>
                            <p><strong>ID Comanda:</strong> <%= orderDetails.get("ID_Comanda") %></p>
                            <p><strong>Data Comanda:</strong> <%= orderDetails.get("DataComanda") %></p>
                            <p><strong>Status:</strong> <%= orderDetails.get("Status") %></p>
                            <p><strong>Total:</strong> <%= orderDetails.get("Total") %> RON</p>
                        <% } %>
                    </div>

                    <h4>Informatii Client</h4>
                    <% if (orderDetails != null && !orderDetails.isEmpty()) { %>
                        <p><strong>Nume:</strong> <%= orderDetails.get("NumePrenume") %></p>
                        <p><strong>Telefon:</strong> <%= orderDetails.get("Telefon") %></p>
                        <p><strong>Email:</strong> <%= orderDetails.get("Email") %></p>
                        <p><strong>Adresa:</strong> <%= orderDetails.get("Adresa") %></p>
                    <% } else { %>
                        <p>Nu s-au gasit detalii despre client.</p>
                    <% } %>

                    <h4>Produse Achizitionate</h4>
                    <table class="table table-bordered table-products">
                        <thead class="table-dark">
                            <tr>
                                <th>Denumire Produs</th>
                                <th>Cantitate</th>
                                <th>Pret Unitar (RON)</th>
                                <th>Total (RON)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (productDetails != null && !productDetails.isEmpty()) { %>
                                <% for (Map<String, String> product : productDetails) { %>
                                    <tr>
                                        <td><%= product.get("DenumireProdus") %></td>
                                        <td><%= product.get("Cantitate") %></td>
                                        <td><%= product.get("PretUnitar") %></td>
                                        <td><%= product.get("TotalProdus") %></td>
                                    </tr>
                                <% } %>
                            <% } else { %>
                                <tr>
                                    <td colspan="4" class="text-center">Nu exista produse achizitionate.</td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
