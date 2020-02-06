<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.User" %>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.Note" %>
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
		User userRead = (User) request.getAttribute("userRead"); 
		List<Note> noteList = (List<Note>) request.getAttribute("noteList");
		List<Matiere> matiereProfList = (List<Matiere>) request.getAttribute("matiereProfList"); 

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
					<a class="nav-link" href="${pageContext.request.contextPath}/HomeProfessorServlet">Mes infos</a>
					<a class="nav-link active" href="${pageContext.request.contextPath}/ClasseProfessorServlet">Mes élèves <span class="breadcrumb-item active"> / <%= userRead.getPrenom() %> <%= userRead.getNom() %></span></a>
				</nav>
			</div>
			<div class="col-md-9">
			<h2> Note de : <%= userRead.getPrenom() %> <%= userRead.getNom() %></h2>
				<table class="table table-striped table-sm">
							<thead>
								<tr style="background-color: #17a2b8;">
									<th>Matiere</th>
									<th>Note</th>
									<th>Actions</th>
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
								<td>
									<%
									int i;
									for (i=0; i<matiereProfList.size(); i++) { 
										if (matiereProfList.get(i).getIdMatiere() == noteList.get(x).getMatiere().getIdMatiere() ) {
									%>
											<a href="${pageContext.request.contextPath}/UpdateStudentNoteServlet?idNote=<%=noteList.get(x).getIdNote() %>&idUser=<%=userRead.getId()%>&idMatiere=<%=noteList.get(x).getMatiere().getIdMatiere()%>"><i class="fa fa-pencil"></i></a>
											<a onclick="return confirm('Vous etes sûr de vouloir supprimer cette note?')" href="${pageContext.request.contextPath}/SeeStudentNotesServlet?action=delete&id=<%=userRead.getId() %>&idNote=<%=noteList.get(x).getIdNote() %>"><i class="fa fa-trash-o"></i></a>
									<% 	  
										}
													} 
									%>
								</td>
							</tr>
							<% } %>
							<% if (matiereProfList.size() > 0) { %>
							<tr class="table-light">
								<td colspan="2" style="text-align: right; margin-right: 20px;"> Ajouter une note  ></td>
								<td>		
										<a href="${pageContext.request.contextPath}/CreateStudentNoteServlet?id=<%=userRead.getId() %>" ><i class="fa fa-plus-circle" aria-hidden="true"></i></a>
								</td>
							</tr>
							<% } %>
							</tbody>
						</table>
					<a href="${pageContext.request.contextPath}/ExportNoteServlet?action=csv&idUser=<%= userRead.getId() %>" class="btn-export" role="button">Exporter au format Csv</a>
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