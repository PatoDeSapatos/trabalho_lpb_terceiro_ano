<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.game.GameVO, model.user.UserVO, java.util.ArrayList" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%@ page import="jakarta.servlet.http.Cookie" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/gameswrapper.css">
    <title>Game Archive - Home</title>
    <c:set var="user" value="${sessionScope.login}" />
    <c:set var="allGames" value="${requestScope.allGames}" />
    <c:set var="userGames" value="${requestScope.userGames}" />
</head>

<body>
    <header>
        <nav>
            <c:choose>
                <c:when test="${user != null}">
                    <a href='GameController?operation=register'><svg class='icon add-button' xmlns='http://www.w3.org/2000/svg' height='2.5em' viewBox='0 -960 960 960' width='2.5em' fill='#FFFFFF'><path d='M440-280h80v-160h160v-80H520v-160h-80v160H280v80h160v160ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z'/></svg></a>
                    <h2 class='welcome-title'>Bem vindo <a href='./pages/userpage.jsp'><c:out value="${user.login}" /></a>! 🖐</h2>
                </c:when>

                <c:otherwise>
                    <a href='./pages/userForm.jsp'><svg class='icon add-button' xmlns="http://www.w3.org/2000/svg" height="2.5em" viewBox="0 -960 960 960" width="2.5em" fill="#FFFFFF"><path d="M480-120v-80h280v-560H480v-80h280q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H480Zm-80-160-55-58 102-102H120v-80h327L345-622l55-58 200 200-200 200Z"/></svg></a>
                </c:otherwise>
            </c:choose>

            <%-- <div class="search-box">
                <svg class="icon search-icon" xmlns="http://www.w3.org/2000/svg" height="2.5em" viewBox="0 -960 960 960" width="2.5em" fill="#FFFFFF"><path d="M784-120 532-372q-30 24-69 38t-83 14q-109 0-184.5-75.5T120-580q0-109 75.5-184.5T380-840q109 0 184.5 75.5T640-580q0 44-14 83t-38 69l252 252-56 56ZM380-400q75 0 127.5-52.5T560-580q0-75-52.5-127.5T380-760q-75 0-127.5 52.5T200-580q0 75 52.5 127.5T380-400Z"/></svg>
                <input type="text" placeholder="Search..." class="search-bar">
            </div> --%>
        </nav>
        <h1>Game Archive</h1>
    </header>

    <div class="recent-games-wrapper">
        <c:if test="${user != null}">
            <c:if test="${userGames.size() > 0}">
                <div>
                    <h2>Último Jogo Cadastrado</h2>
                    <%
                        Cookie[] cookies = request.getCookies();
                        if (cookies != null) {
                            String name = "";
                            String price = "";
                            String views = "";

                            for (Cookie c : cookies) {
                                if ("name".equals(c.getName())) {
                                    name = c.getValue();
                                    break;
                                } else if ("price".equals(c.getName())) {
                                    price = c.getValue();
                                    break;
                                } else if ("views".equals(c.getName())) {
                                    views = c.getValue();
                                    break;
                                }
                            }
                            out.println("<h3>Nome: " + name + "</h3>");
                            out.println("<h3>Preco: " + price + "</h3>");
                            out.println("<h3>Views: " + views + "</h3>");
                        }
                    %>
                </div>
            </c:if>
        </c:if>
        
        <c:if test="${user != null}">
            <c:if test="${userGames.size() > 0}">
                <h3>Seus Jogos: </h3>
                <div class="games-wrapper">

                    <c:forEach begin="0" end="${userGames.size()-1}" varStatus="loop">
                        <c:set var="game" value="${userGames.get(loop.index)}" />
                        <div class='game-section'>
                            <figure>
                                <div class='circular-shadow'></div>
                                <a href="GameController?operation=showgame&game=${game.id}">
                                    <img src='${game.iconLink}' alt='game-icon' />
                                    <figcaption>
                                        <p class='discount'> - <c:out value="${game.discount}" /> </p>
                                        <p class='full-price'>R$ <c:out value="${game.price}" /> </p>
                                        <p class='price'>R$ <c:out value="${game.discount}" /> </p>
                                    </figcaption>
                                </a>
                                <h3> <c:out value="${game.name}" /></h3>
                            </figure>
                            <form action='GameController' method='POST'>
                                <input type='hidden' name='gameId' value='${game.id}' />
                                <button name='operation' value='delete'><svg xmlns='http://www.w3.org/2000/svg' height='24px' viewBox='0 -960 960 960' width='24px' fill='#5f6368'><path d='m376-300 104-104 104 104 56-56-104-104 104-104-56-56-104 104-104-104-56 56 104 104-104 104 56 56Zm-96 180q-33 0-56.5-23.5T200-200v-520h-40v-80h200v-40h240v40h200v80h-40v520q0 33-23.5 56.5T680-120H280Zm400-600H280v520h400v-520Zm-400 0v520-520Z'/></svg></button>
                                <a href='GameController?operation=edit&game=${game.id}'><svg xmlns='http://www.w3.org/2000/svg' height='24px' viewBox='0 -960 960 960' width='24px' fill='#5f6368'><path d='M160-120v-170l527-526q12-12 27-18t30-6q16 0 30.5 6t25.5 18l56 56q12 11 18 25.5t6 30.5q0 15-6 30t-18 27L330-120H160Zm80-80h56l393-392-28-29-29-28-392 393v56Zm560-503-57-57 57 57Zm-139 82-29-28 57 57-28-29ZM560-120q74 0 137-37t63-103q0-36-19-62t-51-45l-59 59q23 10 36 22t13 26q0 23-36.5 41.5T560-200q-17 0-28.5 11.5T520-160q0 17 11.5 28.5T560-120ZM183-426l60-60q-20-8-31.5-16.5T200-520q0-12 18-24t76-37q88-38 117-69t29-70q0-55-44-87.5T280-840q-45 0-80.5 16T145-785q-11 13-9 29t15 26q13 11 29 9t27-13q14-14 31-20t42-6q41 0 60.5 12t19.5 28q0 14-17.5 25.5T262-654q-80 35-111 63.5T120-520q0 32 17 54.5t46 39.5Z'/></svg></a>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </c:if>




        <c:if test="${allGames.size() > 0}">
        <h3>Todos os Jogos: </h3>
        <div class="games-wrapper">
                <c:forEach begin="0" end="${allGames.size()-1}" varStatus="loop">
                    <c:set var="game" value="${allGames.get(loop.index)}" />
                    <div class='game-section'>
                        <figure>
                            <div class='circular-shadow'></div>
                            <a href="GameController?operation=showgame&game=${game.id}">
                                <img src='${game.iconLink}' alt='game-icon' />
                                <figcaption>
                                    <p class='discount'> - <c:out value="${game.discount}" /> </p>
                                    <p class='full-price'>R$ <c:out value="${game.price}" /> </p>
                                    <p class='price'>R$ <c:out value="${game.discount}" /> </p>
                                </figcaption>
                            </a>
                            <h3> <c:out value="${game.name}" /></h3>
                        </figure>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>

    <script src="./js/index.js"></script>
</body>
</html>