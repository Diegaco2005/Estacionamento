/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ConnectionFactory;
import util.DateUtil;
import view.Parametros;

/**
 *
 * @author Diego
 */
public class ParametrosDAO {
    	private Connection connection;
	public ParametrosDAO() throws SQLException{
		this.connection = ConnectionFactory.getConnectionFactory();
	}
	public void atualiza(Parametros parametros) throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("UPDATE opcoes SET vagas = ?, preco_entrada = ?, preco_hora = ?, alterado = ?");
		//System.out.println(movimento.getEntra().toString());
		stmt.setInt(1, parametros.getVagas());
		stmt.setDouble(2, parametros.getVlBase());
		stmt.setDouble(3, parametros.getVlAdicional());
		stmt.setString(4, parametros.getHistorico().toString());
		//System.out.println(stmt.toString());
		stmt.execute();
		stmt.close();
	}
	public ObservableList<Parametros> acessa() throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("select vagas, preco_entrada, preco_hora, alterado FROM opcoes");
		ResultSet rs = stmt.executeQuery();
		ObservableList<Parametros> ParametrosData = FXCollections.observableArrayList();
	       while (rs.next()) {

	    	   Parametros parametros = new Parametros();
	    	   parametros.setVagas(rs.getInt("vagas"));
	    	   parametros.setVlBase(rs.getDouble("preco_entrada"));
	    	   parametros.setVlAdicional(rs.getDouble("preco_hora"));
	    	   parametros.setHistorico(DateUtil.parseToSql(rs.getString("alterado")));

	    	   ParametrosData.add(parametros);
	       }
	       rs.close();
	       return ParametrosData;
	}

    
}
