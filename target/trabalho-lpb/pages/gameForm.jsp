<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.GameVO"
%> 

<%
    String operation = (String) request.getParameter("operation");
    String displayOperation = "";
    GameVO game = null;

    if ( operation.equals("edit") ) {
        game = new GameVO("1", "Celeste", "https://assets.nintendo.com/image/upload/f_auto/q_auto/dpr_1.5/f_auto/q_auto/dpr_1.5/f_auto/q_auto/dpr_1.5/f_auto/q_auto/dpr_1.5/c_scale,w_400/ncom/pt_BR/games/switch/c/celeste-switch/description-image", "https://assets.nintendo.com/image/upload/c_fill,w_1200/q_auto:best/f_auto/dpr_2.0/ncom/software/switch/70010000006442/691ba3e0801180a9864cc8a7694b6f98097f9d9799bc7e3dc6db92f086759252", 0, 2500, 100, 50, 3.5);
        displayOperation = "Edite";
    } else if (operation.equals("register")) {
        game = new GameVO();
        displayOperation = "Registre";
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/gameForm.css">
    <title>Game Archive - <%= displayOperation %> Seu Jogo</title>
</head>
<body>
    <header>
        <nav>
            <a href="../index.jsp">
                <svg xmlns="http://www.w3.org/2000/svg" height="50px" viewBox="0 -960 960 960" width="50px" fill="#5f6368"><path d="m287-446.67 240 240L480-160 160-480l320-320 47 46.67-240 240h513v66.66H287Z"/></svg>
            </a>
        </nav>
        <h1>Game Archive</h1>
    </header>

    <form method="POST" action="GameController" class="wrapper">
        <input type="hidden" value="<%= game.getId() %>" />

        <h1><%= displayOperation %> Seu Jogo: </h1>
        <div class="input-box">
            <label for="">Nome: </label>
            <input type="text" name="name" value="<%= game.getName() %>" required>
        </div>

        <div class="input-box">
            <label for="">Link do Ícone: </label>
            <input type="url" name="iconLink" value="<%= game.getIconLink() %>" required>
        </div>

        <div class="input-box">
            <label for="">Link do Banner: </label>
            <input type="url" name="bannerLink" value="<%= game.getBannerLink() %>" required>
        </div>

        <div class="input-box">
            <label for="">Preço: </label>
            <input type="number" name="price" step="0.01" value="<%= game.getPrice() %>" required>
        </div>

        <div class="input-box">
            <label for="">Compras: </label>
            <input type="number" name="purchases" value="<%= game.getPurchases() %>" required>
        </div>

        <div class="input-box">
            <label for="">Desconto: </label>
            <input type="number" name="discount" min="0" max="100" value="<%= game.getDiscount() %>" required> 
        </div>

        <div class="range-input input-box">
            <label for="">Nota: </label>
            <input type="range" step="0.5" min="1" max="5" name="rating" value="<%= game.getRating() %>" required>
            <p>3/5</p>
        </div>

        <div class="center-box">
            <button name="operation" value="<%= operation %>" type="submit" class="button">Enviar</button>
        </div>
    </form>

    <script src="../js/gameForm.js"></script>
</body>
</html>