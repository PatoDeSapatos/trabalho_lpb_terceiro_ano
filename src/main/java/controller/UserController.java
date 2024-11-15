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
import jakarta.servlet.http.HttpSession;
import model.user.UserVO;
import model.user.UserDAO;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

	@Resource(name="gamearchive")
	private DataSource dataSource;
    private UserDAO dao;
    
	@Override
	public void init() throws ServletException {
		dao = new UserDAO(dataSource);
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");

        switch (operation) {
            case "getUserPage":
                getUserPage(req, resp);
                break;
        
            default:
                break;
        }    
    }



	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");

        switch(operation) {
            case "register":
                register(req, resp);
                break;
            case "login":
                login(req, resp);
                break;
            default:
                System.out.println("Operação não encontrada: " + operation);
                break;
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String cpf = req.getParameter("cpf");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("tel");
        
        UserVO user = new UserVO(login, password, name, cpf, email, phoneNumber);
        boolean res = dao.register(user);

        RequestDispatcher dispatcher;

        if (res) {
            dispatcher = req.getRequestDispatcher("./index.html");
            
            HttpSession session = req.getSession();
            session.setAttribute("login", user);
        } else {
            req.setAttribute("error", "nome de usuário já existe.");
            dispatcher = req.getRequestDispatcher("./pages/userForm.jsp");
        }

        dispatcher.forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserVO user = dao.getUserByLogin(login);
        RequestDispatcher dispatcher;

        if(user != null && password.equals(user.getPassword())) {
            dispatcher = req.getRequestDispatcher("./index.html");
            HttpSession session = req.getSession();
            session.setAttribute("login", user);
        } else {
            req.setAttribute("error", "nome de usuário ou senha incorretos.");
            dispatcher = req.getRequestDispatcher("./pages/userForm.jsp"); 
        }

        dispatcher.forward(req, resp);
    }
    
	private void getUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = (String) req.getParameter("login");
        UserVO user = dao.getUserByLogin(login);

        req.setAttribute("userInfo", user);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/userpage.jsp");
		dispatcher.forward(req, resp);
	}
}
