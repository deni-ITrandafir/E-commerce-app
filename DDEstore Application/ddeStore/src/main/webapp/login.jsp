<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign In - DDE store</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bonus.css">
<link rel="stylesheet" href="alert/dist/sweetalert.css">
</head>
<body>

<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">

	<div class="background" 
		style="background: url(./assets/bkg-2.jpg) no-repeat center center; background-size: cover; ">
    <div class="login-container">
        <h2 class="login-title">Sign in</h2>
        <form method="post" action="login">
            <div class="input-container">
                <label for="username"></label>
                <input type="text" name="username" id="username" placeholder="Enter your email" />
                <i class="icon zmdi zmdi-account"></i>
            </div>
            <div class="input-container">
                <label for="password"></label>
                <input type="password" name="password" id="password" placeholder="Enter your password" />
                <i class="icon zmdi zmdi-lock"></i>
            </div>
            
            
            <input type="submit" name="signin" id="signin"
					class="btn-login" value="Log in" />
            
        </form>
        <p class="register-text">
            Don't have an account? <a href="registration.jsp">Register</a>
        </p>
    </div>
</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src = "https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
	

<script> 
	var status = document.getElementById("status").value;
	if(status == "failed"){
		swal("Sorry", "Wrong Email or Password", "error"); 
	}
</script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>