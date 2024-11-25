<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%@ page import="model.user.UserVO" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:set var="user" value="${sessionScope.login}"/>
        <title><c:out value="${user.name}"/></title>
    </head>
    <body>
        <h1><c:out value="${user.name}"/></h1>
        <p>Login: <c:out value="${user.login}"/></p>
        <p>Senha: <c:out value="${user.password}"/></p>
        <p>CPF: <c:out value="${user.cpf}"/></p>
        <p>Email: <c:out value="${user.email}"/></p>
        <p>Número de celular: <c:out value="${user.phoneNumber}"/></p>
    </body>
</html>