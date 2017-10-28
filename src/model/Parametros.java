package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Parametros {
	private final IntegerProperty vagas;
	private final DoubleProperty valorEntrada;
	private final DoubleProperty valorHora;

	public Parametros(){
		this(-1, 0.00, 0.00);
	}

	public Parametros(Integer vagas, Double valorEntrada, Double valorHora ){
		this.vagas = new SimpleIntegerProperty(vagas);
		this.valorEntrada = new SimpleDoubleProperty(valorEntrada);
		this.valorHora = new SimpleDoubleProperty(valorHora);
	}

	public Integer getvagas() {
		return this.vagas.get();
	}
	public void setVagas(Integer vagas) {
		this.vagas.set(vagas);
	}
	public IntegerProperty vagasProperty() {
		return vagas;
	}

	// Define a getter for the property's value
    public final double getValorEntrada(){return valorEntrada.get();}
    public final void setValorEntrada(double value){valorEntrada.set(value);}
    public DoubleProperty valorEntradaProperty() {return valorEntrada;}
 // Define a getter for the property's value
    public final double getValorHora(){return valorHora.get();}
    public final void setValorHora(double value){valorHora.set(value);}
    public DoubleProperty valorHoraProperty() {return valorHora;}
}
