<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User" %>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.Role" %>
<%@page import="java.util.List" %>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<body>

	<% 
 		User user = (User) request.getSession().getAttribute("thisuser"); 
		String mode = (String) request.getAttribute("mode");
		
		Role roleUpdate = null;

		if (mode == "update") {
			roleUpdate = (Role) request.getAttribute("roleUpdate");
		}
 	%>
     	
<div class="contenu">	
	<nav class="navbar navbar-light" style="background-color: #007bff80;">
	  <a class="navbar-brand">My Admin Intra</a>
	  <div style="display: flex;">
	  	<div style="display: flex; flex-direction: column;">
		  <span class="navbar-text" style="padding-bottom: 0; color: #e3f2fd">
		     <%= user.getPrenom() %> <%= user.getNom() %> 
		  </span>
		  <span style="color: rgba(0,0,0,.5); font-size: 12px;">
		     <%= user.getRole().getIdentifiant() %>
		  </span>
	  	</div>
		<a class="nav-link" href="${pageContext.request.contextPath}/LogoutServlet" style="color: #e3f2fd"><i class="fa fa-sign-out" aria-hidden="true"></i></a>
	  </div>
	</nav>
	
  		<div class="row" style="margin:0;">
  			<div class="col-md-2">
				<nav id="menu-nav" class="nav nav-tabs flex-column" style="margin-top:6px;">
				  <a class="nav-link" href="${pageContext.request.contextPath}/CrudUserServlet">Gestion des Utilisateurs</a>
				  <a class="nav-link" href="${pageContext.request.contextPath}/CrudMatiereServlet">Gestion des Matières</a>
				  <a class="nav-link" href="${pageContext.request.contextPath}/CrudClasseServlet">Gestion des Classes</a>
				  <a class="nav-link active" href="${pageContext.request.contextPath}/CruRoleServlet">Gestion des Roles</a>
				</nav>
			</div>
    		<div class="col-md-9">
				
				<% if (mode == "update") { %>
				<div class="card border-primary mb-3" style="width: 25rem;">
				  <div class="card-body">
				    <h5 class="card-title">Modifier le role <%= roleUpdate.getIdentifiant() %></h5>
				    <form  method="post" action="${pageContext.request.contextPath}/UpdateRoleServlet" id="customer-info-form" class="no-gutters">  
				    	<div style="margin-bottom: 10px">
				    		<label for="validationCustom01">Nom du Role :</label>
			    			<input type="text" class="form-control" id="validationCustom01" value="<c:out value='${roleUpdate.getIdentifiant()}'/>" name="name" placeholder="<c:out value='${roleUpdate.getIdentifiant()}'/>" required>
		  				</div>
		  				<div style="margin-bottom: 10px">
				    		<label for="validationCustom02">Description :</label>
			    			<input type="text" class="form-control" id="validationCustom02" value="<c:out value='${roleUpdate.getDescription()}'/>" name="description" placeholder="<c:out value='${roleUpdate.getDescription()}'/>" required>
		  				</div>
					    <div class="text-right">
				    		<button class="btn btn-primary" type="submit">Valider</button>
			    		</div>
				    </form>
				  </div>
				</div>
				<% } if (mode == "create") { %>
				<div class="card border-primary mb-3" style="width: 25rem;">
				  <div class="card-body">
		    		<h5 class="card-title">Créer un nouveau role </h5>
				    <form  method="post" action="${pageContext.request.contextPath}/CreateRoleServlet" id="customer-info-form" class="no-gutters">
				 		<div style="margin-bottom: 10px">
					    	<label for="validationCustom01">Nom de la Role :</label>
				    		<input type="text" class="form-control" id="validationCustom01" name="name" required>
				 		</div>
				 		<div style="margin-bottom: 10px">
					    	<label for="validationCustom02">Description :</label>
				    		<input type="text" class="form-control" id="validationCustom02" name="description" required>
				 		</div>
					    <div class="text-right">
				    		<button class="btn btn-primary" type="submit">Ajouter</button>
					    </div>
				    </form>
				  </div>
				</div>
				<% } %>
    		</div>
		</div>
		<footer class="mastfoot mt-auto">
				<div class="row" style="margin: 0;">
					<div class="col-md-2"></div>
					<div class="inner col-md-9">
						<p class="mt-auto">Un petit soucis ? Appelez le 06.11.22.33.44 pour plus d'information.</p>
						<p class="mt-auto ml-auto">&copy; Us 2020</p>
					</div>
				</div>
			</footer>
	</div>
</body>
</html>