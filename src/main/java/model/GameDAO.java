package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GameDAO {
	private DataSource dataSource;

    public GameDAO(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

	public GameVO getGameByName(String name) {
		Connection conexao = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		GameVO game;

		try {
			conexao = dataSource.getConnection();
			String sql = "select from games (id, name, views, price, purchases, discount, rating) where name like `%?%`";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, name);
			result = statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(conexao, statement);
		}

        game = new GameVO();

        return game;
	}

    public boolean save(HttpServletRequest request, HttpServletResponse response) {
		Connection conexao = null;
		PreparedStatement statement = null;
		int result;

        String name = request.getParameter("oprt");
        double price = Double.parseDouble( request.getParameter("price") );
        int discount = Integer.parseInt( request.getParameter("discount") );
        double rating = Double.parseDouble( request.getParameter("rating") );

        try {
			conexao = dataSource.getConnection();
			String sql = "insert into games (id, name, gameviews, price, purchases, discount, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, UUID.randomUUID().toString());
			statement.setString(2, name);
			statement.setInt(3, 0);
			statement.setDouble(4, price);
			statement.setInt(5, 0);
			statement.setInt(6, discount);
			statement.setDouble(7, rating);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		finally {
			closeConnection(conexao, statement);
		}

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
}
