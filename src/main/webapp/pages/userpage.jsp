<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.user.UserVO"
%>

<% 
    UserVO user = (UserVO) session.getAttribute("login");
%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><%= user.getName() %></title>
    </head>
    <body>
        <h1><%= user.getName() %></h1>
        <p>Login: <%= user.getLogin() %></p>
        <p>Senha: <%= user.getPassword() %></p>
        <p>CPF: <%= user.getCpf() %></p>
        <p>Email: <%= user.getEmail() %></p>
        <p>Número de celular: <%= user.getPhoneNumber() %></p>
    </body>
</html>