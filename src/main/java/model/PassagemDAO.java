package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class PassagemDAO {

	private DataSource dataSource;
	
	public PassagemDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<Passagem> acharPassagem(String destino, String origem, String data, int nPessoas) {
		Connection conexao = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<Passagem> search = new ArrayList<Passagem>();

		try {
			conexao = dataSource.getConnection();
			String sql = "SELECT * FROM voos WHERE origem=? and destino=? and data=?;";
			statement = conexao.prepareStatement(sql);
			statement.setString(1, origem);
			statement.setString(2, destino);
			statement.setString(3, data);
			
			result = statement.executeQuery();
			
			while (result.next()) {
        		search.add(new Passagem(result));
			}
			
			return search;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			fecharConexao(conexao, statement);
		}
        return search;
	}
	
    protected void fecharConexao(Connection conexao, PreparedStatement statement) {
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
