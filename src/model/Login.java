package model;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Login {

	private final IntegerProperty idLogin;
	private final StringProperty login;
	private final StringProperty senha;


	public Login(){
    	this(0, null, null);
    }
	public Login(Integer idLogin){
    	this(idLogin, null, null);
    }
    public Login(Integer idLogin, String login, String senha){
    	this.idLogin = new SimpleIntegerProperty(idLogin);
    	this.login =  new SimpleStringProperty(login);
    	this.senha =  new SimpleStringProperty(senha);
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
    public void setLogin(String login) {
        this.login.set(login);
    }
    public StringProperty loginProperty() {
        return login;
    }

    public String getSenha() {
        return senha.get();
    }
    public void setSenha(String senha) {
        this.senha.set(senha);
    }
    public StringProperty senhaProperty() {
        return senha;
    }
    public boolean autentica(){
    	try {
			return new LoginDAO().autentica(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }

}
