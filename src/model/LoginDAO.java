package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ConnectionFactory;
import util.DateUtil;

public class LoginDAO {
	private Connection connection;
	public LoginDAO() throws SQLException{
		this.connection = ConnectionFactory.getConnectionFactory();
	}
	public boolean autentica(Login login) throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("SELECT login FROM login WHERE senha = ? AND login = ?;");
		stmt.setString(1, login.getSenha());
		stmt.setString(2, login.getlogin());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			rs.close();
			return true;
		}
		rs.close();
		return false;
	}


}