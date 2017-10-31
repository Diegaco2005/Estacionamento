package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Veiculo;
import util.MaskTextField;

public class VeiculoAddDialogController {
	@FXML
	private MaskTextField placaField;
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
		placaField.setMask("LLL-NNNN");
		placaField.textProperty().addListener((ov, oldValue, newValue) -> {
    		placaField.setText(newValue.toUpperCase());
    	});
		marcaField.textProperty().addListener((ov, oldValue, newValue) -> {
			marcaField.setText(newValue.toUpperCase());
    	});
		corField.textProperty().addListener((ov, oldValue, newValue) -> {
			corField.setText(newValue.toUpperCase());
    	});
		modeloField.textProperty().addListener((ov, oldValue, newValue) -> {
			modeloField.setText(newValue.toUpperCase());
    	});
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
		 String errorMessage = "";
	        if (placaField.getText() == null || placaField.getText().length() == 0) {
	            errorMessage += "Placa é obrigatoria!\n";
	        }
	        if(placaField.getLength() < 8 ){
	        	errorMessage += "Placa inválido!\n";
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Mostra a mensagem de erro.
	            Alert alert = new Alert(AlertType.ERROR);
	                      alert.setTitle("Campos Inválidos");
	                      alert.setHeaderText("Por favor, corrija os campos inválidos");
	                      alert.setContentText(errorMessage);
	                alert.showAndWait();

	            return false;
	        }
	    }
}
