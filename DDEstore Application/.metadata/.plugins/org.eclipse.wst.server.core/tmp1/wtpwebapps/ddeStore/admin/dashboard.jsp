<%
    if (session == null || session.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/ddeStore/css/index.css" rel="stylesheet">
    <style>
    	.card-height {
		    height: 200px;  
		}
		
		.card-body-centered {
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    justify-content: center;
		    text-align: center;
		    height: 100%;  
		}
		.card-title{
			font-size: 24px;
		}
		.card-text {
		    display: flex;
		    align-items: center;
		    justify-content: center;
		    width: 100px;  
		    height: 100px; 
		    background-color: #ffffff;  
		    color: #000;  
		    border-radius: 50%;  
		    font-size: 28px;  
		    font-weight: bold;
		    margin-bottom: 10px;  
		}
		.btn-logout{
			width: 200px;
			margin-top: 300px;
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

        <!-- Content -->
        <div class="content flex-grow-1 p-4">
            <h2>Dashboard</h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="card text-white bg-primary mb-3 card-height">
                        <div class="card-body card-body-centered">
                            <p class="card-text"><%= request.getAttribute("totalUtilizatori") %></p>
                            <h5 class="card-title">Total Utilizatori</h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-success mb-3 card-height">
					    <div class="card-body card-body-centered">
					        <div class="card-text"><%= request.getAttribute("totalProduse") %></div>
					        <h5 class="card-title">Total Produse</h5>
					    </div>
					</div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-info mb-3 card-height">
                        <div class="card-body card-body-centered">
                            <p class="card-text"><%= request.getAttribute("totalCategorii") %></p>
                            <h5 class="card-title">Total Categorii</h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="card text-white bg-warning mb-3 card-height">
                        <div class="card-body card-body-centered">
                            <p class="card-text"><%= request.getAttribute("totalComenziAn") %></p>
                            <h5 class="card-title">Comenzi Anul Curent</h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card text-white bg-danger mb-3 card-height">
                        <div class="card-body card-body-centered">
                            <p class="card-text"><%= request.getAttribute("totalComenziAzi") %></p>
                            <h5 class="card-title">Comenzi Astazi</h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
			    <div class="col-md-4">
			        <div class="card text-white" style="background-color: #9966cc; ">
				        <div class="card-header">
				            <h5>Top Categorii de Produse cele mai vandute (Luna Trecuta)</h5>
				        </div>
				        <div class="card-body">
							<ul class="list-group">
							    <% 
							        List<Map<String, String>> topCategoriesLastMonth = 
							            (List<Map<String, String>>) request.getAttribute("topCategoriesLastMonth");
							        
							        if (topCategoriesLastMonth != null && !topCategoriesLastMonth.isEmpty()) { 
							            for (Map<String, String> category : topCategoriesLastMonth) {
							                String numeCategorie = category.get("NumeCategorie") != null ? category.get("NumeCategorie") : "Fără categorie";
							                String totalVandut = category.get("TotalVandut") != null ? category.get("TotalVandut") : "0";
							    %>
							        <li class="list-group-item d-flex justify-content-between align-items-center">
							            <%= numeCategorie %>
							            <span class="badge bg-primary rounded-pill"><%= totalVandut %></span>
							        </li>
							    <% 
							            } 
							        } else { 
							    %>
							        <div class="alert alert-warning">Nu există date disponibile pentru luna trecută.</div>
							    <% } %>
							</ul>

				        </div>
				    </div>

			    </div >
			    
			    
				<div class="col-md-4">
			    	<div class="card text-white" style="background-color: #99cc00; height: 230px;">
				        <div class="card-header text-center">
				            <h6>Top 3 Utilizatori cu Cele Mai Multe Comenzi Verificate (luna trecuta)</h6>
				        </div>
			        <div class="card-body">
			            <%
			                // Obține lista transmisă de servlet
			                List<Map<String, String>> topUsersDecember = (List<Map<String, String>>) request.getAttribute("topUsersDecember");
			                if (topUsersDecember != null && !topUsersDecember.isEmpty()) {
			            %>
			                <table class="table table-sm text-white">
			                    <thead>
			                        <tr>
			                            <th>Nume Complet</th>
			                            <th>Total Comenzi</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                        <%
			                            // Iterează prin lista și afișează datele
			                            for (Map<String, String> user : topUsersDecember) {
			                        %>
			                        <tr>
			                            <td><%= user.get("NumeComplet") %></td>
			                            <td><%= user.get("TotalComenzi") %> comenzi</td>
			                        </tr>
			                        <%
			                            }
			                        %>
			                    </tbody>
			                </table>
			            <%
			                } else {
			            %>
			                <p class="text-center">Nu există date despre utilizatori pentru luna trecuta.</p>
			            <%
			                }
			            %>
			        </div>
			    </div>
			</div>  
			<div class="col-md-4">
			    <div class="card text-white card-height"
			    		style="background-color: orange !important; height: 230px;">
			        <div class="card-body">
			            <h5 class="card-title"><b>Cea Mai Profitabila Luna</b></h5>
			            <% Map<String, String> mostProfitableMonth = (Map<String, String>) request.getAttribute("mostProfitableMonth"); %>
			            <% if (mostProfitableMonth != null && !mostProfitableMonth.isEmpty()) { %>
			                <p>Luna: <%= mostProfitableMonth.get("Luna") %></p>
			                <p>Vanzari Totale: <%= mostProfitableMonth.get("TotalVanzari") %> RON</p>
			                <p>Din <%= mostProfitableMonth.get("TotalComenzi") %> Comenzi</p>
			            <% } else { %>
			                <p>Nu exista date disponibile.</p>
			            <% } %>
			        </div>
			    </div>
			</div>
        </div>
    </div>
</body>
</html>
