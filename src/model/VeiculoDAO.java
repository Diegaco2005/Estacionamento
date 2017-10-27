package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ConnectionFactory;

public class VeiculoDAO {
	private Connection connection;
	public VeiculoDAO() throws SQLException{
		this.connection = ConnectionFactory.getConnectionFactory();
	}
	public void adiciona(Veiculo veiculo) throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("insert into veiculo(placa, marca, modelo, cor)values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, veiculo.getPlaca());
		stmt.setString(2, veiculo.getMarca());
		stmt.setString(3, veiculo.getModelo());
		stmt.setString(4, veiculo.getCor());

		stmt.execute();
		ResultSet rs = stmt.getGeneratedKeys();
	    rs.next();
	    veiculo.setIdVeiculo(rs.getInt(1));


		stmt.close();
	}
	public ObservableList<Veiculo> selectAll() throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("select id_veiculo, placa, modelo, marca, cor FROM veiculo");
		ResultSet rs = stmt.executeQuery();
		ObservableList<Veiculo> veiculoData = FXCollections.observableArrayList();
	       while (rs.next()) {
	    	   Veiculo veiculo = new Veiculo(rs.getInt("id_veiculo"));
	    	   veiculo.setPlaca(rs.getString("placa"));
	    	   veiculo.setMarca(rs.getString("modelo"));
	    	   veiculo.setModelo(rs.getString("marca"));
	    	   veiculo.setCor(rs.getString("cor"));
	    	   //System.out.println(veiculo.getPlaca());
	    	   veiculoData.add(veiculo);
	       }
	       rs.close();
	       return veiculoData;
	}
}