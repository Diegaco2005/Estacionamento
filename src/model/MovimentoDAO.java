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

public class MovimentoDAO {
	private Connection connection;
	public MovimentoDAO() throws SQLException{
		this.connection = ConnectionFactory.getConnectionFactory();
	}
	public void adiciona(Movimento movimento) throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("insert into movimento(entra, saida, valor, id_veiculo, id_login)values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		System.out.println(movimento.getEntra().toString());
		stmt.setString(1, movimento.getEntra().toString());
		stmt.setString(2, movimento.getSaida().toString());
		stmt.setDouble(3, movimento.getValor());
		stmt.setInt(4, movimento.getVeiculo().getIdVeiculo());
		stmt.setInt(5, 1);
		//System.out.println(stmt.toString());
		stmt.execute();
		ResultSet rs = stmt.getGeneratedKeys();
	    rs.next();
	    movimento.setId(rs.getInt(1));
		stmt.close();
	}
	public void altera(Movimento movimento) throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("update movimento SET saida=?, valor=? where id = ?");

		stmt.setString(1, movimento.getSaida().toString());
		stmt.setDouble(2, movimento.getValor());
		stmt.setInt(3, movimento.getId());
		//System.out.println(stmt.toString());
		stmt.execute();
		stmt.close();
	}
	public ObservableList<Movimento> selectAll() throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("SELECT id, entra, saida, valor, id_veiculo, id_login, placa, cor, modelo, marca FROM movimento INNE JOIN veiculo using(id_veiculo);");
		ResultSet rs = stmt.executeQuery();
		ObservableList<Movimento> movimentoData = FXCollections.observableArrayList();
	       while (rs.next()) {

	    	   Movimento movimento = new Movimento();
	    	   movimento.setId(rs.getInt("id"));
	    	   movimento.setEntrada(DateUtil.parseToSql(rs.getString("entra")));
	    	   movimento.setSaida(DateUtil.parseToSql(rs.getString("saida")));
	    	   //System.out.println("SAIDA SQL "+DateUtil.parseToSql(rs.getString("saida")));
	    	   movimento.setValor(rs.getDouble("valor"));
	    	   movimento.setVeiculo(new Veiculo(rs.getInt("id_veiculo"), rs.getString("placa"), rs.getString("marca"), rs.getString("modelo" ), rs.getString("cor" )));
	    	   movimento.setLogin(new Login(rs.getInt("id_login")));

	    	   movimentoData.add(movimento);
	       }
	       rs.close();
	       return movimentoData;
	}

}