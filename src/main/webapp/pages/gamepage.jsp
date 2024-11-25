<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.game.GameVO"
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<% 
    GameVO game = (GameVO) request.getAttribute("gameInfo");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/gameinfo.css">
    <title>${ gameInfo.getName() } - Game Archive</title>
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
                	<c:forEach var="i" begin="0" end="4">
                		<c:set var="star" value="${gameInfo.getRating() - i}" />
                	
                		<c:choose>
                			<c:when test="${star >= 1}">
                				<svg xmlns='http://www.w3.org/2000/svg' height='40px' viewBox='0 -960 960 960' width='40px' fill='#e8eaed'><path d='m233-120 65-281L80-590l288-25 112-265 112 265 288 25-218 189 65 281-247-149-247 149Z'/></svg>
                			</c:when>
                			
                			<c:when test="${star <= 0}">
                				<svg xmlns='http://www.w3.org/2000/svg' height='40px' viewBox='0 -960 960 960' width='40px' fill='#e8eaed'><path d='M333.33-259 480-347l146.67 89-39-166.67 129-112-170-15L480-709l-66.67 156.33-170 15 129 112.34-39 166.33ZM233-120l65-281L80-590l288-25 112-265 112 265 288 25-218 189 65 281-247-149-247 149Zm247-353.33Z'/></svg>               			
                			</c:when>
                			
                			<c:otherwise>
                				<svg xmlns='http://www.w3.org/2000/svg' height='40px' viewBox='0 -960 960 960' width='40px' fill='#e8eaed'><path d='m606-286-33-144 111-96-146-13-58-136v312l126 77ZM233-120l65-281L80-590l288-25 112-265 112 265 288 25-218 189 65 281-247-149-247 149Z'/></svg>
                			</c:otherwise>
                		</c:choose>
                	</c:forEach>
                </div>
            </div>

            <div class="user-action">
                <figure>
                    <img src=" ${gameInfo.getBannerLink()} " alt="game-banner">
                </figure>

                <div class="buy-section">
                    <div class="price-box">
                        <div class="price-values">
                        	<c:if test="${ gameInfo.getDiscount() > 0 }">
                        		<div class='discount'>- ${ gameInfo.getDiscount() } %</div>
                        	</c:if>

                            <div class="prices">
								<c:if test="${ gameInfo.getDiscount() > 0 }">
	                        		<p class='full-price'>R$ ${gameInfo.getPrice()}</p>
	                        	</c:if>
                                
                                <p class="price">R$ ${gameInfo.calcDiscount()}</p>
                            </div>
                        </div>
                        <form action='GameController' method='POST'>
                            <input type="hidden" name="game" value="${gameInfo.getId()}"/>
                            <button name="operation" value="buy">Comprar</button>
                        </form>
                    </div>              
                </div>

                <div class="aditional-info">
                    <div>
                        <div>Esse Jogo Foi Visto ${gameInfo.getViews()} Vezes</div>
                        <div class="purchases">Compras: ${gameInfo.getPurchases()}</div>
                    </div>

                    <div>
                        <div>Custo Benefício: <span class="rate ${gameInfo.calcCostBenefit()}"> ${gameInfo.calcCostBenefit()} </span></div>
                        <div>Popularidade: <span class="rate ${gameInfo.calcPopularity()}">${gameInfo.calcPopularity()}</span></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>