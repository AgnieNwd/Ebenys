<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User" %>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.Matiere" %>
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
		List<Matiere> allMatieres = (List<Matiere>) request.getAttribute("allMatieres");
		String mode = (String) request.getAttribute("mode");
		
		List<User> allUsersProf = (List<User>) request.getAttribute("allUsersProf");

		Matiere matiereUpdate = null;

		if (mode == "update") {
			matiereUpdate = (Matiere) request.getAttribute("matiereUpdate");
		    int idUser = (int) matiereUpdate.getUser().getId();
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
				  <a class="nav-link active" href="${pageContext.request.contextPath}/CrudMatiereServlet">Gestion des Matières</a>
			  	  <a class="nav-link" href="${pageContext.request.contextPath}/CrudClasseServlet">Gestion des Classes</a>
			  	  <a class="nav-link" href="${pageContext.request.contextPath}/CruRoleServlet">Gestion des Roles</a>
				</nav>
			</div>
    		<div class="col-md-9">
				
				<% if (mode == "update") { %>
				<div class="card border-primary mb-3" style="width: 25rem;">
				  <div class="card-body">
				    <h5 class="card-title">Modifier la matiere <%= matiereUpdate.getLibelleMatiere() %></h5>
				    <form  method="post" action="${pageContext.request.contextPath}/UpdateMatiereServlet" id="customer-info-form" class="no-gutters">  
				    	<div style="margin-bottom: 10px">
				    		<label for="validationCustom01">Nom de la Matiere :</label>
			    			<input type="text" class="form-control" id="validationCustom01" value="<c:out value='${matiereUpdate.getLibelleMatiere()}'/>" name="name" placeholder="<c:out value='${matiereUpdate.getLibelleMatiere()}'/>" required>
		  				</div>
			  			<div style="margin-bottom: 10px">
		  				<label for="validationUserProf">Professeur qui enseigne cette matiere : </label>
				  			<select class="custom-select" name="validationUserProf" id="validationUserProf" required>
						      	<option value="" <c:if test="${mode == 'create'}"> selected </c:if> disabled>Choisir...</option>
					      		<%
					              int i;
					              for (i=0; i<allUsersProf.size(); i++) { 
					           	%>
						           	<% if (allUsersProf.get(i).getId() == matiereUpdate.getUser().getId()) { %>
								     	<option value="<%= allUsersProf.get(i).getId() %>" selected> <%= allUsersProf.get(i).getPrenom() %> <%= allUsersProf.get(i).getNom() %> </option>
								    <% } else { %>
								    	<option value="<%= allUsersProf.get(i).getId() %>"> <%= allUsersProf.get(i).getPrenom() %> <%= allUsersProf.get(i).getNom() %></option>
								    <% } %>
							    <% } %>
						    </select>
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
		    		<h5 class="card-title">Créer une nouvelle matiere </h5>
				    <form  method="post" action="${pageContext.request.contextPath}/CreateMatiereServlet" id="customer-info-form" class="no-gutters">
				 		<div style="margin-bottom: 10px">
					    	<label for="validationCustom01">Nom de la Matiere :</label>
				    		<input type="text" class="form-control" id="validationCustom01" name="name" required>
				 		</div>
			  			<div style="margin-bottom: 10px">
				  			<label for="validationUserProf">Professeur qui enseigne cette matiere : </label>
						    <select class="custom-select" name="validationUserProf" id="validationUserProf" required>
						      	<option value="" selected disabled>Choisir...</option>
					      		<%
					              int i;
					              for (i=0; i<allUsersProf.size(); i++) { 
					           	%>
								   <option value="<%= allUsersProf.get(i).getId() %>"> <%= allUsersProf.get(i).getPrenom() %> <%= allUsersProf.get(i).getNom() %></option>			    
							    <% } %>
						    </select>
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