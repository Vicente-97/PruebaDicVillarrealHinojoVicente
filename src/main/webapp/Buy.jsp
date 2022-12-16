<%@page import="pruebaDic.DAO.DaoTravels"%>
<%@page import="pruebaDic.DAO.DaoUser"%>
<%@page import="pruebaDic.model.Travels"%>
<%@page import="pruebaDic.model.Train"%>
<%@page import="pruebaDic.model.User"%>
<%@page import="pruebaDic.DAO.DaoTrain"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
 	String isSession = (String) session.getAttribute("login");
	String userSession = (String) session.getAttribute("usuario");
	
	if(isSession == null && userSession == null){
		response.sendRedirect("error.jsp?msg=No tienes permisos, haz login.");
		
	}else{
		
		Integer id = Integer.valueOf(request.getParameter("botonCompra"));

		
		DaoUser daoUser = new DaoUser();
		
		User user =daoUser.get(userSession);
		
		DaoTrain daoTrain= new DaoTrain();
		
		Train train =daoTrain.get(id);
		train.setNumbertickets(train.getNumbertickets()-1);
		
		daoTrain.add(train);
		
		
		Travels travels = new Travels(user, train, 1);
		
		DaoTravels daoTravels = new DaoTravels();

		daoTravels.add(travels);%>
		
		<jsp:forward page="LoginExec"></jsp:forward>

	<%} %>









</body>
</html>