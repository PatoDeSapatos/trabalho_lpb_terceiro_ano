package controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Passagem;
import model.PassagemDAO;
import model.Pesquisa;

@WebServlet("PassagemController")
public class PassagemController extends HttpServlet {

    
    @Resource(name="tarefaVoos")
	private DataSource dataSource;
    private PassagemDAO dao;
    
	@Override
	public void init() throws ServletException {
		dao = new PassagemDAO(dataSource);
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operacao = req.getParameter("operacao");
        switch (operacao) {
            case "consultar":
                consultar(req, resp);
                break;
        
            default:
                break;
        }

    }

    private void consultar(HttpServletRequest req, HttpServletResponse resp) {
        String origem = req.getParameter("origem");
        String destino = req.getParameter("destino");
        String dataIda = req.getParameter("dataIda");
        String dataVolta = req.getParameter("dataVolta");

        int nPessoas = req.getParameter("pessoas");

        Passagem pIda = dao.acharPassagem(origem, destino, dataIda, nPessoas);
        Passagem pVolta = dao.acharPassagem(destino, origem, dataVolta, nPessoas);

        Passagem pesquisa = new Pesquisa(origem, destino, dataIda, dataVolta, nPessoas);

		Cookie cookie = new Cookie("pesquisa", pesquisa);
		cookie.setMaxAge(60*60*24*365);
		resp.addCookie(cookie);
		
        req.setAttribute("passageIda", pIda);
        req.setAttribute("passagemVolta", pVolta);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("./relatorio.html");
        dispatcher.forward(req, resp);
    }
}