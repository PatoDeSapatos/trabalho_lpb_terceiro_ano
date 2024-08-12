package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.sql.DataSource;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GameDAO {
	private DataSource dataSource;

    public GameDAO(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

	public ArrayList<GameVO> getGameByName(String name) {
		Connection conexao = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<GameVO> search = new ArrayList<GameVO>();

		try {
			conexao = dataSource.getConnection();
			String sql = "SELECT * FROM games WHERE `name` LIKE '%?%';";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, name);
			result = statement.executeQuery();
			
			while (result.next()) {
        		search.add(new GameVO(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(conexao, statement);
		}
        return search;
	}

	public void getAllGames(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexao = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<GameVO> search = new ArrayList<GameVO>();

		try {
			conexao = dataSource.getConnection();
			String sql = "SELECT * FROM games;";
			statement = conexao.prepareStatement(sql);
			result = statement.executeQuery();
			
			while (result.next()) {
        		search.add(new GameVO(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(conexao, statement);
		}
		
		request.setAttribute("games", search);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/homepage.jsp");
		dispatcher.forward(request, response);
	}

    public boolean save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexao = null;
		PreparedStatement statement = null;
		int result;

        String name = request.getParameter("name");
		String iconLink = request.getParameter("iconLink");
		String bannerLink = request.getParameter("bannerLink");
        double price = Double.parseDouble( request.getParameter("price") );
        int discount = Integer.parseInt( request.getParameter("discount") );
        double rating = Double.parseDouble( request.getParameter("rating") );

        try {
			conexao = dataSource.getConnection();
			String sql = "INSERT INTO games (`id`, `name`, `iconLink`, `bannerLink`, `views`, `price`, `purchases`, `discount`, `rating`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, UUID.randomUUID().toString());
			statement.setString(2, name);
			statement.setString(3, iconLink);
			statement.setString(4, bannerLink);
			statement.setInt(5, 0);
			statement.setDouble(6, price);
			statement.setInt(7, 0);
			statement.setInt(8, discount);
			statement.setDouble(9, rating);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		finally {
			closeConnection(conexao, statement);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
        return result == 1;
    }

	public boolean delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexao = null;
		PreparedStatement statement = null;
		String id = (String) request.getAttribute("gameId");
		int result;

		try {
			conexao = dataSource.getConnection();
			String sql = "DELETE FROM games WHERE `id` LIKE '%?%';";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, id);
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		} finally {
			closeConnection(conexao, statement);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
		return result == 1;
	}

	public boolean update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexao = null;
		PreparedStatement statement = null;
		int result;
		String name = request.getParameter("name");
		String iconLink = request.getParameter("iconLink");
		String bannerLink = request.getParameter("bannerLink");
		double price = Double.parseDouble(request.getParameter("price"));
		String id = request.getParameter("id");

		try {
			conexao = dataSource.getConnection();
			String sql = "UPDATE games SET `name` = '?', `iconLink` = '?', `bannerLink` = '?', `price` = ?,  WHERE `id` LIKE '%?%';";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, iconLink);
			statement.setString(3, bannerLink);
			statement.setDouble(4, price);
			statement.setString(5, id);
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
		return result == 1;
	}

    private void closeConnection(Connection conexao, PreparedStatement statement) {
        try {
			if (conexao != null) {
				conexao.close();
			}
			if (statement != null) {
				statement.close();
			}
        } catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void showGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getAttribute("game");

		GameVO game = getGameById(id);
		request.setAttribute("game", game);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/gamepage.jsp");
		dispatcher.forward(request, response);
    }

	public GameVO getGameById(String gameId) {
		Connection conexao = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		GameVO game = null;

		try {
			conexao = dataSource.getConnection();
			String sql = "SELECT * FROM games WHERE `id` = '?';";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, gameId);
			result = statement.executeQuery();
			game = new GameVO(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(conexao, statement);
		}

		return game;
	}
}
