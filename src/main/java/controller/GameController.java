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
	    String name = request.getParameter("name");
        String oprt = request.getParameterValues("oprt")[0];
        ArrayList<GameVO> game = dao.getGameByName(name);

        //Saida
        /*
        switch (oprt) {
            case "discount":
                request.setAttribute("operacao", "Cálculo de Desconto");
                request.setAttribute("resultado", game.calcDiscount());
                break;
            case "benefit":
                request.setAttribute("operacao", "Cálculo de Custo Benefício");
                request.setAttribute("resultado", game.calcCostBenefit());
                break;
            case "popularity":
                request.setAttribute("operacao", "Cálculo de Popularidade");
                request.setAttribute("resultado", game.calcPopularity());
                break;
            default:
                request.setAttribute("operacao", "Operação Inválida");
                request.setAttribute("resultado", "");
                break;
        }*/

		RequestDispatcher dispatcher = request.getRequestDispatcher("/relatorio.jsp");
		dispatcher.forward(request, response);     
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
