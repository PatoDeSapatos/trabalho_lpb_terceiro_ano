package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GameDAO;
import model.GameVO;

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

        //Saida
        switch (oprt) {
            case "getall": 
                dao.getAllGames(request, response);
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
        
            default:
                break;
        }
    }
}
