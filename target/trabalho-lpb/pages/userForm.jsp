<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/userForm.css">
    <title>Entrar/Registrar</title>
</head>
<body>
    <header id="header">
        <nav>
            <a href="../index.html">
                <svg xmlns="http://www.w3.org/2000/svg" height="50px" viewBox="0 -960 960 960" width="50px" fill="#5f6368"><path d="m287-446.67 240 240L480-160 160-480l320-320 47 46.67-240 240h513v66.66H287Z"/></svg>
            </a>
        </nav>
        <h1>Game Archive</h1>
    </header>

    <form action="../UserController" method="post" id="register-form">
        <c:set var="error" value="${requestScope.error}" />

        <c:if test="${error != null && !error.isEmpty()}">
            <div class='error-message'>
                <h2>Erro!</h2>
                <p>O nome de usuário já existe.</p>
            </div>
        </c:if>

        <h1>Registre-se</h1>

        <div class="form-info">
            <div class="form-section">
                <div>
                    <label for="login">Nome de Usuário:</label>
                    <input type="text" id="login" name="login" required>
                </div>

                <div>
                    <label for="name">Nome Completo:</label>
                    <input type="text" id="name" name="name" required>
                </div>
            </div>
            
            <div class="form-section">
                <div>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div>
                    <label for="password">Senha:</label>
                    <input type="password" id="password" name="password" required>
                </div>
            </div>

            <div class="form-section">
                <div>
                    <label for="cpf">CPF:</label>
                    <input type="text" id="cpf" name="cpf">
                </div>

                <div>
                    <label for="tel">Telefone:</label>
                    <input type="tel" id="tel" name="tel">
                </div>
            </div>
        </div>

        <div class="form-footer">
            <p>Já tem uma conta? <span class="change-page">Entre Aqui!</span> </p>
            <button name="operation" value="register" type="submit">Cadastrar</button>
        </div>
    </form>

    <form action="../UserController" method="post" id="login-form" class="active">
        <h1>Entre</h1>

        <div class="form-section login">
            <div>
                <label for="login">Nome de Usuário:</label>
                <input type="text" id="login" name="login">
            </div>

            <div>
                <label for="password">Senha:</label>
                <input type="password" id="password" name="password">
            </div>
        </div>

        <div class="form-footer">
            <p>Não tem uma conta? <span class="change-page">Cadastre-se Aqui!</span> </p>
            <button name="operation" value="login" type="submit">Entrar</button>
        </div>
    </form>
    <script src="../js/userForm.js"></script>
</>
</html>