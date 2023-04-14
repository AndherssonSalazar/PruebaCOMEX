<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Proyecto Hibernate</title>
</head>
<body>
	<h1>Bienvenido al Proyecto Hibernate</h1>

	<c:set var="mensaje" value="Aquí se mostrarían los datos de la base de datos" />
	<c:if test="${not empty mensaje}">
		<p>${mensaje}</p>
	</c:if>

	<form method="post" action="realizarTransaccion.jsp">
		<label for="nombreDocumento">Nombre del documento:</label>
		<input type="text" name="nombreDocumento"><br>
		<label for="fechaRegistro">Fecha de registro:</label>
		<input type="text" name="fechaRegistro"><br>
		<label for="estado">Estado:</label>
		<input type="text" name="estado"><br>
		<input type="submit" value="Realizar transacción">
	</form>

</body>
</html>
