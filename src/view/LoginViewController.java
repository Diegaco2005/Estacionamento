package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Login;
import model.Veiculo;

public class LoginViewController {
	@FXML
	private TextField loginField;
	@FXML
	private TextField senhaField;

	private Stage dialogStage;
	private Login login;

	private boolean okLogin = false;

	@FXML
	private void initialize(){
	}

	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	public boolean isOkLogin(){
		return okLogin;
	}

	@FXML
	private void handleOk(){
		if (isInputValid()) {
			Login login = new Login();
            login.setLogin(loginField.getText());
            login.setSenha(senhaField.getText());
            if(login.autentica()){
            	 okLogin = true;
            	 this.login = login;
                 dialogStage.close();
            }else{
            	 // Mostra a mensagem de erro.
                Alert alert = new Alert(AlertType.ERROR);
                          alert.setTitle("Login Inv�lido");
                          alert.setHeaderText("Por favor, verifique os campos");
                          alert.setContentText("Login ou senha incorretor!");
                    alert.showAndWait();

            }
        }

	}
	private boolean isInputValid(){
		String errorMessage = "";
        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Login inv�lido!\n";
        }
        if (senhaField.getText() == null || senhaField.getText().length() == 0) {
            errorMessage += "Senha inv�lida!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alert = new Alert(AlertType.ERROR);
                      alert.setTitle("Campos Inv�lidos");
                      alert.setHeaderText("Por favor, corrija os campos inv�lidos");
                      alert.setContentText(errorMessage);
                alert.showAndWait();

            return false;
        }
	}
	public Login getLogin(){
		return this.login;

	}

}
