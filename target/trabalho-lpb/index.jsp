<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.GameVO"
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/gameswrapper.css">
    <title>Home - Catálogo de Jogos</title>
</head>

<body>
    <header>
        <nav>
            <svg class="icon add-button" xmlns="http://www.w3.org/2000/svg" height="2.5em" viewBox="0 -960 960 960" width="2.5em" fill="#FFFFFF"><path d="M440-280h80v-160h160v-80H520v-160h-80v160H280v80h160v160ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>
            <div class="search-box">
                <svg class="icon search-icon" xmlns="http://www.w3.org/2000/svg" height="2.5em" viewBox="0 -960 960 960" width="2.5em" fill="#FFFFFF"><path d="M784-120 532-372q-30 24-69 38t-83 14q-109 0-184.5-75.5T120-580q0-109 75.5-184.5T380-840q109 0 184.5 75.5T640-580q0 44-14 83t-38 69l252 252-56 56ZM380-400q75 0 127.5-52.5T560-580q0-75-52.5-127.5T380-760q-75 0-127.5 52.5T200-580q0 75 52.5 127.5T380-400Z"/></svg>
                <input type="text" placeholder="Search..." class="search-bar">
            </div>
        </nav>
        <h1>Game Archive</h1>
    </header>

    <form method="POST" action="GameController" class="wrapper">
        <h1>Adicione Seu Jogo: </h1>
        <div class="input-box">
            <label for="">Nome: </label>
            <input type="text" name="name" required>
        </div>

        <div class="input-box">
            <label for="">Preço: </label>
            <input type="number" name="price" step="0.01" required>
        </div>

        <div class="input-box">
            <label for="">Views: </label>
            <input type="number" name="views" required>
        </div>

        <div class="input-box">
            <label for="">Compras: </label>
            <input type="number" name="purchases" required>
        </div>

        <div class="input-box">
            <label for="">Desconto: </label>
            <input type="number" name="discount" min="0" max="100" required> 
        </div>

        <div class="range-input input-box">
            <label for="">Nota: </label>
            <input type="range" step="0.5" min="1" max="5" name="rating" required>
            <p>3/5</p>
        </div>

        <div class="center-box">
            <button name="oprt" value="register" type="submit" class="button">Enviar</button>
        </div>
    </form>

    <div class="games-wrapper">
        <div class="game-tabs">
            <h1>Recentes: </h1>
        </div>

        <div class="games-box">
            <% 
                GameVO game = new GameVO();
            %>
        </div>

        <div class="games-info">
            
        </div>
    </div>

    <script src="./js/index.js"></script>
</body>
</html>