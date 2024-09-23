package model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.sql.DataSource;

import model.DAO;
import model.game.GameVO;

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
			String sql = "INSERT INTO users (id, login, password, name, cpf, email, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?);";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, UUID.randomUUID().toString());
			statement.setString(2, user.getLogin());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getName());
			statement.setString(5, user.getCpf());
			statement.setString(6, user.getEmail());
			statement.setString(7, user.getPhoneNumber());
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
			String sql = "SELECT * FROM games WHERE name = '?';";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet result = statement.executeQuery();

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
