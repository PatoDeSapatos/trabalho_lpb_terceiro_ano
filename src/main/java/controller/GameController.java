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
import model.GameDAO;

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
        
        //Entrada
        String oprt = request.getParameter("operation");
        RequestDispatcher dispatcher = null;

        //Saida
        switch (oprt) {
            case "getall": 
                dao.getAllGames(request, response);
                break;
            case "showgame":
                dao.showGame(request, response);
                break;
            case "register":
                request.setAttribute("operation", "register");
                dispatcher = request.getRequestDispatcher("/pages/gameForm.jsp");
                dispatcher.forward(request, response);
                break;
            case "edit":
                String gameId = (String) request.getParameter("game");
                request.setAttribute("game", dao.getGameById(gameId));
                request.setAttribute("operation", "edit");
    
                dispatcher = request.getRequestDispatcher("/pages/gameForm.jsp");
                dispatcher.forward(request, response);
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
                dao.save(request, response);
                break;
            case "edit":
                dao.update(request, response);
                break;
            case "delete":
                dao.delete(request, response);
                break;
            case "buy":
                dao.buy(request, response);
                break;
        
            default:
                break;
        }
    }
}
