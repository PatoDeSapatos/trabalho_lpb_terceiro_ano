package model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.DAO;

public class UserDAO extends DAO {

    public UserDAO(DataSource dataSource) {
        super(dataSource);
    }

    public boolean register(UserVO user) {
        Connection conexao = null;
		PreparedStatement statement = null;
		int result;

        try {
			conexao = dataSource.getConnection();
			String sql = "INSERT INTO users (login, password, name, cpf, email, phone_number) VALUES (?, ?, ?, ?, ?, ?);";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getCpf());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getPhoneNumber());
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
			String sql = "DELETE FROM users WHERE id = ?;";
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

    public UserVO getUserByLogin(String login) {
		Connection conexao = null;
		PreparedStatement statement = null;

		try {
			conexao = dataSource.getConnection();
			String sql = "SELECT * FROM users WHERE login = ?;";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				UserVO user = new UserVO(result);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(conexao, statement);
		}

        return null;
    }

	public UserVO getUserById(String id) {
		Connection conexao = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		UserVO user = null;

		try {
			conexao = dataSource.getConnection();
			String sql = "SELECT * FROM users WHERE id = ?;";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, id);
			result = statement.executeQuery();

			while (result.next()) {
				user = new UserVO(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(conexao, statement);
		}

		return user;
	}

}
