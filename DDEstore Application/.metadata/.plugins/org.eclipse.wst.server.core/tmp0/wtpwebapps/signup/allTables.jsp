<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.Entry" %>

<%
    Map<String, List<Map<String, String>>> allTablesData = 
        (Map<String, List<Map<String, String>>>) request.getAttribute("allTablesData");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Tables</title>
    
    
    
<link rel="stylesheet" href="css/bonus.css">



</head>
<body id="page-top">



	<!-- Navigation-->
	<div class="navbar">
    <div class="navbar-logo">
        
        <a  href="index.jsp">
			<img src="./assets/logo2.jpg" >
		</a>
        
    </div>
    <div class="navbar-links">
        <a href="allTables">All Tables</a>
        <a href="logout">Logout</a>
        <div class="navbar-user"><%=session.getAttribute("name") %></div>
    </div>
</div>
	
	
	
	
	
	<%-- 
	<nav
		class="navbar navbar-expand-lg text-uppercase fixed-top"
		id="mainNav"
		style="background-color: black; color: #ffffff;">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">
			    <img src="./assets/logo2.jpg"  style="width: 20%;">
			</a>
			
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
				  
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="allTables">All Tables</a></li>
				<!--	<li class="nav-item mx-0 mx-lg-1"><
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#about">About</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#contact">Contact</a></li>-->
					
					
					<li class=" mx-0 mx-lg-1 text-white"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="logout"> Logout </a></li>
					<li class=" mx-0 mx-lg-1"
						style="background-color: #CC0066; border-radius:50px; "><a
						class="nav-link py-3 px-3 px-lg-5 rounded-lg" ><%=session.getAttribute("name") %></a></li>
					
				</ul>
			</div>
		</div>
	</nav>
	 --%>
	
    <h1 style="text-align: center; ">All Tables Data</h1>

    <% if (allTablesData != null && !allTablesData.isEmpty()) { %>
        <% for (Map.Entry<String, List<Map<String, String>>> entry : allTablesData.entrySet()) { %>
            <h2 style="text-align: center; ">Table: <%= entry.getKey() %></h2>
            <table>
                <thead>
                    <% if (!entry.getValue().isEmpty()) { %>
                        <tr>
                            <% for (String column : entry.getValue().get(0).keySet()) { %>
                                <th><%= column %></th>
                            <% } %>
                        </tr>
                    <% } %>
                </thead>
                <tbody>
                    <% for (Map<String, String> row : entry.getValue()) { %>
                        <tr>
                            <% for (String value : row.values()) { %>
                                <td><%= value %></td>
                            <% } %>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
    <% } else { %>
        <p>No data available in the tables.</p>
    <% } %>
</body>
</html>
