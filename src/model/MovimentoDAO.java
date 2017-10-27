package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ConnectionFactory;
import util.DateUtil;

public class MovimentoDAO {
	private Connection connection;
	public MovimentoDAO() throws SQLException{
		this.connection = ConnectionFactory.getConnectionFactory();
	}
	public void adiciona(Movimento movimento) throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("insert into movimento(entra, saida, valor, id_veiculo, id_login)values(?,?,?,?,?)");
		System.out.println(movimento.getEntra().toString());
		stmt.setString(1, movimento.getEntra().toString());
		stmt.setString(2, movimento.getSaida().toString());
		stmt.setDouble(3, movimento.getValor());
		stmt.setInt(4, movimento.getVeiculo().getIdVeiculo());
		stmt.setInt(5, 1);
		//System.out.println(stmt.toString());
		stmt.execute();
		stmt.close();
	}
	public ObservableList<Movimento> selectAll() throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("select entra, saida, valor, id_veiculo, id_login FROM movimento");
		ResultSet rs = stmt.executeQuery();
		ObservableList<Movimento> movimentoData = FXCollections.observableArrayList();
	       while (rs.next()) {

	    	   Movimento movimento = new Movimento();
	    	   movimento.setEntrada(DateUtil.parseToSql(rs.getString("entra")));
	    	   movimento.setSaida(DateUtil.parseToSql(rs.getString("saida")));
	    	   movimento.setValor(rs.getDouble("valor"));
	    	   movimento.setVeiculo(new Veiculo(rs.getInt("id_veiculo")));
	    	   movimento.setLogin(new Login(rs.getInt("id_login")));

	    	   movimentoData.add(movimento);
	       }
	       rs.close();
	       return movimentoData;
	}
}