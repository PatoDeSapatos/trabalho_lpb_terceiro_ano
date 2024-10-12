<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/index.css">
    <title>Voos</title>
</head>
<body>
    <%
        Cookie[] cookies = request.getCookies();
        String origem = null;
        String destino = null;
        String dataIda = null;
        String dataVolta = null;
        String nPessoas = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();

                switch(name) {
                    case "origem":
                        origem = cookie.getValue();
                        break;
                    case "destino": 
                        destino = cookie.getValue();
                        break;
                    case "dataIda": 
                        dataIda = cookie.getValue();
                        break;
                    case "dataVolta": 
                        dataVolta = cookie.getValue();
                        break;
                    case "nPessoas": 
                        nPessoas = cookie.getValue();
                        break;
                }
            }
        }
    %>

    <form action="PassagemController" method="post">

        <div class="field-wrapper">
            <div class="field-box">
                <label for="origem">Origem: </label>
                <select name="origem" id="origem" <% if (origem != null) out.println("value='" + origem + "'"); %>>
                    <option value="gru">Guarulhos</option>
                    <option value="cgh">Congonhas</option>
                    <option value="bsb">Brasília</option>
                    <option value="gig">Galeão</option>
                    <option value="vcp">Viracopos</option>
                </select>
            </div>

            <div class="field-box">
                <label for="destino">Destino: </label>
                <select name="destino" id="destino" <% if (destino != null) out.println("value='" + destino + "'"); %>>
                    <option value="gru">Guarulhos</option>
                    <option value="cgh">Congonhas</option>
                    <option value="bsb">Brasília</option>
                    <option value="gig">Galeão</option>
                    <option value="vcp">Viracopos</option>
                </select>
            </div>

            <div class="field-box">
                <label for="dataIda">Data de Ida: </label>
                <input type="date" name="dataIda" id="dataIda" <% if (dataIda != null) out.println("value='" + dataIda + "'"); %> >
            </div>

            <div class="field-box">
                <label for="dataVolta">Data de Volta: </label>
                <input type="date" name="dataVolta" id="dataVolta" <% if (dataVolta != null) out.println("value='" + dataVolta + "'"); %>>
            </div>
        </div>

        <div class="field-box">
            <label for="pessoas">Número de Pessoas: </label>
            <input type="number" name="pessoas" id="pessoas" <% if (nPessoas != null) out.println("value='" + nPessoas + "'"); %>>
        </div>

        <button type="submit" name="operacao" value="consultar">Procurar Viagem</button>
    </form>
</body>
</html>