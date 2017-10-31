package model;

import java.sql.SQLException;
import java.time.Duration;
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
	private final IntegerProperty id;
	private LocalDateTime entra;
	private LocalDateTime saida;
	private final ObjectProperty<Veiculo> veiculo;
	private final ObjectProperty<Login> login;
    private final DoubleProperty valor;


    public Movimento(){
    	this(-1, null);
    }
    public Movimento(Integer id, Veiculo veiculo){
    	this.id = new SimpleIntegerProperty(id);
    	LocalDateTime data = LocalDateTime.now();
    	this.entra = data;
    	this.saida = data;

    	this.valor = new SimpleDoubleProperty();
    	this.veiculo = new SimpleObjectProperty<Veiculo>(new Veiculo());
    	this.login = new SimpleObjectProperty<Login>(new Login());

    }

    public boolean verificarSaidaPendente(){
    	return this.saida.equals(entra);
    }
    public Duration verificaHoras(){
    	return Duration.between(this.getEntra(), this.getSaida());
    }
    public void salveEntrada() throws SQLException{
    	MovimentoDAO movimentodao = new MovimentoDAO();
    	movimentodao.adiciona(this);
    }
    public void salveSaida() throws SQLException{
    	MovimentoDAO movimentodao = new MovimentoDAO();
    	movimentodao.altera(this);
    }


    public Integer getId(){
		return this.id.get();
	}
	public void setId(Integer id){
		this.id.set(id);
	}
	 public IntegerProperty idProperty() {
        return id;
    }


    public final LocalDateTime getEntra(){return entra;}
    public void setEntrada(LocalDateTime entra){this.entra = entra.withNano(0);}

    public final LocalDateTime getSaida(){return saida;}
    public void setSaida(LocalDateTime saida){this.saida = saida.withNano(0);}


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
