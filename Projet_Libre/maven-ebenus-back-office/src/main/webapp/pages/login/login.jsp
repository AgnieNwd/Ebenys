<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>MY ADMIN INTRA</title>
	<!-- CSS files -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./assets/css/style.css"> 
</head>
<body class="body-login" style="background-color: #007bff80; background: linear-gradient(0deg, rgba(255, 255, 255, 0.075) 0%, #dedede 0%, #007bff80 40%);">
	<form action="${pageContext.request.contextPath}/LoginServlet" method="Post" class="form-signin">
	  <h1 class="h3 mb-3 font-weight-normal">Connexion à My Intra Admin</h1>
	  <label for="inputEmail" class="sr-only">Adresse mail</label>
	  <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>
	  <label for="inputPassword" class="sr-only">Mot de passe</label>
	  <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
	
	  <button class="btn btn-lg btn-primary btn-block" type="submit">Identifiez vous</button>
	</form>
	<footer class="mastfoot mt-auto">
	    <div class="inner-login">
	      <p>My Intra Admin fait avec ♥ par &copy; Us 2020</p>
	    </div>
    </footer>
</body>
</html>