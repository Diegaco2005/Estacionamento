package model;

import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Veiculo {
	private final IntegerProperty idVeiculo;
	private final StringProperty placa;
	private final StringProperty marca;
	private final StringProperty modelo;
	private final StringProperty cor;


	public Veiculo(){
		this(-1,null,null,null,null);
	}
	public Veiculo(int idVeiculo){
		this(idVeiculo,null,null,null,null);
	}
	public Veiculo(String placa){
		this(-1,placa,null,null,null);
	}
	public Veiculo(Integer idVeiculo, String placa, String marca, String modelo, String cor){
		this.idVeiculo = new SimpleIntegerProperty(idVeiculo);
		this.placa = new SimpleStringProperty(placa);
		this.marca = new SimpleStringProperty(marca);
		this.modelo = new SimpleStringProperty(modelo);
		this.cor = new SimpleStringProperty(cor);
	}

	public void salve() throws SQLException{
		 VeiculoDAO veiculodao = new VeiculoDAO();
		 veiculodao.adiciona(this);
	}

	public Integer getIdVeiculo(){
		return this.idVeiculo.get();
	}
	public void setIdVeiculo(Integer id){
		this.idVeiculo.set(id);
	}
	 public IntegerProperty idVeiculoProperty() {
        return idVeiculo;
    }


	public String getPlaca(){
		return this.placa.get();
	}
	public void setPlaca(String placa){
		this.placa.set(placa);
	}
	 public StringProperty placaProperty() {
        return placa;
    }


	public String getMarca(){
		return this.marca.get();
	}
	public void setMarca(String marca){
		this.marca.set(marca);
	}
	public StringProperty marcaProperty() {
        return marca;
    }


	public String getModelo() {
		return this.modelo.get();
	}
	public void setModelo(String modelo) {
		this.modelo.set(modelo);
	}
	public StringProperty modeloProperty() {
        return modelo;
    }



	public String getCor() {
		return this.cor.get();
	}
	public void setCor(String cor) {
		this.cor.set(cor);
	}
	public StringProperty corProperty() {
        return cor;
    }



}
