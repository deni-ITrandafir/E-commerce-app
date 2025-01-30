<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up - DDE store</title>

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
        <h2 class="login-title">Sign up</h2>
        <form method="post" action="register">
            <div class="input-container">
                <label for="first-name"></label>
                <input type="text" name="first-name" id="first-name" placeholder="Enter your first name" />
                <i class="icon zmdi zmdi-account"></i>
            </div>
            <div class="input-container">
                <label for="last-name"></label>
                <input type="text" name="last-name" id="last-name" placeholder="Enter your last name" />
                <i class="icon zmdi zmdi-account"></i>
            </div>
            <div class="input-container">
                <label for="email"></label>
                <input type="email" name="email" id="email" placeholder="Enter your email" />
                <i class="icon zmdi zmdi-email"></i>
            </div>
            <div class="input-container">
                <label for="pass"></label>
                <input type="password" name="pass" id="pass" placeholder="Enter your password" />
                <i class="icon zmdi zmdi-lock"></i>
            </div>
            <div class="input-container">
                <label for="re_pass"></label>
                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password" />
                <i class="icon zmdi zmdi-lock-outline"></i>
            </div>
            <div class="input-container">
                <label for="contact"></label>
                <input type="text" name="contact" id="contact" placeholder="Enter your phone number" />
                <i class="icon zmdi zmdi-phone"></i>
            </div>
            <!-- <div class="options">
                <label>
                    <input type="checkbox" name="agree-term" id="agree-term" />
                    I agree to all statements in <a href="#" class="term-service">Terms of service</a>
                </label>
            </div> -->
            
            <input type="submit" name="signup" id="signup"
					class=" btn-login" value="Register" />
        </form>
        <p class="register-text">
            Already a member? <a href="login.jsp">Log in</a>
        </p>
    </div>
</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src = "https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
	

<script> 
	var status = document.getElementById("status").value;
	if(status == "success"){
		swal("Congrats", "Account Created Successfully", "success"); 
	}
</script>

</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>