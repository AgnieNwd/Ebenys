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
	
	<title>MY INTRA PROF</title>
	<!-- CSS files -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./assets/css/style.css"> 
    
</head>
<body>

	<% 
 		User user = (User) request.getSession().getAttribute("thisuser"); 
		List<User> allUsers = (List<User>) request.getAttribute("allUsers");
		List<Matiere> matiereList = (List<Matiere>) request.getAttribute("matiereList"); 

	 %>
	<div class="contenu">
		<nav class="navbar main-header">
			<a class="navbar-brand">My Intra Prof</a>
			<div style="display: flex;">
				<div style="display: flex; flex-direction: column;">
				<span class="navbar-text" style="padding-bottom: 0; color: #e3f2fd">
					<%= user.getPrenom() %> <%= user.getNom()%> 
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
				<a class="nav-link active" href="${pageContext.request.contextPath}/">Mes infos</a>
				<a class="nav-link" href="${pageContext.request.contextPath}/ClasseProfessorServlet">Mes élèves</a>
				</nav>
			</div>
			<div class="col-md-8">
				<div class="row" style="margin: 0;">
					<div class="col-lg-12 col-sm-12 col-12 profile-header"></div>
				</div>
				<div class="row user-detail">
					<div class="col-lg-12 col-sm-12 col-12">
						<i class="fa fa-user rounded-circle img-thumbnail" aria-hidden="true"></i>
						<h5><%= user.getPrenom() %> <%= user.getNom() %></h5>
						<p><i class="fa fa-envelope" aria-hidden="true"></i> <%= user.getEmail() %></p>
		
						<hr>
						<p>Date de naissance : <%= user.getDateNaissance() %></p>
						<p>Classe(s) : <%= user.getClasse().getLibelleClasse() %></p>
						<p>Matière(s) : 
							<%
								int i;
								for (i=0; i < matiereList.size(); i++) { 
							%>
								<%= matiereList.get(i).getLibelleMatiere() %>
							<% } %> 
						</p>
					</div>
				</div>
			</div>
		</div>
		<footer class="mastfoot mt-auto">
		<div class="row" style="margin: 0;">
			<div class="col-md-2"></div>
			<div class="inner col-md-8">
				<p class="mt-auto">Un petit soucis ? Appelez le 06.11.22.33.44 pour plus d'information.</p>
				<p class="mt-auto ml-auto">&copy; Us 2020</p>
			</div>
		</div>
		</footer>
	</div>
</body>
</html>