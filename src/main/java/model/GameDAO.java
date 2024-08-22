package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;

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
			String sql = "SELECT * FROM games WHERE name LIKE '%?%';";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, name);
			result = statement.executeQuery();
			
			while (result.next()) {
        		search.add(new GameVO(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(conexao, statement);
		}
        return search;
	}

	public ArrayList<GameVO> getAllGames() throws ServletException, IOException {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(conexao, statement);
		}

		return search;
	}

    public boolean save(GameVO game) {
		Connection conexao = null;
		PreparedStatement statement = null;
		int result;

        try {
			conexao = dataSource.getConnection();
			String sql = "INSERT INTO games (id, name, iconLink, bannerLink, views, price, purchases, discount, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, UUID.randomUUID().toString());
			statement.setString(2, game.getName());
			statement.setString(3, game.getIconLink());
			statement.setString(4, game.getBannerLink());
			statement.setInt(5, 0);
			statement.setDouble(6, game.getPrice());
			statement.setInt(7, 0);
			statement.setInt(8, game.getDiscount());
			statement.setDouble(9, game.getRating());
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

	public boolean delete(String id) {
		Connection conexao = null;
		PreparedStatement statement = null;
		int result;

		try {
			conexao = dataSource.getConnection();
			String sql = "DELETE FROM games WHERE id = ?;";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, id);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		} finally {
			closeConnection(conexao, statement);
		}

		return result == 1;
	}

	public boolean update(String id, GameVO newGame) throws ServletException, IOException {
		Connection conexao = null;
		PreparedStatement statement = null;
		int result;

		try {
			conexao = dataSource.getConnection();
			String sql = "UPDATE games SET name = ?, iconLink = ?, bannerLink = ?, price = ?, discount = ?, rating = ? WHERE id = ?;";
			statement = conexao.prepareStatement(sql);

			statement.setString(1, newGame.getName());
			statement.setString(2, newGame.getIconLink());
			statement.setString(3, newGame.getBannerLink());
			statement.setDouble(4, newGame.getPrice());
			statement.setInt(5, newGame.getDiscount());
			statement.setDouble(6, newGame.getRating());
			statement.setString(7, id);

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

    public void updateGameViews(String id) throws ServletException, IOException {
		Connection conexao = null;
		PreparedStatement statement = null;

		try {
			conexao = dataSource.getConnection();
			String sql = "UPDATE games SET views = views + 1 WHERE id = ?;";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	public GameVO getGameById(String gameId) {
		Connection conexao = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		GameVO game = null;

		try {
			conexao = dataSource.getConnection();
			String sql = "SELECT * FROM games WHERE id = ?;";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, gameId);
			result = statement.executeQuery();

			while (result.next()) {
				String name = result.getString("name");
				String iconLink = result.getString("iconLink");
				String bannerLink = result.getString("bannerLink");
				int views = result.getInt("views");
				double price = result.getDouble("price");
				int purchases = result.getInt("purchases");
				int discount = result.getInt("discount");
				double rating = result.getDouble("rating");

				game = new GameVO(gameId, name, iconLink, bannerLink, views, price, purchases, discount, rating);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(conexao, statement);
		}

		return game;
	}

	public void buy(GameVO game) {
		Connection conexao = null;
		PreparedStatement statement = null;

		String id = game.getId();
		int purchases = game.getPurchases() + 1;
		game.setPurchases(purchases);

		try {
			conexao = dataSource.getConnection();
			String sql = "UPDATE games SET purchases = purchases + 1 WHERE id = ?;";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
