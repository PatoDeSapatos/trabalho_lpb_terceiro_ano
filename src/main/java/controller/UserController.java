package controller;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

	static final long serialVersionUID = 1L;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gamearchive");
    private UserDAO dao;
    
	@Override
	public void init() throws ServletException {
		dao = new UserDAO(emf);
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");

        switch (operation) {
            case "getUserPage":
                getUserPage(req, resp);
                break;

            case "logout":
                logout(req, resp);
                break;
        
            default:
                System.out.println("GErro: operação não encontrada: " + operation);
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
                    
                case "edit":
                    edit(req, resp);
                    break;
                
                default:
                    System.out.println("PErro: Operação não encontrada: " + operation);
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

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserVO user = (UserVO) session.getAttribute("login");
        dao.edit(user.getId(), req.getParameter("name"), req.getParameter("cpf"), req.getParameter("phoneNumber"));
        session.setAttribute("login", user);
        req.getRequestDispatcher("./index.html");
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("./index.html").forward(req, resp);
    }
    
	private void getUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = (String) req.getParameter("login");
        UserVO user = dao.getUserByLogin(login);

        req.setAttribute("userInfo", user);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/userpage.jsp");
		dispatcher.forward(req, resp);
	}
}
