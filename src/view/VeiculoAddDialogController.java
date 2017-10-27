package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Veiculo;

public class VeiculoAddDialogController {
	@FXML
	private TextField placaField;
	@FXML
	private TextField marcaField;
	@FXML
	private TextField modeloField;
	@FXML
	private TextField corField;

	private Stage dialogStage;
	private Veiculo veiculo;
	private boolean okClicked = false;


	@FXML
	private void initialize(){
	}

	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	public void setVeiculo(Veiculo veiculo){
		this.veiculo = veiculo;
		placaField.setText(veiculo.getPlaca());

	}
	public boolean isOkClicked(){
		return okClicked;
	}

	@FXML
	private void handleOk(){
		if (isInputValid()) {
            veiculo.setPlaca(placaField.getText());
            veiculo.setMarca(marcaField.getText());
            veiculo.setModelo(modeloField.getText());
            veiculo.setCor(corField.getText());

            okClicked = true;
            dialogStage.close();
        }

	}
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
	 private boolean isInputValid() {
		 /*String errorMessage = "";
	        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
	            errorMessage += "Nome inv�lido!\n";
	        }
	        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
	            errorMessage += "Sobrenome inv�lido!\n";
	        }
	        if (streetField.getText() == null || streetField.getText().length() == 0) {
	            errorMessage += "Rua inv�lida!\n";
	        }

	        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
	            errorMessage += "C�digo Postal inv�lido!\n";
	        } else {
	            // tenta converter o c�digo postal em um int.
	            try {
	                Integer.parseInt(postalCodeField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "C�digo Postal inv�lido (deve ser um inteiro)!\n";
	            }
	        }

	        if (cityField.getText() == null || cityField.getText().length() == 0) {
	            errorMessage += "Cidade inv�lida!\n";
	        }

	        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
	            errorMessage += "Anivers�rio inv�lido!\n";
	        } else {
	            if (!DateUtil.validDate(birthdayField.getText())) {
	                errorMessage += "Anivers�rio inv�lido. Use o formato dd.mm.yyyy!\n";
	            }
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
	        }*/
	       return true;
	    }
}
