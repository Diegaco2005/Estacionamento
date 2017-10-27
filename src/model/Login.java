package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Login {

	private final IntegerProperty idLogin;
	private final StringProperty login;


	public Login(){
    	this(0, null);
    }
	public Login(Integer idLogin){
    	this(idLogin, null);
    }
    public Login(Integer idLogin, String login){
    	this.idLogin = new SimpleIntegerProperty(idLogin);
    	this.login =  new SimpleStringProperty(login);
    }


    public Integer getIdLogin(){
		return this.idLogin.get();
	}
	public void setIdLogin(Integer id){
		this.idLogin.set(id);
	}
	 public IntegerProperty idLoginProperty() {
        return idLogin;
    }


	public String getlogin() {
        return login.get();
    }

    public void setlogin(String login) {
        this.login.set(login);
    }

    public StringProperty loginProperty() {
        return login;
    }

}
