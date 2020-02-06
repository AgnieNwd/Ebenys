<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User" %>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.Classe" %>
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
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./assets/css/style.css"> 
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    
</head>
<body>

	<% 
 		User user = (User) request.getSession().getAttribute("thisuser");
	
		String mode = (String) request.getAttribute("mode");
		String dateBorn = "";
		User userUpdate = null;
		
		List<Classe> allClasses = (List<Classe>) request.getAttribute("classes");

		if (mode == "update") {
		    userUpdate = (User) request.getAttribute("userUpdate");
		    int idClasse = (int) userUpdate.getClasse().getIdClasse();
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    dateBorn = dateFormat.format(userUpdate.getDateNaissance());
		}
 	%>
     	
<div class="contenu">
	<nav class="navbar navbar-light" style="background-color: #007bff80;">
	  <a class="navbar-brand">My Admin Intra</a>
	  <div style="display: flex;">
	  	<div style="display: flex; flex-direction: column; color: #e3f2fd">
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
			  <a class="nav-link active" href="${pageContext.request.contextPath}/CrudUserServlet">Gestion des Utilisateurs</a>
			  <a class="nav-link" href="${pageContext.request.contextPath}/CrudMatiereServlet">Gestion des Matières</a>
			  <a class="nav-link" href="${pageContext.request.contextPath}/CrudClasseServlet">Gestion des Classes</a>
			  <a class="nav-link" href="${pageContext.request.contextPath}/CruRoleServlet">Gestion des Roles</a>
			</nav>
		</div>
  		<div class="col-md-9">
			<div class="container">
			<% if (mode == "update") { %>
				<h2> Modifier l'utilisateur  <%= userUpdate.getPrenom() %> <%= userUpdate.getNom() %> </h2>
				<hr>
				<form  method="post" action="${pageContext.request.contextPath}/UpdateUserServlet" id="customer-info-form" class="no-gutters">
				
			<% } else { %>
				<h2> Ajouter un nouveau utilisateur  </h2>
				<hr>
				<form  method="post" action="${pageContext.request.contextPath}/CreateUserServlet" id="customer-info-form" class="no-gutters">
			<% } %>
			
					<div class="form-row">
					  <div class="col-md-4 mb-3">
					    <label for="validationCustom01">Prénom</label>
					    <input type="text" class="form-control" id="validationCustom01" value="<c:out value='${userUpdate.getPrenom()}'/>" name="firstname" required>
					  </div>
					  <div class="col-md-4 mb-3">
					    <label for="validationCustom02">Nom</label>
					    <input type="text" class="form-control" id="validationCustom02" value="<c:out value='${userUpdate.getNom()}'/>" name="lastname" required>
					  </div>
					  <div class="col-md-4 mb-3">
					    <label for="validationCustom03">Email</label>
					    <input type="email" class="form-control" id="validationCustom03" value="<c:out value='${userUpdate.getEmail()}'/>" name="email" required>
					  </div>
					</div>
					<% if (mode == "create" || user.getRole().getIdRole() == 1) { %>
					<div class="form-row">
					  <div class="col-md-4 mb-3">
					    <label for="validationPassword">Mot de Passe</label>
					    <input type="password" class="form-control" id="validationPassword" value="" name="password" <c:if test="${mode == 'create'}"> required </c:if>>
					  </div>
					</div>
					
					<% } %>
					<div class="form-row">
					  <div class="col-md-4 mb-3">
					    <label for="validationRole">Role</label>
					    <select class="custom-select" name="validationRole" id="validationRole">
			       			<option value="" <c:if test="${mode == 'create'}"> selected </c:if> disabled>Choisir...</option>
					     	<option value="Administrateur" <c:if test="${userUpdate.getRole().getIdentifiant() == 'Administrateur'}"> selected </c:if>>Administrateur </option>
						 	<option value="Directeur" <c:if test="${userUpdate.getRole().getIdentifiant() == 'Directeur'}"> selected </c:if>>Directeur </option>
							<option value="Professeur" <c:if test="${userUpdate.getRole().getIdentifiant() == 'Professeur'}"> selected </c:if>>Professeur </option>
							<option value="Eleve" <c:if test="${userUpdate.getRole().getIdentifiant() == 'Eleve'}"> selected </c:if>>Eleve </option>
					    </select>
					  </div>
					  
					  <% if (mode == "update" && (userUpdate.getRole().getIdRole() == 3 || userUpdate.getRole().getIdRole() == 4)) { %>
					  <div id="classe_div" class="col-md-4 mb-3">
					    <label for="validationClasse">Classe</label>
					    <select class="custom-select" name="validationClasse" id="validationClasse" required>
				      		<%
				              int i;
				              for (i=0; i<allClasses.size(); i++) { 
				           	%>
					           	<% if (allClasses.get(i).getIdClasse() == userUpdate.getClasse().getIdClasse()) { %>
							     	<option value="<%= allClasses.get(i).getIdClasse() %>" selected> <%= allClasses.get(i).getLibelleClasse() %> </option>
							    <% } else { %>
							    	<option value="<%= allClasses.get(i).getIdClasse() %>"> <%= allClasses.get(i).getLibelleClasse() %> </option>
							    <% } %>
						    <% } %>
					    </select>
					  </div>
					  <% } else { %>
					  
					  <div id="classe_div" class="col-md-4 mb-3">
					    <label for="validationClasse">Classe</label>
					    <select class="custom-select" name="validationClasse" id="validationClasse" required>
					      	<option value="" selected disabled>Choisir...</option>
						    <%
				              int i;
				              for (i=0; i<allClasses.size(); i++) { 
				           	%>
						    <option value="<%= allClasses.get(i).getIdClasse() %>"> <%= allClasses.get(i).getLibelleClasse() %> </option>
					    	<% } %>
					    </select>
					  </div>
					 
					 <% } %>
					  <div class="col-md-4 mb-3">
					    <label for="date">Date de Naissance</label>
					    <input type="date" data-toggle="datepicker" class="form-control" id="date" value="<% out.print(dateBorn); %>" name="dateNaiss" required>
					  </div>
					</div>
					<% if (mode == "create") { %>
					   	<button class="btn btn-primary" type="submit">Ajouter</button>
					<% } else { %>
						<button class="btn btn-primary" type="submit">Valider</button>
					<% } %>
					
				</form>
			</div>
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
	<script>
	    $(document).ready(function(){
    		if ($('#validationRole').val() == 'Directeur' || $('#validationRole').val() == 'Administrateur') {
              	$("#classe_div select").val("1");
              	$('#classe_div').hide();
          	}
    		
	      	$('#validationRole').change(function(){
				if($('#validationRole').val() == 'Directeur' || $('#validationRole').val() == 'Administrateur') {
				    $("#classe_div select").val("1");
				    $('#classe_div').hide();
				} else {
				    $('#classe_div').show(); 
				} 
	      	});
	      
	    });
	    
	    
	</script>
</body>
</html>