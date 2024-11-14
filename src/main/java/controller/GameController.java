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
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        
        String oprt = req.getParameter("operation");

        switch (oprt) {
            case "getall": 
                getHomePage(req, resp);
                break;
            case "showgame":
                getGamePage(req, resp);
                break;
            case "register":
                getResgisterPage(req, resp);
                break;
            case "edit":
                getEditPage(req, resp);
                break;

            default:
                req.setAttribute("operacao", "Operação Inválida");
                req.setAttribute("resultado", "");
                break;
        }  
	} 

    @Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {   
        String oprt = req.getParameter("operation").toLowerCase();

        switch (oprt) {
            case "register":
                registerGame(req, resp);
                break;
            case "edit":
                editGame(req, resp);
                break;
            case "delete":
                deleteGame(req, resp);
                break;
            case "buy":
                buyGame(req, resp);
                break;
        
            default:
                break;
        }
    }

    private void buyGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (String) req.getParameter("game");
        GameVO game = dao.getGameById(id);

        dao.buy(game);

        req.setAttribute("gameInfo", game);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/gamepage.jsp");
		dispatcher.forward(req, resp);
    }

    private void deleteGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (String) req.getParameter("gameId");
        boolean resultado = dao.delete(id);

        req.setAttribute("result", resultado);
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
		dispatcher.forward(req, resp);
    }

    private void editGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("gameId");
        String name = req.getParameter("name");
		String iconLink = req.getParameter("iconLink");
		String bannerLink = req.getParameter("bannerLink");
		double price = Double.parseDouble(req.getParameter("price"));
		int discount = Integer.parseInt(req.getParameter("discount"));
		double rating = Double.parseDouble(req.getParameter("rating"));

        GameVO newGame = new GameVO(id, "", name, iconLink, bannerLink, discount, price, discount, discount, rating);

        boolean resultado = dao.update(id, newGame);

        req.setAttribute("result", resultado);
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
		dispatcher.forward(req, resp);
    }

    private void registerGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String name = req.getParameter("name");
		String iconLink = req.getParameter("iconLink");
		String bannerLink = req.getParameter("bannerLink");
        double price = Double.parseDouble( req.getParameter("price") );
        int discount = Integer.parseInt( req.getParameter("discount") );
        double rating = Double.parseDouble( req.getParameter("rating") );

        GameVO game = new GameVO("", userId, name, iconLink, bannerLink, discount, price, discount, discount, rating);

        boolean resultado = dao.save(game);

        req.setAttribute("result", resultado);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
		dispatcher.forward(req, resp);
    }

    public void getHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("games", dao.getAllGames());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/homepage.jsp");
		dispatcher.forward(req, resp);
    }

    private void getGamePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = (String) req.getParameter("game");

        dao.updateGameViews(id);
        GameVO game = dao.getGameById(id);

        req.setAttribute("gameInfo", game);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/gamepage.jsp");
		dispatcher.forward(req, resp);
    }

    private void getResgisterPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("operation", "register");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/gameForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void getEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gameId = (String) req.getParameter("game");
        req.setAttribute("game", dao.getGameById(gameId));
        req.setAttribute("operation", "edit");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/gameForm.jsp");
        dispatcher.forward(req, resp);
    }
}
