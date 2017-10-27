package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;

public class Movimento {
/*
 * id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
	entra DATETIME DEFAULT CURRENT_TIMESTAMP,
	saida DATETIME,
	valor DECIMAL(10,2),
	id_veiculo INT,
	id_login INT,
	*/
	private LocalDateTime entra;
	private LocalDateTime saida;
	private final ObjectProperty<Veiculo> veiculo;
	private final ObjectProperty<Login> login;
    private final DoubleProperty valor;


    public Movimento(){
    	this(null);
    }
    public Movimento(Veiculo veiculo){
    	this.saida = LocalDateTime.of(1800, 1, 12, 0, 0);
    	this.entra = LocalDateTime.of(1800, 1, 12, 0, 0);
    	this.valor = new SimpleDoubleProperty();
    	this.veiculo = new SimpleObjectProperty<Veiculo>(new Veiculo());
    	this.login = new SimpleObjectProperty<Login>(new Login());

    }

    public void salve() throws SQLException{
    	MovimentoDAO movimentodao = new MovimentoDAO();
    	movimentodao.adiciona(this);
    }

    public final LocalDateTime getEntra(){return entra;}
    public void setEntrada(LocalDateTime entra){this.entra = entra;}

    public final LocalDateTime getSaida(){return saida;}
    public void setSaida(LocalDateTime saida){this.saida = saida;}


    // Define a getter for the property's value
    public final double getValor(){return valor.get();}
    public final void setValor(double value){valor.set(value);}
    public DoubleProperty valorProperty() {return valor;}

    // Define a getter for the property's value
    public final Veiculo getVeiculo(){return veiculo.get();}
    public final void setVeiculo(Veiculo value){veiculo.set(value);}
    public ObjectProperty veiculoProperty() {return veiculo;}

    // Define a getter for the property's value
    public final Login getLogin(){return login.get();}
    public final void setLogin(Login value){login.set(value);}
    public ObjectProperty loginProperty() {return login;}



}
