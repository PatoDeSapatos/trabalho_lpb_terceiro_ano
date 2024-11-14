package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;

import javax.sql.DataSource;

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


@WebServlet("/PassagemController")
public class PassagemController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Resource(name="tarefaVoos")
	private DataSource dataSource;
    private PassagemDAO dao;
    
	@Override
	public void init() throws ServletException {
		dao = new PassagemDAO(dataSource);
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operacao = req.getParameter("operacao");
        switch (operacao) {
            case "consultar":
                consultar(req, resp);
                break;
        
            default:
                break;
        }

    }

    private void consultar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String origem = req.getParameter("origem").toUpperCase();
        String destino = req.getParameter("destino").toUpperCase();
        String dataIda = req.getParameter("dataIda");
        String dataVolta = req.getParameter("dataVolta");
        String pessoasString = req.getParameter("pessoas");
        int nPessoas = 1;
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        if (dataIda != null) {
	        dataIda = format.format(LocalDate.parse(dataIda)).toString().strip();
        }
        
        if (dataVolta != null) {
        	dataVolta = format.format(LocalDate.parse(dataVolta)).toString().strip();
        }

        if (pessoasString != "") nPessoas = Integer.parseInt(pessoasString);
        
        ArrayList<Passagem> pIda = dao.acharPassagem(origem, destino, dataIda, nPessoas);
        ArrayList<Passagem> pVolta = dao.acharPassagem(destino, origem, dataVolta, nPessoas);
        Pesquisa pesquisa = new Pesquisa(origem, destino, dataIda, dataVolta, nPessoas);
        
        System.out.println(pIda);
        
		Cookie cookie = new Cookie("pesquisa", URLEncoder.encode( pesquisa.toString(), "utf-8" ));
		cookie.setMaxAge(60*60*24*365);
		resp.addCookie(cookie);
		
        req.setAttribute("passagemIda", pIda);
        req.setAttribute("passagemVolta", pVolta);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("./relatorio.jsp");
        dispatcher.forward(req, resp);
    }
}