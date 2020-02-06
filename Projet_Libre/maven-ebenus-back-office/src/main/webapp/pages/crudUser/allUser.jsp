<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User" %>
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
		List<User> allUsers = (List<User>) request.getAttribute("allUsers");
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
  			<div class="col-md-2 bd-sidebar">
				<nav id="menu-nav" class="nav nav-tabs flex-column" style="margin-top:6px;">
				  <a class="nav-link active" href="${pageContext.request.contextPath}/CrudUserServlet">Gestion des Utilisateurs</a>
				  <a class="nav-link" href="${pageContext.request.contextPath}/CrudMatiereServlet">Gestion des Matières</a>
				  <a class="nav-link" href="${pageContext.request.contextPath}/CrudClasseServlet">Gestion des Classes</a>
				  <a class="nav-link" href="${pageContext.request.contextPath}/CruRoleServlet">Gestion des Roles</a>
				</nav>
			</div>
    		<div class="col-md-9">
				<h2>Gestion des utilisateurs</h2>
				<hr>
			    <div class="table-responsive">
			        <table class="table table-striped table-sm">
			          <thead>
			            <tr style="background-color: #adaebd;">
			              <th>Prénom</th>
			              <th>Nom</th>
			              <th>Email</th>
			              <th>Date naissance</th>
			              <th>Date création</th>
			              <th>Date modification</th>
			              <th>Identifiant rôle</th>
			              <th>Description rôle</th>
			              <th>Classe</th>
			              <th>Actions</th>
			            </tr>
			          </thead>
			          <tbody>
		         	<%
		              int i;
		              for (i=0; i<allUsers.size(); i++) { 
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
						DateFormat dateFormatBorn = new SimpleDateFormat("dd/MM/yyyy");
						String dateBorn = dateFormatBorn.format(allUsers.get(i).getDateNaissance());
						String dateCrea = dateFormat.format(allUsers.get(i).getDateCreation());
						String dateEdit = dateFormat.format(allUsers.get(i).getDateModification());
					%>
			            <tr>
							<td><%= allUsers.get(i).getPrenom() %></td>
							<td><%= allUsers.get(i).getNom() %></td>
							<td><%= allUsers.get(i).getEmail() %></td>
							<td><%= dateBorn %></td>
							<td><%= dateCrea %></td>
							<td><%= dateEdit %></td>
							<td><%= allUsers.get(i).getRole().getIdentifiant() %></td>
							<td><%= allUsers.get(i).getRole().getDescription() %></td>
							<td><%= allUsers.get(i).getClasse().getLibelleClasse() %></td>
							<td>
								<% if (allUsers.get(i).getRole().getIdRole() == 4 || allUsers.get(i).getRole().getIdRole() == 3) { %>
									<a href="${pageContext.request.contextPath}/ReadUserServlet?id=<%=allUsers.get(i).getId() %>&mode=<%=allUsers.get(i).getRole().getIdentifiant() %>" class="no-style-btn"><i class="fa fa-eye" aria-hidden="true"></i></a>
								<% } %>
								<a href="${pageContext.request.contextPath}/UpdateUserServlet?id=<%=allUsers.get(i).getId() %>"><i class="fa fa-pencil"></i></a>
								<% if (user.getRole().getIdRole() < 3 && allUsers.get(i).getId() != user.getId()) { %>
									<a onclick="return confirm('Vous etes sûr ?')" href="${pageContext.request.contextPath}/CrudUserServlet?action=delete&id=<%=allUsers.get(i).getId() %>" class="no-style-btn"><i class="fa fa-trash-o"></i></a>
								<% } %>
							</td>
			            </tr>
			            <% } %>
			            <tr>
				            <td colspan="9" style="text-align: right; margin-right: 20px;"> Ajouter un nouvel user  ></td>
				            <td>		
				            	<a href="${pageContext.request.contextPath}/CreateUserServlet" ><i class="fa fa-plus-circle" aria-hidden="true"></i></a>
							</td>
			            </tr>
			          </tbody>
			        </table>
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
</body>
</html>