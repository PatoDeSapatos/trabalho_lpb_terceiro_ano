package controller;

import java.io.IOException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.game.GameDAO;
import model.game.GameVO;

@WebServlet("/GameController")
public class GameController extends HttpServlet {

	@Resource(name="gamearchive")
	private DataSource dataSource;
    private GameDAO dao;
    
	@Override
	public void init() throws ServletException {
		dao = new GameDAO(dataSource);
	}
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
        String oprt = request.getParameter("operation");

        switch (oprt) {
            case "getall": 
                getHomePage(request, response);
                break;
            case "showgame":
                getGamePage(request, response);
                break;
            case "register":
                getResgisterPage(request, response);
                break;
            case "edit":
                getEditPage(request, response);
                break;

            default:
                request.setAttribute("operacao", "Operação Inválida");
                request.setAttribute("resultado", "");
                break;
        }  
	} 

    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        String oprt = request.getParameter("operation").toLowerCase();

        switch (oprt) {
            case "register":
                registerGame(request, response);
                break;
            case "edit":
                editGame(request, response);
                break;
            case "delete":
                deleteGame(request, response);
                break;
            case "buy":
                buyGame(request, response);
                break;
        
            default:
                break;
        }
    }

    private void buyGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String) request.getParameter("game");
        GameVO game = dao.getGameById(id);

        dao.buy(game);

        request.setAttribute("gameInfo", game);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/gamepage.jsp");
		dispatcher.forward(request, response);
    }

    private void deleteGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String) request.getParameter("gameId");
        boolean resultado = dao.delete(id);

        request.setAttribute("result", resultado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
    }

    private void editGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("gameId");
        String name = request.getParameter("name");
		String iconLink = request.getParameter("iconLink");
		String bannerLink = request.getParameter("bannerLink");
		double price = Double.parseDouble(request.getParameter("price"));
		int discount = Integer.parseInt(request.getParameter("discount"));
		double rating = Double.parseDouble(request.getParameter("rating"));

        GameVO newGame = new GameVO(id, "", name, iconLink, bannerLink, discount, price, discount, discount, rating);

        boolean resultado = dao.update(id, newGame);

        request.setAttribute("result", resultado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
    }

    private void registerGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
		String iconLink = request.getParameter("iconLink");
		String bannerLink = request.getParameter("bannerLink");
        double price = Double.parseDouble( request.getParameter("price") );
        int discount = Integer.parseInt( request.getParameter("discount") );
        double rating = Double.parseDouble( request.getParameter("rating") );

        GameVO game = new GameVO("", userId, name, iconLink, bannerLink, discount, price, discount, discount, rating);

        boolean resultado = dao.save(game);

        request.setAttribute("result", resultado);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
    }

    public void getHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("games", dao.getAllGames());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/homepage.jsp");
		dispatcher.forward(request, response);
    }

    private void getGamePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("game");

        dao.updateGameViews(id);
        GameVO game = dao.getGameById(id);

        request.setAttribute("gameInfo", game);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/gamepage.jsp");
		dispatcher.forward(request, response);
    }

    private void getResgisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("operation", "register");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/gameForm.jsp");
        dispatcher.forward(request, response);
    }

    private void getEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameId = (String) request.getParameter("game");
        request.setAttribute("game", dao.getGameById(gameId));
        request.setAttribute("operation", "edit");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/gameForm.jsp");
        dispatcher.forward(request, response);
    }
}
