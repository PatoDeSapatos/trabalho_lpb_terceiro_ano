package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.persistence.EntityManagerFactory;


public abstract class DAO {
    protected EntityManagerFactory emf;

    public DAO(EntityManagerFactory emf) {
        super();
        this.emf = emf;
    }
    
    

    protected void closeConnection(Connection conexao, PreparedStatement statement) {
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