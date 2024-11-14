<%@ 
	page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="model.Passagem, java.util.*"
 %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/relatorio.css">
    <title>Voos</title>
</head>
<body>
    <div>
        <div class="header">
            <h1>Passagens </h1>
            <a href="./index.html">Voltar</a>
        </div>

        <h2>Ida: </h2>
		<div class="passagens-ida wrapper">
		
			<%
				ArrayList<Passagem> passagensIda = (ArrayList<Passagem>) request.getAttribute("passagemIda");
			
				for(Passagem passagemIda : passagensIda) {
					out.println("<div class='passagem-box'><div><span>Origem:</span>" + passagemIda.getOrigem() + "</div><div><span>Destino:</span>" + passagemIda.getDestino() + "</div><div><span>Data:</span>" + passagemIda.getData() + "</div><div><span>Valor:</span>" + passagemIda.getValor() + "</div><div><span>Horario:</span>" + passagemIda.getHorario() + "</div></div>");
				}
				
				if (passagensIda.size() <= 0) out.println("<p>Nenhum resultado encontrado...</p>");
			%>

        </div>

        <h2>Volta: </h2>
        <div class="passagens-volta wrapper">
            <%
				ArrayList<Passagem> passagensVolta = (ArrayList<Passagem>) request.getAttribute("passagemIda");
				
				for(Passagem passagemVolta : passagensVolta) {
					out.println("<div class='passagem-box'><div><span>Origem:</span>" + passagemVolta.getOrigem() + "</div><div><span>Destino:</span>" + passagemVolta.getDestino() + "</div><div><span>Data:</span>" + passagemVolta.getData() + "</div><div><span>Valor:</span>" + passagemVolta.getValor() + "</div><div><span>Horario:</span>" + passagemVolta.getHorario() + "</div></div>");
				}
				
				if (passagensVolta.size() <= 0) out.println("<p>Nenhum resultado encontrado...</p>");
			%>
        </div>
	</div>
</body>
</html>