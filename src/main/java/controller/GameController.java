package controller;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.game.GameDAO;
import model.game.GameVO;
import model.user.UserVO;

@WebServlet("/GameController")
public class GameController extends HttpServlet {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gamearchive");
	private static final long serialVersionUID = 1L;
	private GameDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new GameDAO(emf);
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
		int id = Integer.parseInt( (String) req.getParameter("game") );
		GameVO game = dao.getGameById(id);

		dao.buy(game);

		req.setAttribute("gameInfo", game);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/gamepage.jsp");
		dispatcher.forward(req, resp);
	}

	private void deleteGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("gameId"));
		boolean resultado = dao.delete(id);

		req.setAttribute("result", resultado);
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
		dispatcher.forward(req, resp);
	}

	private void editGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt( req.getParameter("gameId") );
		String name = req.getParameter("name");
		String iconLink = req.getParameter("iconLink");
		String bannerLink = req.getParameter("bannerLink");
		double price = Double.parseDouble(req.getParameter("price"));
		int discount = Integer.parseInt(req.getParameter("discount"));
		double rating = Double.parseDouble(req.getParameter("rating"));

		GameVO game = dao.getGameById(id);
		game.setName(name);
		game.setIconLink(iconLink);
		game.setBannerLink(bannerLink);
		game.setPrice(price);
		game.setDiscount(discount);
		game.setRating(rating);

		boolean resultado = dao.update(id, game);

		req.setAttribute("result", resultado);
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
		dispatcher.forward(req, resp);
	}

	private void registerGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserVO user = (UserVO) req.getSession().getAttribute("login");
		String name = req.getParameter("name");
		String iconLink = req.getParameter("iconLink");
		String bannerLink = req.getParameter("bannerLink");
		double price = Double.parseDouble(req.getParameter("price"));
		int discount = Integer.parseInt(req.getParameter("discount"));
		double rating = Double.parseDouble(req.getParameter("rating"));

		GameVO game = new GameVO(0, user, name, iconLink, bannerLink, discount, price, discount, discount, rating);

		Cookie cName = new Cookie("name", name);
		Cookie cPrice = new Cookie("price", String.valueOf(price));
		Cookie cViews = new Cookie("views", String.valueOf(game.getViews()));

		cName.setMaxAge(60*60*24*365);
		cPrice.setMaxAge(60*60*24*365);
		cViews.setMaxAge(60*60*24*365);

		resp.addCookie(cName);
		resp.addCookie(cPrice);
		resp.addCookie(cViews);
		
		boolean resultado = dao.save(game);

		req.setAttribute("result", resultado);
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
		dispatcher.forward(req, resp);
	}

	public void getHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("allGames", dao.getAllGames());
		if((UserVO) req.getSession().getAttribute("login") != null) {
			req.setAttribute("userGames", dao.getAllUserGames((UserVO) req.getSession().getAttribute("login")));
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/homepage.jsp");
		dispatcher.forward(req, resp);
	}

	private void getGamePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt( (String) req.getParameter("game") );

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
		int gameId = Integer.parseInt( (String) req.getParameter("game") );
		req.setAttribute("game", dao.getGameById(gameId));
		req.setAttribute("operation", "edit");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/gameForm.jsp");
		dispatcher.forward(req, resp);
	}
}
