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

    public UserVO login(String login, String password) {
		Connection conexao = null;
		PreparedStatement statement = null;

		try {
			conexao = dataSource.getConnection();
			String sql = "SELECT * FROM users WHERE login = '?';";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet result = statement.executeQuery(sql);

            UserVO user = new UserVO(result);
            if (user.getPassword() == password) {
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
}
