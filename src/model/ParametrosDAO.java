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
import view.ParametrosViewController;

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
		PreparedStatement stmt = this.connection.prepareStatement("UPDATE opcoes SET vagas = ?, preco_entrada = ?, preco_hora = ?");
		//System.out.println(movimento.getEntra().toString());
		stmt.setInt(1, parametros.getVagas());
		stmt.setDouble(2, parametros.getValorEntrada());
		stmt.setDouble(3, parametros.getValorHora());
		//System.out.println(stmt.toString());
		stmt.execute();
		stmt.close();
	}
	public Parametros acessa() throws SQLException{
		PreparedStatement stmt = this.connection.prepareStatement("select vagas, preco_entrada, preco_hora, alterado FROM opcoes");
		ResultSet rs = stmt.executeQuery();

		Parametros parametros = new Parametros();
		if (rs.next()) {
    	   parametros.setVagas(rs.getInt("vagas"));
    	   parametros.setValorEntrada(rs.getDouble("preco_entrada"));
    	   parametros.setValorHora(rs.getDouble("preco_hora"));
       }
       rs.close();
       return parametros;
	}


}