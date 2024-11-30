<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%@ page import="model.user.UserVO" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:set var="user" value="${sessionScope.login}"/>
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/userpage.css">
        <title><c:out value="${user.name}"/></title>
    </head>
    <body>
        <header>
            <div>
                <a href="index.html"> <svg class="icon add-button" xmlns="http://www.w3.org/2000/svg" height="2.5em" viewBox="0 -960 960 960" width="2.5em" fill="#FFFFFF"><path d="M240-200h120v-240h240v240h120v-360L480-740 240-560v360Zm-80 80v-480l320-240 320 240v480H520v-240h-80v240H160Zm320-350Z"/></svg></a>
                
                <a style="margin-left: 1em" href="UserController?operation=logout"><svg class="icon add-button" xmlns="http://www.w3.org/2000/svg" height="2.5em" viewBox="0 -960 960 960" width="2.5em" fill="#FFFFFF"><path d="M200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h280v80H200v560h280v80H200Zm440-160-55-58 102-102H360v-80h327L585-622l55-58 200 200-200 200Z"/></svg></a>

                <h1>Olá <u><c:out value="${user.name}" /></u>!</h1>
            </div>
            <h1>Game Archive</h1>
        </header>
        <div class="content">
            <div class="user-wrapper">
                <h2>Suas Informações</h2>
                <hr>
                <p>Login: <c:out value="${user.login}"/></p>
                <p>CPF: <c:out value="${user.cpf}"/></p>
                <p>Email: <c:out value="${user.email}"/></p>
                <p>Número de celular: <c:out value="${user.phoneNumber}"/></p>
                <!--<a href="pages/edituser.jsp" style="align-self: center;"><button>Editar</button></a>-->
            </div>
        </div>
    </body>
</html>