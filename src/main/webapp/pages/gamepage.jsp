<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.GameVO"
%>

<% 
    GameVO game = (GameVO) request.getAttribute("game");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/gameinfo.css">
    <title><%= game.getName() %> - Game Archive</title>
</head>
<body>
    <header>
        <nav>
            <a href="./index.html">
                <svg xmlns="http://www.w3.org/2000/svg" height="50px" viewBox="0 -960 960 960" width="50px" fill="#5f6368"><path d="m287-446.67 240 240L480-160 160-480l320-320 47 46.67-240 240h513v66.66H287Z"/></svg>
            </a>
        </nav>
        <h1>Game Archive</h1>
    </header>

    <div class="game-info">
        <div class="main-info">
            <div class="header">
                <h1><%= game.getName() %></h1>
                <div class="rate-box">
                    <%
                        double rating = game.getRating();
                        for(int i = 0; i < 5; i++) {
                            double star = (double) rating - i;

                            if (star >= 1) {
                                out.println("<svg xmlns='http://www.w3.org/2000/svg' height='40px' viewBox='0 -960 960 960' width='40px' fill='#e8eaed'><path d='m233-120 65-281L80-590l288-25 112-265 112 265 288 25-218 189 65 281-247-149-247 149Z'/></svg>");
                            } else if (star <= 0) {
                                out.println("<svg xmlns='http://www.w3.org/2000/svg' height='40px' viewBox='0 -960 960 960' width='40px' fill='#e8eaed'><path d='M333.33-259 480-347l146.67 89-39-166.67 129-112-170-15L480-709l-66.67 156.33-170 15 129 112.34-39 166.33ZM233-120l65-281L80-590l288-25 112-265 112 265 288 25-218 189 65 281-247-149-247 149Zm247-353.33Z'/></svg>");
                            } else {
                                out.println("<svg xmlns='http://www.w3.org/2000/svg' height='40px' viewBox='0 -960 960 960' width='40px' fill='#e8eaed'><path d='m606-286-33-144 111-96-146-13-58-136v312l126 77ZM233-120l65-281L80-590l288-25 112-265 112 265 288 25-218 189 65 281-247-149-247 149Z'/></svg>");
                            }
                        }
                    %>
                </div>
            </div>

            <div class="user-action">
                <figure>
                    <img src=" <%= game.getBannerLink() %> " alt="game-banner">
                </figure>

                <div class="buy-section">
                    <div class="price-box">
                        <div class="price-values">
                            <%
                                double discount = game.getDiscount();
                                if (discount > 0) {
                                    out.println( "<div class='discount'>-"+ game.getDiscount() + "%</div> ");
                                }
                            %>
                            
                            <div class="prices">
                                <% 
                                    if (discount > 0) {
                                        out.println( "<p class='full-price'>R$ "+game.getPrice()+"</p>");
                                    }
                                %>
                                
                                <p class="price">R$ <%= game.calcDiscount() %></p>
                            </div>
                        </div>
                        <button>Comprar</button>
                    </div>              
                </div>

                <div class="aditional-info">
                    <div>
                        <div>Esse Jogo Foi Visto <%= game.getViews() %> Vezes</div>
                        <div class="purchases">Compras: <%= game.getPurchases() %></div>
                    </div>

                    <div>
                        <div>Custo Benefício: <span class="rate <%= game.calcCostBenefit() %>"> <%= game.calcCostBenefit() %> </span></div>
                        <div>Popularidade: <span class="rate <%= game.calcPopularity() %>"><%= game.calcPopularity() %></span></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>