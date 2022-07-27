<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>Je suis la page HOME</h1>
	<p>${ perso.nom }</p>
	<h1>Liste de personnes</h1>
    <c:forEach items="${ personnes }" var="personne">
        <c:out value="${ personne.nom } ${ personne.prenom }"/>      
    </c:forEach>
    
    <p>${ nom }</p>
</body>
</html>