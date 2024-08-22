<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/relatorio.css">
    <title>Relat�rio - Cat�logo de Jogos</title>
</head>
<body>
    <div class="wrapper">
        <h1><%= request.getAttribute("nome") %></h1>        
        <h2>Resultado do <%= request.getAttribute("operacao")  %> : </h2>
        <h2><%= request.getAttribute("resultado") %></h2>

		<div>
			<ul>
				<li>Pre�o: <%= request.getAttribute("price") %></li>
				<li>Views: <%= request.getAttribute("views") %></li>
				<li>Compras: <%= request.getAttribute("purchases") %></li>
				<li>Desconto: <%= request.getAttribute("discount") %></li>
				<li>Nota: <%= request.getAttribute("rating") %></li>
			</ul>
		</div>

        <a href="../index.html"><button class="button">Voltar</button></a>
    </div>
</body>
</html>