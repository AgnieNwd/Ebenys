<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User" %>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.Note" %>

<%@page import="java.util.List" %>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>MY INTRA STUDENT</title>
	<!-- CSS files -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./assets/css/style.css"> 
    
</head>
<body>

	<% 
 		User user = (User) request.getSession().getAttribute("thisuser"); 
		List<User> allUsers = (List<User>) request.getAttribute("allUsers");
		List<Note> noteList = (List<Note>) request.getAttribute("noteList");

	 %>
	<div class="contenu">
	
		<nav class="navbar main-header">
			<a class="navbar-brand">My Intra Student</a>
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
					<a class="nav-link" href="${pageContext.request.contextPath}/">Mes infos</a>
					<a class="nav-link active" href="${pageContext.request.contextPath}/NoteStudentServlet">Mes notes</a>
				</nav>
			</div>
			<div class="col-md-9">
				<h2> Mes Notes </h2>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-sm">
								<thead>
										<tr style="background-color: #17a2b8;">
											<th>Matiere</th>
											<th>Note</th>
										</tr>
								</thead>
								<tbody>
									<%
											int x;
											for (x=0; x<noteList.size(); x++) { 
							%>
											<tr>
									<td><%= noteList.get(x).getMatiere().getLibelleMatiere() %></td>
									<td><%= noteList.get(x).getLibelleNote() %></td>
											</tr>
									<% } %>
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