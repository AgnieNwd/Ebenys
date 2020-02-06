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
    			<h2>Gestion des matieres</h2>
    			<hr>
    			<p>
    				Ajouter une nouvelle matiere  > <a href="${pageContext.request.contextPath}/CreateMatiereServlet" ><i class="fa fa-plus-circle" aria-hidden="true"></i></a>
    			</p>
    			<ul class="list-group list-group-horizontal-md" style="flex-wrap: wrap;">
    			<%
	              int i;
	              for (i=0; i<allMatieres.size(); i++) { 
				%>
					  <li class="list-group-item">
						    <p>
							  	<%= allMatieres.get(i).getLibelleMatiere() %>  
						  		<a href="${pageContext.request.contextPath}/UpdateMatiereServlet?id=<%=allMatieres.get(i).getIdMatiere() %>"><i class="fa fa-pencil"></i></a>
							  	<a onclick="return confirm('/!\\ La suppression de cette matiere entrainera la suppression de toutes les notes existantes reliées à cette matiere. Vous etes sûr de votre choix?')" href="${pageContext.request.contextPath}/CrudMatiereServlet?action=delete&id=<%=allMatieres.get(i).getIdMatiere() %>" class="no-style-btn"><i class="fa fa-trash-o"></i></a>
						    </p>
				  			<p class="text-muted">
							  	<%= allMatieres.get(i).getUser().getPrenom()%> <%= allMatieres.get(i).getUser().getNom() %>
							</p>
					  </li>
				<% } %>
				</ul>
				
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