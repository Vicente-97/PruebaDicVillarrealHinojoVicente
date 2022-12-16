<%@page import="pruebaDic.DAO.DaoTravels"%>
<%@page import="pruebaDic.DAO.DaoUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pruebaDic.model.Travels"%>
<%@page import="pruebaDic.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>

		 <hr>

	</div>

	<br>
	
	<div>
		<table>
		
		
			<tr>
				
				<th>Usuario</th>
				<th>Travel</th>
				<th>Asiento</th>
				
				
				
			</tr>
			
<%
 	String isSession = (String) session.getAttribute("login");
	String userSession = (String) session.getAttribute("usuario");
	
	if(isSession == null && userSession == null){
		response.sendRedirect("error.jsp?msg=No tienes permisos, haz login.");
		
	}else{
		
		List<Travels> listaViajes = new ArrayList();
		
		DaoUser daoUser = new DaoUser();
		
		 User user =daoUser.get(userSession);
		 
		 DaoTravels daoTravels= new DaoTravels();
		 listaViajes=daoTravels.getListPurchase(userSession);
		
		
		
		%>
			for (Travels train: listaViajes){
				  %>
				  	<tr>
				  		<td><%=train.get%></td>
				  		<td><%=train.getOrigin()%></td>
				  	</tr>
				  	
		<jsp:forward page="LoginExec"></jsp:forward>

	<%} %>



</body>
</html>